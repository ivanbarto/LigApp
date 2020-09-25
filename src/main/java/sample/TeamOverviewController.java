package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamOverviewController  implements Initializable {
    private Label label;
    @FXML
    private TableView<Team> teamTableView;
    @FXML
    private TableColumn<Team, Integer> idTeamColumn;
    @FXML
    private TableColumn<Team, String> nameColumn;
    @FXML
    private TableColumn<Team, String> shortNameColumn;
    @FXML
    private TableColumn<Team, String> managerNameColumn;
    @FXML
    private TableColumn<Team, Integer> idLeagueColumn;

    TeamQueries teamQueries;
    ObservableList<Team> teams;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamQueries = new TeamQueries();
        populateTableView();
    }

    @FXML
    private void btnAddTeamOnClick(ActionEvent event) {
        try {
            Parent createTeamParent = FXMLLoader.load(getClass().getResource("/fxml/add_team.fxml"));
            Scene createTeamScene = new Scene(createTeamParent);

            Stage stage = new Stage();
            stage.setScene(createTeamScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteTeamOnClick(ActionEvent event) {
        //teamQueries.removeTeam(this.selectedPlayerId, false);
        //TODO:sacar de la tabla la fila borrada, si tuvo exito la eliminacion, lo cual se peude determinar devolviendo true/false desde el metodo removeplayer. Tamblien bloquear el boton.
        //btnDelete.setDisable(true);
    }


    @FXML
    private void btnUpdateTeamOnClick(ActionEvent event) {

    }


    private void populateTableView(){
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idTeam"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        idLeagueColumn.setCellValueFactory(new PropertyValueFactory<>("idLeague"));

        teamTableView.setItems(teamQueries.getTeams());
    }

    //TODO metodo que me diga si está o no seleccionado un equipo en la tabla

}
