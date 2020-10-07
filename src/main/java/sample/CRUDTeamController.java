package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CRUDTeamController implements Initializable {
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtShortName;
    @FXML
    private TextField txtManagerName;
    @FXML
    private TextField txtManagerEmail;
    @FXML
    private TextField txtManagerPhone;
    @FXML
    private Label lblIdTeam;

    private Team team;
    private TeamQueries teamQueries;
    private Stage dialogStage;
    private boolean saveClicked = false;
    private Boolean teamIsModified;

    public CRUDTeamController(){
        this.teamQueries = new TeamQueries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonsStyle();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setIdTeam(int idTeam) {
        this.team = teamQueries.getTeam(idTeam);

        lblIdTeam.setText(String.valueOf(team.getIdTeam()));
        txtName.setText(team.getName());
        txtShortName.setText(team.getShortName());
        txtManagerName.setText(team.getManagerName());
        txtManagerEmail.setText(team.getManagerEmail());
        txtManagerPhone.setText(team.getManagerPhone());
        //TODO ACA SIGUE FALTANDO IMPLEMENTAR LO DE LIGAS
    }

    public void setIsModified(boolean teamIsModified){
        this.teamIsModified = teamIsModified;
    }

   /* public void setTeam(Team team) {
        this.team = team;

        lblIdTeam.setText(String.valueOf(team.getIdTeam()));
        txtName.setText(team.getName());
        txtShortName.setText(team.getShortName());
        txtManagerName.setText(team.getManagerName());
        txtManagerEmail.setText(team.getManagerEmail());
        txtManagerPhone.setText(team.getManagerPhone());
    }*/

    //TODO ver como implementar eso
    public void btnSaveOnClick(){
        if(this.teamIsModified){
            btnSave.setOnAction(event -> btnUpdateOnClick());
        } else{
            btnSave.setOnAction(event -> btnCreateOnClick());
        }
    }

    @FXML
    public void btnCreateOnClick(){
        if (validFields()) {
            //TODO A algunos de estos campos se le pusieron valores por defecto, ver si dejarlo asi o cambiarlo
            team = new Team();
            team.setName(txtName.getText());
            team.setShortName(txtShortName.getText());
            //TODO ver como capturar datos de una imagen para guardarla como blob
            team.setShieldImage(null);
            team.setManagerName(txtManagerName.getText());
            team.setManagerEmail(txtManagerEmail.getText());
            team.setManagerPhone(txtManagerPhone.getText());
            //TODO ese ID cambiarlo una vez que hayamos creado la clase League y todo eso
            team.setIdLeague(1);

            teamQueries.addTeam(team);

            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void btnUpdateOnClick(){
        if (validFields()) {

            //team.setIdTeam(team.getIdTeam());
            team.setName(txtName.getText());
            team.setShortName(txtShortName.getText());
            //linea innecesaria, la puse para no olvidarme que eso es algo que falta modificar
            team.setShieldImage(team.getShieldImage());
            team.setManagerName(txtManagerName.getText());
            team.setManagerEmail(txtManagerEmail.getText());
            team.setManagerPhone(txtManagerPhone.getText());
            //linea innecesaria, la puse para no olvidarme que eso es algo que falta modificar
            team.setIdLeague(team.getIdLeague());

            teamQueries.updateTeam(team);

            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void btnCancelOnAction(){
        dialogStage.close();
    }

    private boolean validFields() {
        //TODO Hacer lo necesario para validar que la entrada de datos sea correcta
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void setButtonsStyle(){
        btnSave.setOnMouseEntered(mouseEvent -> btnSave.setStyle("-fx-background-color: #03a306;"));
        btnSave.setOnMouseExited(mouseEvent -> btnSave.setStyle("-fx-background-color: #0A2463;"));
        btnCancel.setOnMouseEntered(mouseEvent -> btnCancel.setStyle("-fx-background-color: #ed0707;"));
        btnCancel.setOnMouseExited(mouseEvent -> btnCancel.setStyle("-fx-background-color: #0A2463;"));
    }


}
