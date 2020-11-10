package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.utils.FxDialogs;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MatchOverviewController implements Initializable {
    @FXML
    private TableView<Match> matchTableView;
    @FXML
    private TableColumn<Match, Integer> idMatchColumn;
    @FXML
    private TableColumn<Match, Integer> accessCodeColumn;
    @FXML
    private TableColumn<Match, Integer> meetingColumn;
    @FXML
    private TableColumn<Match, Integer> idTeam1Column;
    @FXML
    private TableColumn<Match, Integer> idTeam2Column;
    @FXML
    private TableColumn<Match, LocalDate> dateColumn;
    @FXML
    private TableColumn<Match, String> timeColumn;
    @FXML
    private TableColumn<Match, String> stateColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRefresh;
    @FXML
    private Label lblStatus;

    private MatchQueries matchQueries;
    private TeamQueries teamQueries;
    private CRUDMatchController crudMatchController;
    private int selectedMatchId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matchQueries = new MatchQueries();
        teamQueries = new TeamQueries();
        crudMatchController = new CRUDMatchController();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
        setButtonsStyle();
    }

    @FXML
    private void btnAddMatchOnClick() {
    }

    @FXML
    private void btnDeleteMatchOnClick() {
    }

    @FXML
    private void btnUpdateMatchOnClick() {
    }

    private void populateTableView(){
        idMatchColumn.setCellValueFactory(cellData -> cellData.getValue().idMatchProperty().asObject());
        accessCodeColumn.setCellValueFactory(cellData -> cellData.getValue().accessCodeProperty().asObject());
        meetingColumn.setCellValueFactory(cellData -> cellData.getValue().meetingProperty().asObject());
        idTeam1Column.setCellValueFactory(cellData -> cellData.getValue().idTeam1Property().asObject());
        idTeam2Column.setCellValueFactory(cellData -> cellData.getValue().idTeam2Property().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());


        matchTableView.setItems(matchQueries.getMatches());

        matchTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                selectedMatchId = idMatchColumn.getCellData(matchTableView.getSelectionModel().getSelectedIndex());
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        });
    }

    @FXML
    public void btnRefreshOnAction(){
        populateTableView();
    }

    private void setButtonsStyle(){
        btnAdd.setOnMouseEntered(mouseEvent -> btnAdd.setStyle("-fx-background-color: #03a306;"));
        btnAdd.setOnMouseExited(mouseEvent -> btnAdd.setStyle("-fx-background-color: #0A2463;"));
        btnDelete.setOnMouseEntered(mouseEvent -> btnDelete.setStyle("-fx-background-color: #ed0707;"));
        btnDelete.setOnMouseExited(mouseEvent -> btnDelete.setStyle("-fx-background-color: #0A2463;"));
        btnUpdate.setOnMouseEntered(mouseEvent -> btnUpdate.setStyle("-fx-background-color: #2c4dde;"));
        btnUpdate.setOnMouseExited(mouseEvent -> btnUpdate.setStyle("-fx-background-color: #0A2463;"));
        btnRefresh.setOnMouseEntered(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/flatastic-8/24/Refresh-icon.png)"));
        btnRefresh.setOnMouseExited(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/mono-general-4/24/refresh-icon.png)"));
    }
}
