package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreatePlayer;
    @FXML
    private Button btnCreateTeam;

    public void btnCancelOnAction(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    //este metodo abre el formulario de alta jugador al tocar el boton
    public void btnCreatePlayerOnAction(ActionEvent event) throws IOException {
        //TODO El fxml de abajo luego cambiarlo cuando hagamos el CRUD, por ahora solo tiene add
        Parent createPlayerParent = FXMLLoader.load(getClass().getResource("/fxml/add_player.fxml"));
        Scene createPlayerScene = new Scene(createPlayerParent);

        //Esta linea toma la informacion del stage
        //Sacamos la scene de donde viene el boton, y a ese su respectiva stage.
        Stage window = (Stage) btnCreatePlayer.getScene().getWindow();

        window.setScene(createPlayerScene);
        window.show();
    }

    public void btnCreateTeamOnAction(ActionEvent event) throws IOException {
        //TODO El fxml de abajo luego cambiarlo cuando hagamos el CRUD, por ahora solo tiene add
        Parent createPlayerParent = FXMLLoader.load(getClass().getResource("/fxml/add_team.fxml"));
        Scene createPlayerScene = new Scene(createPlayerParent);

        Stage window = (Stage) btnCreateTeam.getScene().getWindow();

        window.setScene(createPlayerScene);
        window.show();
    }


}

