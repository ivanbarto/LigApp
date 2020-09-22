package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PlayerOverviewController implements Initializable {
    @FXML
    private Button btnAdd;

    private Label label;
    @FXML
    private TableView<Player> playerTableView;
    @FXML
    private TableColumn<Player, Integer> idPlayerColumn;
    @FXML
    private TableColumn<Player, String> firstNameColumn;
    @FXML
    private TableColumn<Player, String> lastNameColumn;
    @FXML
    private TableColumn<Player, String> dniColumn;
    @FXML
    private TableColumn<Player, Integer> idTeamColumn;
    @FXML
    private TableColumn colEdit;

    PlayerQueries playerQueries;
    ObservableList<Player> players;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerQueries = new PlayerQueries();
        populateTableView();
    }

    private void populateTableView() {
        idPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("idPlayer"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idTeam"));

        playerTableView.setItems(playerQueries.getPlayers());
    }


    public void btnAddOnAction(ActionEvent event) throws IOException {
        Parent createPlayerParent = FXMLLoader.load(getClass().getResource("/fxml/add_player.fxml"));
        Scene createPlayerScene = new Scene(createPlayerParent);

        Stage stage = new Stage();
        stage.setScene(createPlayerScene);
        stage.show();


    }


}
