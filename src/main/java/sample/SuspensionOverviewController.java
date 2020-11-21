package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class SuspensionOverviewController implements Initializable {
    @FXML
    private TableView<Player> playerTableView1;
    @FXML
    private TableColumn<Player, Integer> idPlayerColumn1;
    @FXML
    private TableColumn<Player, String> firstNameColumn1;
    @FXML
    private TableColumn<Player, String> lastNameColumn1;
    @FXML
    private TableColumn<Player, Boolean> isSuspendedColumn1;
    @FXML
    private TableColumn<Player, String> numberOfDaysColumn1;
    @FXML
    private TableView<Player> playerTableView2;
    @FXML
    private TableColumn<Player, Integer> idPlayerColumn2;
    @FXML
    private TableColumn<Player, String> firstNameColumn2;
    @FXML
    private TableColumn<Player, String> lastNameColumn2;
    @FXML
    private TableColumn<Player, Boolean> isSuspendedColumn2;
    @FXML
    private TableColumn<Player, String> numberOfDaysColumn2;
    @FXML
    private Button btnRefresh;
    @FXML
    private Label lblAccessCode;
    @FXML
    private Label lblMeeting;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblState;
    @FXML
    private Label lblTeam1;
    @FXML
    private Label lblTeam2;
    @FXML
    private Button btnSuspendPlayer;
    @FXML
    private Button btnEndMatch;

    private MatchQueries matchQueries;
    private TeamQueries teamQueries;
    private PlayerQueries playerQueries;

    public SuspensionOverviewController(){
        this.matchQueries = new MatchQueries();
        this.teamQueries = new TeamQueries();
        this.playerQueries = new PlayerQueries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTableView();
        setButtonsStyle();
    }





    private void populateTableView() {
        idPlayerColumn1.setCellValueFactory(cellData -> cellData.getValue().idPlayerProperty().asObject());
        firstNameColumn1.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn1.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        isSuspendedColumn1.setCellValueFactory(cellData -> cellData.getValue().isSuspendedProperty());
        numberOfDaysColumn1.setCellValueFactory(cellData -> cellData.getValue().numberOfSuspensionDaysProperty());

        idPlayerColumn2.setCellValueFactory(cellData -> cellData.getValue().idPlayerProperty().asObject());
        firstNameColumn2.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn2.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        isSuspendedColumn2.setCellValueFactory(cellData -> cellData.getValue().isSuspendedProperty());
        numberOfDaysColumn2.setCellValueFactory(cellData -> cellData.getValue().numberOfSuspensionDaysProperty());

        formatTableRows();

        playerTableView1.setItems(playerQueries.getTeamPlayers(1/*cboTeam.getValue().getIdTeam())*/));
        playerTableView2.setItems(playerQueries.getTeamPlayers(10/*cboTeam.getValue().getIdTeam())*/));

        playerTableView1.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                /*selectedPlayerId = idPlayerColumn.getCellData(playerTableView.getSelectionModel().getSelectedIndex());
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);*/
            }
        });

        playerTableView1.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                /*selectedPlayerId = idPlayerColumn.getCellData(playerTableView.getSelectionModel().getSelectedIndex());
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);*/
            }
        });
    }

    private void formatTableRows(){
        isSuspendedColumn1.setCellFactory(column -> new TableCell<Player,Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item, empty);

                String suspendedStyle = "-fx-background-color: red; -fx-alignment: CENTER";
                String non_suspendedStyle = "-fx-background-color: green; -fx-alignment: CENTER";

                //Me pone el texto en "SI" y color rojo si el booleano es true
                setText(empty ? null : item.booleanValue() ? "SI" : "NO");
                setStyle(empty ? null : item.booleanValue()? suspendedStyle : non_suspendedStyle);

            }
        });
        isSuspendedColumn2.setCellFactory(column -> new TableCell<Player,Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item, empty);

                String suspendedStyle = "-fx-background-color: red; -fx-alignment: CENTER";
                String non_suspendedStyle = "-fx-background-color: green; -fx-alignment: CENTER";

                //Me pone el texto en "SI" y color rojo si el booleano es true
                setText(empty ? null : item.booleanValue() ? "SI" : "NO");
                setStyle(empty ? null : item.booleanValue()? suspendedStyle : non_suspendedStyle);

            }
        });
    }

    public void setButtonsStyle() {
        btnSuspendPlayer.setOnMouseEntered(mouseEvent -> btnSuspendPlayer.setStyle("-fx-background-color: #ed0707;"));
        btnSuspendPlayer.setOnMouseExited(mouseEvent -> btnSuspendPlayer.setStyle("-fx-background-color: #0A2463;"));
        btnEndMatch.setOnMouseEntered(mouseEvent -> btnEndMatch.setStyle("-fx-background-color: #2c4dde;"));
        btnEndMatch.setOnMouseExited(mouseEvent -> btnEndMatch.setStyle("-fx-background-color: #0A2463;"));
        btnRefresh.setOnMouseEntered(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/flatastic-8/24/Refresh-icon.png)"));
        btnRefresh.setOnMouseExited(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/mono-general-4/24/refresh-icon.png)"));
    }
}
