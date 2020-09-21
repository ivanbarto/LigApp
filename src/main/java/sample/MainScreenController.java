package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private BorderPane rootLayout;
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
        showHomeScreen();
    }


    @FXML
    public void btnHomeOnClick(ActionEvent event){
        showHomeScreen();
    }

    @FXML
    public void btnTeamsOnClick(ActionEvent event){
        showTeamOverview();
    }

    @FXML
    public void btnPlayersOnClick(ActionEvent event){
        showPlayerOverview();
    }

    @FXML
    public void btnCloseOnClick(ActionEvent event){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    public void showHomeScreen(){
        Parent homeScreen = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/homeScreen.fxml"));
            homeScreen = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.setCenter(homeScreen);
    }

    public void showTeamOverview(){
        Parent teamOverview = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/team_overview.fxml"));
            teamOverview = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.setCenter(teamOverview);
    }

    public void showPlayerOverview(){
        Parent playerOverview = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/player_overview.fxml"));
            playerOverview = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.setCenter(playerOverview);
    }
}
