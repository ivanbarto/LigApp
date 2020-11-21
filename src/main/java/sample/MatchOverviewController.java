package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.utils.FxDialogs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MatchOverviewController implements Initializable {
    @FXML
    private TableView<Match> matchTableView;
    @FXML
    private TableColumn<Match, Integer> idMatchColumn;
    @FXML
    private TableColumn<Match, Integer> accessCodeColumn;
    @FXML
    private TableColumn<Match, Integer> meetingColumn;
    @FXML
    private TableColumn<Match, Integer> idTeam1Column;
    @FXML
    private TableColumn<Match, Integer> idTeam2Column;
    @FXML
    private TableColumn<Match, LocalDate> dateColumn;
    @FXML
    private TableColumn<Match, String> timeColumn;
    @FXML
    private TableColumn<Match, String> stateColumn;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnManageSuspensions;
    @FXML
    private Label lblStatus;

    private MainScreenController mainScreenController;
    private MatchQueries matchQueries;
    private TeamQueries teamQueries;
    private CRUDMatchController crudMatchController;
    private int selectedMatchId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matchQueries = new MatchQueries();
        teamQueries = new TeamQueries();
        crudMatchController = new CRUDMatchController();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnManageSuspensions.setDisable(true);
        populateTableView();
        setButtonsStyle();
    }

    @FXML
    private void btnAddMatchOnClick() {
        boolean saveClicked = showMatchEditDialog(0, false);
        if (saveClicked) {
            crudMatchController.btnCreateOnClick();
        }
        populateTableView();
    }

    @FXML
    private void btnDeleteMatchOnClick() {
        lblStatus.setText("Eliminando...");
        matchQueries.removeMatch(this.selectedMatchId);
        lblStatus.setText("");
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        populateTableView();
    }

    @FXML
    private void btnUpdateMatchOnClick() {
        Match selectedMatch = matchTableView.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            boolean saveClicked = showMatchEditDialog(this.selectedMatchId, true);
            if (saveClicked) {
                crudMatchController.btnUpdateOnClick();
            }
        } else {
            FxDialogs.showError("No selecciono partido", "Debe seleccionar un partido en la tabla");
        }
        populateTableView();
    }

    @FXML
    private void btnManageSuspensionsOnClick() {
        Match selectedMatch = matchTableView.getSelectionModel().getSelectedItem();
        if (selectedMatch != null) {
            String state = stateColumn.getCellData(matchTableView.getSelectionModel().getSelectedIndex());
            if (state.equals("Jugado")) {
                /*mainScreenController = new MainScreenController();
                mainScreenController.showSuspensionOverview();*/

            } else {
                FxDialogs.showError("Estado diferente a 'Jugado'", "No se puede manejar suspensiones de partidos creados o finalizados");
            }
            /*boolean saveClicked = showMatchEditDialog(this.selectedMatchId, true);
            if(saveClicked){
                crudMatchController.btnUpdateOnClick();
            }*/
        } else {
            FxDialogs.showError("Error ", "No se selecciono un partido");
        }
        populateTableView();
    }

    private void populateTableView() {
        idMatchColumn.setCellValueFactory(cellData -> cellData.getValue().idMatchProperty().asObject());
        accessCodeColumn.setCellValueFactory(cellData -> cellData.getValue().accessCodeProperty().asObject());
        meetingColumn.setCellValueFactory(cellData -> cellData.getValue().meetingProperty().asObject());
        idTeam1Column.setCellValueFactory(cellData -> cellData.getValue().idTeam1Property().asObject());
        idTeam2Column.setCellValueFactory(cellData -> cellData.getValue().idTeam2Property().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());


        matchTableView.setItems(matchQueries.getMatches());

        matchTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 1) {
                selectedMatchId = idMatchColumn.getCellData(matchTableView.getSelectionModel().getSelectedIndex());
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                btnManageSuspensions.setDisable(false);
            }
        });
    }

    public boolean showMatchEditDialog(int idMatch, boolean matchIsModified) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/add_match.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Agregar o editar partido");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.getIcons().add(new Image("file:src/main/resources/images/icon.png"));
            Stage currentStage = (Stage) (matchTableView.getScene().getWindow());
            dialogStage.initOwner(currentStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the match into the controller.
            CRUDMatchController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            /*controller.setPlayer(player);*/
            controller.setIdMatch(idMatch);
            controller.setIsModified(matchIsModified);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showSuspensionDialog(int idMatch, boolean matchIsModified) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/suspension_overview.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Agregar o editar suspensiones");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.getIcons().add(new Image("file:src/main/resources/images/icon.png"));
            Stage currentStage = (Stage) (matchTableView.getScene().getWindow());
            dialogStage.initOwner(currentStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the match into the controller.
            CRUDMatchController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setIdMatch(idMatch);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void btnRefreshOnAction(){
        populateTableView();
    }

    private void setButtonsStyle(){
        btnAdd.setOnMouseEntered(mouseEvent -> btnAdd.setStyle("-fx-background-color: #03a306;"));
        btnAdd.setOnMouseExited(mouseEvent -> btnAdd.setStyle("-fx-background-color: #0A2463;"));
        btnDelete.setOnMouseEntered(mouseEvent -> btnDelete.setStyle("-fx-background-color: #ed0707;"));
        btnDelete.setOnMouseExited(mouseEvent -> btnDelete.setStyle("-fx-background-color: #0A2463;"));
        btnUpdate.setOnMouseEntered(mouseEvent -> btnUpdate.setStyle("-fx-background-color: #2c4dde;"));
        btnUpdate.setOnMouseExited(mouseEvent -> btnUpdate.setStyle("-fx-background-color: #0A2463;"));
        btnRefresh.setOnMouseEntered(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/flatastic-8/24/Refresh-icon.png)"));
        btnRefresh.setOnMouseExited(mouseEvent -> btnRefresh.setStyle("-fx-graphic: url(https://icons.iconarchive.com/icons/custom-icon-design/mono-general-4/24/refresh-icon.png)"));
    }
}
