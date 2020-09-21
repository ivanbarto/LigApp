package sample.utils;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public final class Utils {

    /**
     * Convert a Mat object (OpenCV) in the corresponding Image for JavaFX
     *
     * @param frame
     *            the {@link Mat} representing the current frame
     * @return the {@link Image} to show
     */
    public static Image mat2Image(Mat frame)
    {
        try
        {
            return SwingFXUtils.toFXImage(matToBufferedImage(frame), null);
        }
        catch (Exception e)
        {
            System.err.println("Cannot convert the Mat obejct: " + e);
            return null;
        }
    }

    /**
     * Generic method for putting element running on a non-JavaFX thread on the
     * JavaFX thread, to properly update the UI
     *
     * @param property
     *            a {@link ObjectProperty}
     * @param value
     *            the value to set for the given {@link ObjectProperty}
     */
    public static <T> void onFXThread(final ObjectProperty<T> property, final T value)
    {
        Platform.runLater(() -> {
            property.set(value);
        });
    }

    public static void showError(Exception errorMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(String.valueOf(errorMessage));
        alert.showAndWait();
    }

    public static void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String fileToBase64(File file){

        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            showError(e, "Fallo eliminar jugador en BBDD");
            return "";
        }
    }

    public static BufferedImage matToBufferedImage(Mat original)
    {
        // init
        BufferedImage image = null;
        int width = original.width(), height = original.height(), channels = original.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        original.get(0, 0, sourcePixels);

        if (original.channels() > 1)
        {
            image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        }
        else
        {
            image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        return image;
    }
}
