package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;

public class CRUDPlayerController {
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

    final FileChooser fileChooser = new FileChooser();

    private String base64Encoded = null;

    private Player player;
    private PlayerQueries playerQueries;
    private Stage dialogStage;
    private boolean saveClicked = false;

    public CRUDPlayerController() {
        this.playerQueries = new PlayerQueries();
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

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /*public void setPlayer(Player player){
        this.player = player;

        lblIdPlayer.setText(String.valueOf(player.getIdPlayer()));
        txtFirstName.setText(player.getFirstName());
        txtLastName.setText(player.getLastName());
        txtDNI.setText(player.getDNI());
    }*/

    public void setIdPlayer(int idPlayer){
        this.player = playerQueries.getPlayer(idPlayer);

        lblIdPlayer.setText(String.valueOf(player.getIdPlayer()));
        txtFirstName.setText(player.getFirstName());
        txtLastName.setText(player.getLastName());
        txtDNI.setText(player.getDNI());
        areaComments.setText(player.getComments());
    }

    //TODO ver como implementar eso
    public void btnSaveOnClick(boolean teamIsModified){
        if(teamIsModified){
            btnUpdateOnClick();
        } else{
            btnCreateOnClick();
        }
    }

    public void btnCreateOnClick() {
        if (validFields()) {
            //TODO A algunos de estos campos se le pusieron valores por defecto, ver si dejarlo asi o cambiarlo
            player = new Player();
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            //TODO ver como carajo solucionar que se carggue bien la fecha
            player.setBirthDate(dpBirthDate.getValue());
            player.setHasMedicalClearance(true);
            player.setComments(areaComments.getText());
            player.setIsSuspended(false);
            player.setNumberOfSuspensionDays(null);
            //TODO ese ID cambiarlo una vez que hayamos creado la clase Team y todo eso
            player.setIdTeam(1);
            player.setPhoto(base64Encoded);

            playerQueries.addPlayer(player);

            saveClicked = true;
            dialogStage.close();
        }

    }

    public void btnUpdateOnClick() {
        if (validFields()) {
            /*//TODO A algunos de estos campos se le pusieron valores por defecto, ver si dejarlo asi o cambiarlo
            player.setIdPlayer(Integer.parseInt(lblIdPlayer.getText()));
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            //TODO ver como carajo solucionar que se carggue bien la fecha
            player.setBirthDate(dpBirthDate.getValue());
            player.setHasMedicalClearance(true);
            player.setComments(areaComments.getText());
            player.setIsSuspended(false);
            player.setNumberOfSuspensionDays(null);
            //TODO ese ID cambiarlo una vez que hayamos creado la clase Team y todo eso
            player.setIdTeam(1);
            player.setPhoto(base64Encoded);

            playerQueries.addPlayer(player);

            saveClicked = true;
            dialogStage.close();*/
        }

    }

    public void btnCancelOnAction() {
        dialogStage.close();
    }

    private boolean validFields() {
        //TODO Hacer lo necesario para validar que la entrada de datos sea correcta
        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    private Date convertedBirthDate() {
        LocalDate localDate = dpBirthDate.getValue();
        //TODO ver otro metodo de almacenar la fecha, o guardarla como string o long ya que asi está deprecated
        return new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    //TODO esto es para poder sacar la edad del tipo para permitirle o no estar en la liga, verlo
    /*public static String calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return String.valueOf(Period.between(birthDate, currentDate).getYears());
        } else {
            return null;
        }
    }*/
}

