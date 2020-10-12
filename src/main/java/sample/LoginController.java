package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.primaryStage;

public class LoginController implements Initializable {
    @FXML
    private Button btnSignIn;

    @FXML
    private PasswordField etPassword;
    @FXML
    private TextField etUsername;
    @FXML
    private Label txtError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonsStyle();

    }

    public void onLogin(MouseEvent event){
        LoginQueries loginQueries = new LoginQueries();

        boolean connectionIsSuccessful = loginQueries.logIn(etUsername.getText(), etPassword.getText());

        if(connectionIsSuccessful){
            initMainScreen();
        }else{
            txtError.setText("Nombre de usuario o contraseña incorrectos");
        }

    }

    public void removeAlert(MouseEvent event){
        txtError.setText("");
    }

    public void setButtonsStyle(){
        btnSignIn.setOnMouseEntered(mouseEvent -> btnSignIn.setStyle("-fx-background-color: #44459e;"));
        btnSignIn.setOnMouseExited(mouseEvent -> btnSignIn.setStyle("-fx-background-color: #0A2463;"));
    }

    private void initMainScreen() {
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
}
