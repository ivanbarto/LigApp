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
import sample.utils.Utils;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamOverviewController  implements Initializable {
    @FXML
    private TableView<Team> teamTableView;
    @FXML
    private TableColumn<Team, Integer> idTeamColumn;
    @FXML
    private TableColumn<Team, String> nameColumn;
    @FXML
    private TableColumn<Team, String> shortNameColumn;
    @FXML
    private TableColumn<Team, String> managerNameColumn;
    @FXML
    private TableColumn<Team,String> managerEmailColumn;
    @FXML
    private TableColumn<Team,String> managerPhoneColumn;
    @FXML
    private TableColumn<Team, Integer> idLeagueColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label lblStatus;

    private TeamQueries teamQueries;
    private PlayerQueries playerQueries;
    private CRUDTeamController crudTeamController;
    private int selectedTeamId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamQueries = new TeamQueries();
        playerQueries = new PlayerQueries();
        crudTeamController = new CRUDTeamController();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
    }

    @FXML
    private void btnAddTeamOnClick() {
        /*Team tempTeam = new Team();*/
        //Se pone 0 para que cargue un Team nulo desde el constructor, asi el resto de los campos de add_player quedan vacíos
        boolean saveClicked = showTeamEditDialog(0);
        if (saveClicked) {
            crudTeamController.btnCreateOnClick();
        }
    }



    /*@FXML
    private void btnAddTeamOnClick(ActionEvent event) {
        try {
            Parent createTeamParent = FXMLLoader.load(getClass().getResource("/fxml/add_team.fxml"));
            Scene createTeamScene = new Scene(createTeamParent);

            Stage stage = new Stage();
            stage.setScene(createTeamScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @FXML
    private void btnDeleteTeamOnClick() {
        lblStatus.setText("Eliminando...");
        playerQueries.removeTeamPlayers(this.selectedTeamId);
        teamQueries.removeTeam(this.selectedTeamId);
        //TODO:sacar de la tabla la fila borrada, si tuvo exito la eliminacion, lo cual se peude determinar devolviendo true/false desde el metodo removeplayer. Tamblien bloquear el boton.
        lblStatus.setText("");
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
    }


    @FXML
    private void btnUpdateTeamOnClick() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            boolean saveClicked = showTeamEditDialog(this.selectedTeamId);
            if (saveClicked) {
                crudTeamController.btnUpdateOnClick();
            }

        } else {
            FxDialogs.showError("No selecciono equipo", "Debe seleccionar un equipo en la tabla");
        }
        populateTableView();
    }
/*
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            FxDialogs.showInformation("No person Selected","Please select person in the table");
        }
    }
*/

    private void populateTableView(){
        idTeamColumn.setCellValueFactory(cellData -> cellData.getValue().idTeamProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        shortNameColumn.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
        managerNameColumn.setCellValueFactory(cellData -> cellData.getValue().managerNameProperty());
        managerEmailColumn.setCellValueFactory(cellData -> cellData.getValue().managerEmailProperty());
        managerPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().managerPhoneProperty());
        idLeagueColumn.setCellValueFactory(cellData -> cellData.getValue().idLeagueProperty().asObject());


        /*idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("idTeam"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        managerNameColumn.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        idLeagueColumn.setCellValueFactory(new PropertyValueFactory<>("idLeague"));*/

        teamTableView.setItems(teamQueries.getTeams());

        teamTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                selectedTeamId = idTeamColumn.getCellData(teamTableView.getSelectionModel().getSelectedIndex());
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }
        });
    }

    public boolean showTeamEditDialog(int idTeam) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/add_team.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Agregar o editar equipo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Stage currentStage = (Stage)(teamTableView.getScene().getWindow());
            dialogStage.initOwner(currentStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CRUDTeamController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            /*controller.setTeam(team);*/
            controller.setIdTeam(idTeam);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO metodo que me diga si está o no seleccionado un equipo en la tabla
}
