package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class CRUDPlayerController {
    @FXML
    private Button btnCreate;
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
    private TextField txtAge;
    @FXML
    private TextArea areaComments;

    Player player;
    PlayerQueries playerQueries;

    public CRUDPlayerController() {
        this.playerQueries = new PlayerQueries();
    }

    public void btnCreateOnAction() {
        if (validFields()) {
            //TODO A algunos de estos campos se le pusieron valores por defecto, ver si dejarlo asi o cambiarlo
            player = new Player();
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            player.setBirthDate(convertedBirthDate());
            //Al atributo Age seguramente borrarlo y calcularlo con un funcion que hay mas abajo
            player.setAge(txtAge.getText());
            player.setHasMedicalClearance(true);
            player.setComments(areaComments.getText());
            player.setSuspended(false);
            player.setNumberOfSuspensionDays(null);
            //TODO ese ID cambiarlo una vez que hayamos creado la clase Team y todo eso
            player.setIdTeam(1);
            //TODO falta seguir, toda la parte de la conexion a mysql

            playerQueries.addPlayer(player);

        }

    }

    public void btnBackOnAction() throws IOException {
        //acá le paso el boton como parametro para que el metodo me saque la scene y stage
        backToMainMenu(btnBack);
    }

    public void backToMainMenu(Button button) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
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

