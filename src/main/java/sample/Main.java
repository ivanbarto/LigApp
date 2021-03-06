package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;
    private BorderPane rootLayout;

    public static Stage stage;

//    static {
//        nu.pattern.OpenCV.loadShared();
//        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
//    }

    static {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        try{
//            nu.pattern.OpenCV.loadShared();
//        }catch (Exception e){
//            System.err.println(e);
//        }
        nu.pattern.OpenCV.loadShared();

        //TODO por ahora entra directo a main menu pero despues poner el login

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LigApp");
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.getIcons().add(new Image("file:src/main/resources/images/icon.png"));

        initLogin();


        //showHomeScreen();
        //showPlayerOverview();
        //showTeamOverview();
    }

    public void initRootLayout() {
        try {
            Parent rootLayout = FXMLLoader.load(getClass().getResource("/fxml/rootLayout.fxml"));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("My New Stage Title");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initLogin() {
        try {
            Parent rootLayout = FXMLLoader.load(getClass().getResource("/fxml/login_screen.fxml"));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("LigApp - Login");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO NO BORRAR ESTOS METODOS, POR SI TENEMOS PROBLEMAS MAS ADELANTE
    public void showHomeScreen(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/homeScreen.fxml"));
            AnchorPane homeScreen = (AnchorPane) loader.load();

            rootLayout.setCenter(homeScreen);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPlayerOverview(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/player_overview.fxml"));
            AnchorPane playerOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(playerOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTeamOverview(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/team_overview.fxml"));
            AnchorPane teamOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(teamOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        launch(args);
    }

}
