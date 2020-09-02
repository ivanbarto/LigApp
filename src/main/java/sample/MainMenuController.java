package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController{
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreatePlayer;
    @FXML
    private Button btnCreateTeam;
    @FXML
    private TableView<Player> tbvPlayer;
    @FXML
    private TableView<Team> tbvTeam;
    @FXML
    private TableColumn<Player, Integer> idPlayerColumn;
    @FXML
    private TableColumn<Player, String> firstNameColumn ;
    @FXML
    private TableColumn<Player, String> lastNameColumn;
    @FXML
    private TableColumn<Player, String> dniColumn;
    @FXML
    private TableColumn<Player, String> ageColumn;
    @FXML
    private TableColumn<Player, Integer> idTeamColumn;



    PlayerQueries playerQueries;

    public MainMenuController() {
        playerQueries = new PlayerQueries();
        fillTable();



    }

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

    public void fillTable(){
        tbvPlayer = new TableView<>();
        idPlayerColumn = new TableColumn<>();
        firstNameColumn = new TableColumn<>();
        lastNameColumn = new TableColumn<>();
        dniColumn = new TableColumn<>();
        ageColumn = new TableColumn<>();
        idTeamColumn = new TableColumn<>();
        idPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("idPlayer"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idTeam"));
        tbvPlayer.setItems(playerQueries.getPlayers());
        tbvPlayer.getColumns().add(idPlayerColumn);
        tbvPlayer.getColumns().add(firstNameColumn);
        tbvPlayer.getColumns().add(lastNameColumn);
        tbvPlayer.getColumns().add(dniColumn);
        tbvPlayer.getColumns().add(ageColumn);
        tbvPlayer.getColumns().add(idTeamColumn);
    }
}

