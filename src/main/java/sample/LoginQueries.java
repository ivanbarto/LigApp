package sample;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginQueries {

    private final String GET_USERS = "SELECT * FROM users WHERE username = ? and password = ?";

    private DatabaseConnection dbConnection;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public LoginQueries() {this.dbConnection = new DatabaseConnection();}

    public boolean logIn(String email, String password){
        boolean success = false;

        try {
            ps = dbConnection.getConnection().prepareStatement(GET_USERS);
            ps.setString(1,email);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
               success = true;
            }

        }catch (SQLException e){
            showSQLErrorAlert(e,"Ocurrió un error al momento de loguearse. Por favor, vuelve a intentarlo.");
        }

        return success;
    }

    public void showSQLErrorAlert(SQLException errorMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(String.valueOf(errorMessage));
        alert.showAndWait();
    }

    public void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
