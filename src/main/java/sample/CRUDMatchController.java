package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CRUDMatchController implements Initializable {
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnGenerateCode;
    @FXML
    private TextField txtAccessCode;
    @FXML
    private TextField txtMeeting;
    @FXML
    private ComboBox<Team> cboTeam1;
    @FXML
    private ComboBox<Team> cboTeam2;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField txtTime;
    @FXML
    private ComboBox<String> cboState;
    @FXML
    private Label lblIdMatch;

    private ObservableList<Team> teamsToChoose;
    private ObservableList<String> statesToChoose;

    private Match match;
    private MatchQueries matchQueries;
    private TeamQueries teamQueries;
    private Boolean matchIsModified;
    private Stage dialogStage;
    private boolean saveClicked = false;

    public CRUDMatchController(){
        this.matchQueries = new MatchQueries();
        this.teamQueries = new TeamQueries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamsToChoose = FXCollections.observableArrayList();
        statesToChoose = FXCollections.observableArrayList();

        cboTeam1.setItems(teamsToChoose);
        cboTeam2.setItems(teamsToChoose);
        cboState.setItems(statesToChoose);

        teamsToChoose.addAll(teamQueries.getTeams());
        statesToChoose.addAll("Creado","Jugado","Finalizado");
        //setButtonsStyle();
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setIdMatch(int idMatch){
        this.match = matchQueries.getMatch(idMatch);

        lblIdMatch.setText(String.valueOf(match.getIdMatch()));
        txtAccessCode.setText(String.valueOf(match.getAccessCode()));
        txtMeeting.setText(String.valueOf(match.getMeeting()));
        cboTeam1.setValue(teamQueries.getTeam(match.getIdTeam1()));
        cboTeam2.setValue(teamQueries.getTeam(match.getIdTeam2()));
        dpDate.setValue(match.getDate());
        txtTime.setText(match.getTime());
        cboState.setValue(match.getState());

    }

    public void setIsModified(boolean matchIsModified){
        this.matchIsModified = matchIsModified;
    }

    public void btnSaveOnClick(){
        if(this.matchIsModified){
            btnSave.setOnAction(event -> btnUpdateOnClick());
        } else{
            btnSave.setOnAction(event -> btnCreateOnClick());
        }
    }

    public void btnCreateOnClick() {
        if (validFields()) {
            match = new Match();
            match.setAccessCode(Integer.parseInt(txtAccessCode.getText()));
            match.setMeeting(Integer.parseInt(txtMeeting.getText()));
            match.setIdTeam1(cboTeam1.getValue().getIdTeam());
            match.setIdTeam2(cboTeam2.getValue().getIdTeam());
            match.setDate(dpDate.getValue());
            match.setTime(txtTime.getText());
            match.setState(cboState.getValue());

            matchQueries.addMatch(match);

            saveClicked = true;
            dialogStage.close();
        }
    }

    public void btnUpdateOnClick() {
        if (validFields()) {
            match.setAccessCode(Integer.parseInt(txtAccessCode.getText()));
            match.setMeeting(Integer.parseInt(txtMeeting.getText()));
            match.setIdTeam1(cboTeam1.getValue().getIdTeam());
            match.setIdTeam2(cboTeam2.getValue().getIdTeam());
            match.setDate(dpDate.getValue());
            match.setTime(txtTime.getText());
            match.setState(cboState.getValue());

            matchQueries.updateMatch(match);

            saveClicked = true;
            dialogStage.close();
        }

    }

    public void btnCancelOnAction() {
        dialogStage.close();
    }

    public void btnGenerateCodeOnClick(){
        Random randomAccesCode = new Random();
        int accesCode = randomAccesCode.nextInt(999999);
        txtAccessCode.setText(String.valueOf(accesCode));
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private boolean validFields() {
        return true;
    }

    public void setButtonsStyle(){
        btnSave.setOnMouseEntered(mouseEvent -> btnSave.setStyle("-fx-background-color: #03a306;"));
        btnSave.setOnMouseExited(mouseEvent -> btnSave.setStyle("-fx-background-color: #0A2463;"));
        btnCancel.setOnMouseEntered(mouseEvent -> btnCancel.setStyle("-fx-background-color: #ed0707;"));
        btnCancel.setOnMouseExited(mouseEvent -> btnCancel.setStyle("-fx-background-color: #0A2463;"));
        btnGenerateCode.setOnMouseEntered(mouseEvent -> btnGenerateCode.setStyle("-fx-background-color: #03a306;"));
        btnGenerateCode.setOnMouseExited(mouseEvent -> btnGenerateCode.setStyle("-fx-background-color: #0A2463;"));
    }

}
