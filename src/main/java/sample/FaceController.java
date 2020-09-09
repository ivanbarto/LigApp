package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import sample.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FaceController {

    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button btnSavePicture;
    @FXML
    private Button btnInitCamera;
    @FXML
    private ImageView currentFrame;
    @FXML
    private Button btnCancel;


    // a timer for acquiring the video stream
    private ScheduledExecutorService timer;
    // the OpenCV object that realizes the video capture
    private VideoCapture capture = new VideoCapture();
    // a flag to change the button behavior
    private boolean cameraActive = false;
    // the id of the camera to be used
    private static int cameraId = 0;

    /**
     * The action triggered by pushing the button on the GUI
     *
     * @param event the push button event
     */
    @FXML
    protected void startCamera(ActionEvent event) {

        CascadeClassifier classifier = new CascadeClassifier("classifiers/lbpcascade_frontalface.xml");

        if (!this.cameraActive) {
            // start the video capture
            this.capture.open(cameraId);

            // is the video stream available?
            if (this.capture.isOpened()) {
                this.cameraActive = true;
                btnSavePicture.setDisable(false);

                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run() {
                        // effectively grab and process a single frame
                        Mat frame = grabFrame();

                        //detectamos todas las caras
                        MatOfRect faceDetection = new MatOfRect();
                        classifier.detectMultiScale(frame,faceDetection);

                        //hace un crop de la cara - puede servir para crear un histograma de la cara

                        //despues asi la asignamos para que se vea en camara
                        //updateImageView(currentFrame, SwingFXUtils.toFXImage(subImage,null));

                        //pintamos un rectangulo en las caras
                        for (Rect rect : faceDetection.toArray()){
                            Imgproc.rectangle(
                                    frame,
                                    new Point(rect.x,rect.y),
                                    new Point(rect.x+rect.width, rect.y+rect.height),
                                    new Scalar(0,0,255),
                                    2
                            );
                        }
                        BufferedImage subImage = Utils.matToBufferedImage(frame).getSubimage(
                                faceDetection.toArray()[0].x,
                                faceDetection.toArray()[0].y,
                                faceDetection.toArray()[0].width,
                                faceDetection.toArray()[0].height
                        );

                        // convert and show the frame
                        Image imageToShow = Utils.mat2Image(frame);



//                        ArrayList cara = new ArrayList<Mat>();
//                        cara.add(frame);
//
//
//
//                        float[] range = {0, 256}; //the upper boundary is exclusive
//                        MatOfFloat histRange = new MatOfFloat(range);
//                        Imgproc.calcHist(cara,new MatOfInt(0),new Mat(),new Mat(),new MatOfInt(256),histRange);

                        savePlayerFace(subImage);
                        updateImageView(currentFrame,imageToShow);
                    }
                };

                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 66, TimeUnit.MILLISECONDS);

                // update the button content
                this.btnInitCamera.setText("Cerrar cámara");
            } else {
                // log the error
                System.err.println("Impossible to open the camera connection...");
            }
        } else {
            // the camera is not active at this point
            this.cameraActive = false;
            // update again the button content
            this.btnInitCamera.setText("Iniciar cámara");

            btnSavePicture.setDisable(true);
            // stop the timer
            this.stopAcquisition();
        }
    }

    public void btnBackOnAction() throws IOException {
        backToMainMenu(btnCancel);
    }

    public void backToMainMenu(Button button) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/fxml/tabs.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) button.getScene().getWindow();

        window.setScene(mainMenuScene);
        window.show();
    }

    /**
     * Get a frame from the opened video stream (if any)
     *
     * @return the {@link Mat} to show
     */
    private Mat grabFrame() {
        // init everything
        Mat frame = new Mat();

        // check if the capture is open
        if (this.capture.isOpened()) {
            try {
                // read the current frame
                this.capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()) {
                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
                }

            } catch (Exception e) {
                // log the error
                System.err.println("Exception during the image elaboration: " + e);
            }
        }

        return frame;
    }

    /**
     * Stop the acquisition from the camera and release all the resources
     */
    private void stopAcquisition() {
        if (this.timer != null && !this.timer.isShutdown()) {
            try {
                // stop the timer
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // log any exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }

        if (this.capture.isOpened()) {
            // release the camera
            this.capture.release();
        }
    }

    /**
     * Update the {@link ImageView} in the JavaFX main thread
     *
     * @param view  the {@link ImageView} to update
     * @param image the {@link Image} to show
     */
    private void updateImageView(ImageView view, Image image) {
        Utils.onFXThread(view.imageProperty(), image);
    }

    public void setBtnSavePictureOnAction(ActionEvent event){
        btnSavePicture.setDisable(true);
        this.cameraActive=false;
        this.stopAcquisition();
    }

    /**
     * On application close, stop the acquisition from the camera
     */
    protected void setClosed() {
        this.stopAcquisition();
    }

    public void saveImage(){
        File input = new File("faces/sample.jpg");
        try{
            //Reading the image
            BufferedImage image = ImageIO.read(input);

            //Saving the image with a different name
            File output = new File("faces/sample.jpg");
            ImageIO.write(image, "jpg", output);

            System.out.println("image Saved");
        }catch (Exception e){
            System.out.println("error: " + e);
        }
    }

    //la cara que va al sv cuando se le saca la foto de muestra

    private void savePlayerFace(BufferedImage playerFaceImage){
        try{
            //Saving the image with a different name
            File outPut = new File("faces/sample.jpg");
            ImageIO.write(playerFaceImage, "jpg", outPut);


            System.out.println("player's face image Saved");
        }catch (Exception e){
            System.out.println("error: " + e);
        }
    }

    private void compareFaces(BufferedImage actualFace, BufferedImage savedFace){


    }

    private void convertImage2Matrix(){
        Mat matrix = Imgcodecs.imread("C:/OpenCV/sample.jpg");
    }

    private void writeImage(){

        //Reading the Image from the file and storing it in to a Matrix object
        String file ="C:/EXAMPLES/OpenCV/sample.jpg";
        Mat matrix = Imgcodecs.imread(file);

        System.out.println("Image Loaded ..........");
        String file2 = "C:/EXAMPLES/OpenCV/sample_resaved.jpg";

        //Writing the image
        Imgcodecs.imwrite(file2, matrix);
        System.out.println("Image Saved ............");
    }

    @FXML //guardar un archivo y mostrarlo en pantalla tipo preview
    private void openFileChooser(ActionEvent event){
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(Stage.getWindows().get(0));
        if (file != null) {
            openFile(file);
        }

    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
