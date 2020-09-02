package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @FXML
    private TableView<Player> tbvPlayer;
    @FXML
    private TableView<Team> tbvTeam;

    @Override
    public void start(Stage primaryStage) throws Exception{

        PlayerQueries playerQueries = new PlayerQueries();

        //estas 3 instrucciones son para generar una columna en una tabla
        TableColumn<Player, String> playerNameColumn = new TableColumn<>("Nombre");
        playerNameColumn.setMinWidth(100);
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        tbvPlayer = new TableView<>();
        tbvPlayer.setItems(playerQueries.getPlayers());
        tbvPlayer.getColumns().add(playerNameColumn);


        //TODO por ahora entra directo a main menu pero despues poner el login
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/tabs.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
