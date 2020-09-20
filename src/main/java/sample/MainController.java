package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btnHome;
    @FXML
    private Button btnLeagues;
    @FXML
    private Button btnTeams;
    @FXML
    private Button btnPlayers;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnTrackFace;
    @FXML
    private Button btnClose;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void btnHomeOnClick(ActionEvent event){

    }

    @FXML
    public void btnPlayersOnClick(ActionEvent event){

    }

    @FXML
    public void btnCloseOnClick(ActionEvent event){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
