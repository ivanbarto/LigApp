package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @FXML
    private TableView<Player> tbvPlayer;
    @FXML
    private TableView<Team> tbvTeam;

    public static Stage stage;

//    static {
//        nu.pattern.OpenCV.loadShared();
//        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
//    }

    static {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        try{
//            nu.pattern.OpenCV.loadShared();
//        }catch (Exception e){
//            System.err.println(e);
//        }
        nu.pattern.OpenCV.loadShared();


       // PlayerQueries playerQueries = new PlayerQueries();
/*
        //estas 3 instrucciones son para generar una columna en una tabla
        TableColumn<Player, String> playerNameColumn = new TableColumn<>("Nombre");
        playerNameColumn.setMinWidth(100);
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        tbvPlayer = new TableView<>();
        tbvPlayer.setItems(playerQueries.getPlayers());
        tbvPlayer.getColumns().add(playerNameColumn);
*/
        stage = primaryStage;

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
