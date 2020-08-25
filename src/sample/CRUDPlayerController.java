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

    public void btnCreateOnAction(){
        if(validFields()){
            player = new Player();
            player.setFirstName(txtFirstName.getText());
            player.setLastName(txtLastName.getText());
            player.setDNI(txtDNI.getText());
            player.setBirthDate(convertedBirthDate());
            player.setAge(txtAge.getText());
            //TODO estos dos que siguen tienen esos datos por defecto, pero ver otra de hacerlo si no es buena practica
            player.setHasMedicalClearance(true);
            player.setNumberOfSuspensionDays(null);
            //TODO ese ID cambiarlo una vez que hayamos creado la clase Team y todo eso
            //player.setIdTeam(tomar el id del equipo automaticamente);
            //TODO falta seguir, toda la parte de la conexion a mysql
            showSuccessAlert();
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

    public void showSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alta de jugador");
        alert.setHeaderText(null);
        alert.setContentText("¡JUGADOR AGREGADO CON ÉXITO!");
        alert.showAndWait();
    }

    private boolean validFields(){
        //TODO Hacer lo necesario para validar que la entrada de datos sea correcta
        return true;
    }
     private Date convertedBirthDate(){
         LocalDate localDate = dpBirthDate.getValue();
         //TODO ver otro metodo de almacenar la fecha, o guardarla como string o long ya que asi está deprecated
         Date date = new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
         return date;
     }
}

