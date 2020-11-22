package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.utils.FxDialogs;

import java.io.IOException;
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
    private Button btnSuspendPlayer1;
    @FXML
    private Button btnSuspendPlayer2;
    @FXML
    private Button btnEndMatch;
    @FXML
    private ComboBox<Match> cboMatches;

    private MatchQueries matchQueries;
    private TeamQueries teamQueries;
    private PlayerQueries playerQueries;
    private ObservableList<Match> playedMatchesToChoose;
    private int selectedPlayerIdTeam1;
    private int selectedPlayerIdTeam2;
    private int selectedMatchId;

    public SuspensionOverviewController(){
        this.matchQueries = new MatchQueries();
        this.teamQueries = new TeamQueries();
        this.playerQueries = new PlayerQueries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTableView();
        setButtonsStyle();

        playedMatchesToChoose = FXCollections.observableArrayList();

        cboMatches.setItems(playedMatchesToChoose);
        playedMatchesToChoose.addAll(matchQueries.getPlayedMatches());
        btnSuspendPlayer1.setDisable(true);
        btnSuspendPlayer2.setDisable(true);
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

        Match match = cboMatches.getValue();

        if(match==null){
            lblAccessCode.setText("");
            lblDate.setText("");
            lblMeeting.setText("");
            lblState.setText("");
            lblTime.setText("");
            lblTeam1.setText("Equipo:");
            lblTeam2.setText("Equipo:");

        }else{
            this.selectedMatchId = match.getIdMatch();
            lblAccessCode.setText(String.valueOf(match.getAccessCode()));
            lblDate.setText(String.valueOf(match.getDate()));
            lblMeeting.setText(String.valueOf(match.getMeeting()));
            lblState.setText(match.getState());
            lblTime.setText(match.getTime());
            lblTeam1.setText("Equipo: "+teamQueries.getTeam(match.getIdTeam1()).getName());
            lblTeam2.setText("Equipo: "+teamQueries.getTeam(match.getIdTeam2()).getName());

            playerTableView1.setItems(playerQueries.getTeamPlayers(match.getIdTeam1()));
            playerTableView2.setItems(playerQueries.getTeamPlayers(match.getIdTeam2()));
        }


        playerTableView1.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                btnSuspendPlayer1.setDisable(false);
                selectedPlayerIdTeam1 = idPlayerColumn1.getCellData(playerTableView1.getSelectionModel().getSelectedIndex());
            }
        });

        playerTableView2.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                btnSuspendPlayer2.setDisable(false);
                selectedPlayerIdTeam2 = idPlayerColumn2.getCellData(playerTableView2.getSelectionModel().getSelectedIndex());
            }
        });
    }

    @FXML
    public void btnSuspendPlayer1OnClick(){
        int numberOfSuspensionDays = Integer.parseInt(FxDialogs.showTextInput("SUSPENDER JUGADOR","Ingrese la cantidad de fechas de suspensión que quiere dar al jugador","1"));
        playerQueries.suspendPlayer(numberOfSuspensionDays,this.selectedPlayerIdTeam1);
    }

    @FXML
    public void btnSuspendPlayer2OnClick(){
        int numberOfSuspensionDays = Integer.parseInt(FxDialogs.showTextInput("SUSPENDER JUGADOR","Ingrese la cantidad de fechas de suspensión que quiere dar al jugador","1"));
        playerQueries.suspendPlayer(numberOfSuspensionDays,this.selectedPlayerIdTeam2);
    }

    @FXML
    public void btnEndMatchOnClick(){
        String choice = FxDialogs.showConfirm("Confirmar elección", "¿Está seguro que desea finalizar el partido?");
        if(choice.equals("OK")){
            matchQueries.endMatch("Finalizado", this.selectedMatchId);
        }
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
        btnSuspendPlayer1.setOnMouseEntered(mouseEvent -> btnSuspendPlayer1.setStyle("-fx-background-color: #ed0707;"));
        btnSuspendPlayer1.setOnMouseExited(mouseEvent -> btnSuspendPlayer1.setStyle("-fx-background-color: #0A2463;"));
        btnSuspendPlayer2.setOnMouseEntered(mouseEvent -> btnSuspendPlayer2.setStyle("-fx-background-color: #ed0707;"));
        btnSuspendPlayer2.setOnMouseExited(mouseEvent -> btnSuspendPlayer2.setStyle("-fx-background-color: #0A2463;"));
        btnEndMatch.setOnMouseEntered(mouseEvent -> btnEndMatch.setStyle("-fx-background-color: #2c4dde;"));
        btnEndMatch.setOnMouseExited(mouseEvent -> btnEndMatch.setStyle("-fx-background-color: #0A2463;"));
        btnRefresh.setOnMouseEntered(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/flatastic-8/24/Refresh-icon.png)"));
        btnRefresh.setOnMouseExited(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/mono-general-4/24/refresh-icon.png)"));
    }

    @FXML
    public void btnRefreshOnAction(){
        populateTableView();
    }
}
