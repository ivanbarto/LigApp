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
    private Button btnCreate;
    @FXML
    private Button btnAddPhoto;
    @FXML
    private Button btnBack;
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

    final FileChooser fileChooser = new FileChooser();

    private String base64Encoded = null;

    Player player;
    PlayerQueries playerQueries;

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

    public void btnCreateOnAction() {
        if (validFields()) {
            //TODO A algunos de estos campos se le pusieron valores por defecto, ver si dejarlo asi o cambiarlo
            player = new Player();
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            //TODO ver como carajo solucionar que se carggue bien la fecha
            player.setBirthDate(dpBirthDate.getValue());
            //Al atributo Age seguramente borrarlo y calcularlo con un funcion que hay mas abajo
            //player.setAge(txtAge.getText());
            player.setHasMedicalClearance(true);
            player.setComments(areaComments.getText());
            player.setIsSuspended(false);
            player.setNumberOfSuspensionDays(null);
            //TODO ese ID cambiarlo una vez que hayamos creado la clase Team y todo eso
            player.setIdTeam(1);
            player.setPhoto(base64Encoded);

            playerQueries.addPlayer(player,false);

        }

    }



    public void btnBackOnAction() throws IOException {
        //acá le paso el boton como parametro para que el metodo me saque la scene y stage
        backToMainMenu(btnBack);
    }

    public void backToMainMenu(Button button) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("/fxml/tabs.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) button.getScene().getWindow();

        window.setScene(mainMenuScene);
        window.show();
    }


    private boolean validFields() {
        //TODO Hacer lo necesario para validar que la entrada de datos sea correcta
        return true;
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

