package sample;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FaceQueries {

    private final String ADD_PLAYER_FACE_QUERY ="INSERT INTO faces (image, idPlayer) VALUES (?,?)";

    DatabaseConnection dbConnection;

    public FaceQueries() {
        this.dbConnection = new DatabaseConnection();
    }

    public void addPlayerFace(String face, int playerId){



        try{
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(ADD_PLAYER_FACE_QUERY);

            //ps.setBlob(1, Base64.getDecoder().)
            ps.setInt(2,playerId);

        } catch (SQLException e) {
            showSQLErrorAlert(e, "player face add");
        }
    }

    public void showSQLErrorAlert(SQLException errorMessage,String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(String.valueOf(errorMessage));
        alert.showAndWait();
    }

    public void showSuccessAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
