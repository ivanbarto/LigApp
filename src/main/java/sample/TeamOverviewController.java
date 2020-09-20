package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Team,String> shortNameColumn;
    @FXML
    private TableColumn<Team,String> managerNameColumn;
    @FXML
    private TableColumn<Team,Integer> idLeagueColumn;

    TeamQueries teamQueries;
    ObservableList<Team> teams;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamQueries = new TeamQueries();
        populateTableView();
    }

    private void populateTableView(){
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idTeam"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        idLeagueColumn.setCellValueFactory(new PropertyValueFactory<>("idLeague"));

        teamTableView.setItems(teamQueries.getTeams());
    }
}
