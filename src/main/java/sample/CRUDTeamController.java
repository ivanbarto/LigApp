package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CRUDTeamController {
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnBack;
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

    private Team team;
    private TeamQueries teamQueries;

    public CRUDTeamController(){
        this.teamQueries = new TeamQueries(); }

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
        }
    }

    public void btnBackOnAction() throws IOException {
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
}
