package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.ResourceBundle;

public class CRUDPlayerController implements Initializable {
    @FXML
    private Button btnSave;
    @FXML
    private Button btnAddPhoto;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtDNI;
    @FXML
    private DatePicker dpBirthDate;
    @FXML
    private TextArea areaComments;
    @FXML
    private Label lblSelectedPhoto;
    @FXML
    private Label lblIdPlayer;
    @FXML
    private ComboBox<Team> cboTeam;
    private ObservableList<Team> teamsToChoose;

    final FileChooser fileChooser = new FileChooser();

    private String base64Encoded = null;

    private Player player;
    private PlayerQueries playerQueries;
    private TeamQueries teamQueries;
    private Boolean playerIsModified;
    private Stage dialogStage;
    private boolean saveClicked = false;

    public CRUDPlayerController() {
        this.playerQueries = new PlayerQueries();
        this.teamQueries = new TeamQueries();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamsToChoose = FXCollections.observableArrayList();

        cboTeam.setItems(teamsToChoose);
        teamsToChoose.addAll(teamQueries.getTeams());
        setButtonsStyle();
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setIdPlayer(int idPlayer){
        this.player = playerQueries.getPlayer(idPlayer);

        lblIdPlayer.setText(String.valueOf(player.getIdPlayer()));
        txtFirstName.setText(player.getFirstName());
        txtLastName.setText(player.getLastName());
        txtDNI.setText(player.getDNI());
        dpBirthDate.setValue(player.getBirthDate());
        base64Encoded = player.getPhoto();
        areaComments.setText(player.getComments());
        cboTeam.setValue(teamQueries.getTeam(player.getIdTeam()));
    }

    public void setIsModified(boolean playerIsModified){
        this.playerIsModified = playerIsModified;
    }

    public void btnSaveOnClick(){
        if(this.playerIsModified){
            btnSave.setOnAction(event -> btnUpdateOnClick());
        } else{
            btnSave.setOnAction(event -> btnCreateOnClick());
        }
    }

    public void btnCreateOnClick() {
        if (validFields()) {
            player = new Player();
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            player.setBirthDate(dpBirthDate.getValue());
            player.setHasMedicalClearance(true);
            player.setComments(areaComments.getText());
            player.setIsSuspended(false);
            player.setNumberOfSuspensionDays(null);
            player.setIdTeam(cboTeam.getValue().getIdTeam());
            player.setPhoto(base64Encoded);

            playerQueries.addPlayer(player);

            saveClicked = true;
            dialogStage.close();
        }

    }

    public void btnUpdateOnClick() {
        if (validFields()) {
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            player.setBirthDate(dpBirthDate.getValue());
            //player.setHasMedicalClearance(true);
            player.setComments(areaComments.getText());
            player.setIdTeam(cboTeam.getValue().getIdTeam());
            player.setPhoto(base64Encoded);

            playerQueries.updatePlayer(player);

            saveClicked = true;
            dialogStage.close();
        }

    }

    public void btnCancelOnAction() {
        dialogStage.close();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void btnAddPhotoOnAction(){
        File file = fileChooser.showOpenDialog(Main.stage);

        lblSelectedPhoto.setText(file.getName());

        try {
            base64Encoded = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));

        } catch (IOException e) {
            e.printStackTrace();
            lblSelectedPhoto.setText("Error cargando foto");

        }
    }

    private boolean validFields() {
        return true;
    }

    public void setButtonsStyle(){
        btnSave.setOnMouseEntered(mouseEvent -> btnSave.setStyle("-fx-background-color: #03a306;"));
        btnSave.setOnMouseExited(mouseEvent -> btnSave.setStyle("-fx-background-color: #0A2463;"));
        btnCancel.setOnMouseEntered(mouseEvent -> btnCancel.setStyle("-fx-background-color: #ed0707;"));
        btnCancel.setOnMouseExited(mouseEvent -> btnCancel.setStyle("-fx-background-color: #0A2463;"));
        btnAddPhoto.setOnMouseEntered(mouseEvent -> btnAddPhoto.setStyle("-fx-background-color: #03a306;"));
        btnAddPhoto.setOnMouseExited(mouseEvent -> btnAddPhoto.setStyle("-fx-background-color: #0A2463;"));
    }
}

