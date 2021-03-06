package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    public BorderPane rootLayout;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMatches;
    @FXML
    private Button btnTeams;
    @FXML
    private Button btnPlayers;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSuspensions;
    @FXML
    private Button btnClose;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showHomeScreen();
        setButtonsStyle();
    }

    @FXML
    public void btnHomeOnClick(){
        showHomeScreen();
    }

    @FXML
    public void btnTeamsOnClick(){
        showTeamOverview();
    }

    @FXML
    public void btnPlayersOnClick(){
        showPlayerOverview();
    }

    @FXML
    public void btnMatchesOnClick(){ showMatchOverview(); }

    @FXML
    public void btnSuspensionsOnClick(){ showSuspensionOverview(); }

    @FXML
    public void btnCloseOnClick(){
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

    public void showMatchOverview(){
        Parent matchOverview = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/match_overview.fxml"));
            matchOverview = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.setCenter(matchOverview);
    }

    public void showSuspensionOverview(){
        Parent matchOverview = null;
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/suspension_overview.fxml"));
            matchOverview = loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayout.setCenter(matchOverview);
    }



    public void setButtonsStyle(){
        btnHome.setOnMouseEntered(mouseEvent -> btnHome.setStyle("-fx-background-color: #44459e;"));
        btnHome.setOnMouseExited(mouseEvent -> btnHome.setStyle("-fx-background-color: #0A2463;"));
        btnMatches.setOnMouseEntered(mouseEvent -> btnMatches.setStyle("-fx-background-color: #44459e;"));
        btnMatches.setOnMouseExited(mouseEvent -> btnMatches.setStyle("-fx-background-color: #0A2463;"));
        btnTeams.setOnMouseEntered(mouseEvent -> btnTeams.setStyle("-fx-background-color: #44459e;"));
        btnTeams.setOnMouseExited(mouseEvent -> btnTeams.setStyle("-fx-background-color: #0A2463;"));
        btnPlayers.setOnMouseEntered(mouseEvent -> btnPlayers.setStyle("-fx-background-color: #44459e;"));
        btnPlayers.setOnMouseExited(mouseEvent -> btnPlayers.setStyle("-fx-background-color: #0A2463;"));
        btnSettings.setOnMouseEntered(mouseEvent -> btnSettings.setStyle("-fx-background-color: #44459e;"));
        btnSettings.setOnMouseExited(mouseEvent -> btnSettings.setStyle("-fx-background-color: #0A2463;"));
        btnSuspensions.setOnMouseEntered(mouseEvent -> btnSuspensions.setStyle("-fx-background-color: #44459e;"));
        btnSuspensions.setOnMouseExited(mouseEvent -> btnSuspensions.setStyle("-fx-background-color: #0A2463;"));
        btnClose.setOnMouseEntered(mouseEvent -> btnClose.setStyle("-fx-background-color: #c41212;"));
        btnClose.setOnMouseExited(mouseEvent -> btnClose.setStyle("-fx-background-color: #0A2463;"));
    }
}
