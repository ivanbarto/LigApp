package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.utils.FxDialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PlayerOverviewController implements Initializable {
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Player> playerTableView;
    @FXML
    private TableColumn<Player, Integer> idPlayerColumn;
    @FXML
    private TableColumn<Player, String> firstNameColumn;
    @FXML
    private TableColumn<Player, String> lastNameColumn;
    @FXML
    private TableColumn<Player, String> dniColumn;
    @FXML
    private TableColumn<Player, String> commentsColumn;
    @FXML
    private TableColumn<Player, Integer> idTeamColumn;
    @FXML
    private Label lblStatus;
    @FXML
    private TableColumn colEdit;

    private int selectedPlayerId;

    private PlayerQueries playerQueries;
    private CRUDPlayerController crudPlayerController;

    //ObservableList<Player> playersInTeam = playerQueries.getPlayers();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerQueries = new PlayerQueries();
        crudPlayerController = new CRUDPlayerController();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
    }

    @FXML
    private void btnAddPlayerOnclick(ActionEvent event) {
        /*Player tempPlayer = new Player();*/
        //Se pone 0 para que cargue un Player nulo desde el constructor, asi el resto de los campos de add_player quedan vacíos
        boolean saveClicked = showPlayerEditDialog(0, false);
        if (saveClicked) {
            crudPlayerController.btnCreateOnClick();
        }
        populateTableView();
    }

    /*
    * lblStatus.setText("Eliminando...");
        playerQueries.removeTeamPlayers(this.selectedTeamId);
        teamQueries.removeTeam(this.selectedTeamId);
        //TODO:sacar de la tabla la fila borrada, si tuvo exito la eliminacion, lo cual se peude determinar devolviendo true/false desde el metodo removeplayer. Tamblien bloquear el boton.
        lblStatus.setText("");
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
    * */

    @FXML
    private void btnDeletePlayerOnClick(ActionEvent event) {
        lblStatus.setText("Eliminando...");
        playerQueries.removePlayer(this.selectedPlayerId);
        //TODO:sacar de la tabla la fila borrada, si tuvo exito la eliminacion, lo cual se peude determinar devolviendo true/false desde el metodo removeplayer. Tamblien bloquear el boton.
        lblStatus.setText("");
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
    }

    @FXML
    private void btnUpdatePlayerOnclick(ActionEvent event) {
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            boolean saveClicked = showPlayerEditDialog(this.selectedPlayerId, true);
            if(saveClicked){
                crudPlayerController.btnUpdateOnClick();
            }
        } else{
            FxDialogs.showError("No selecciono jugador", "Debe seleccionar un jugador en la tabla");
        }
        populateTableView();
    }

    private void populateTableView() {
        idPlayerColumn.setCellValueFactory(cellData -> cellData.getValue().idPlayerProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        dniColumn.setCellValueFactory(cellData -> cellData.getValue().DNIProperty());
        commentsColumn.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
        idTeamColumn.setCellValueFactory(cellData -> cellData.getValue().idTeamProperty().asObject());

        /*
        idPlayerColumn.setCellValueFactory(new PropertyValueFactory<>("idPlayer"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idTeam"));
         */

        playerTableView.setItems(playerQueries.getPlayers());

        playerTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                selectedPlayerId = idPlayerColumn.getCellData(playerTableView.getSelectionModel().getSelectedIndex());
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        });
    }

    public boolean showPlayerEditDialog(int idPlayer, boolean playerIsModified) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/add_player.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Agregar o editar jugador");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Stage currentStage = (Stage)(playerTableView.getScene().getWindow());
            dialogStage.initOwner(currentStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CRUDPlayerController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            /*controller.setPlayer(player);*/
            controller.setIdPlayer(idPlayer);
            controller.setIsModified(playerIsModified);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
