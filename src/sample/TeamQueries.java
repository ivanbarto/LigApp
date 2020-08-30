package sample;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamQueries {
    DatabaseConnection dbConnection;

    public TeamQueries() { this.dbConnection = new DatabaseConnection(); }

    public void addTeam(Team team) {
        try {
            String sql = "INSERT INTO team (name,shortName,shieldImage,managerName,managerEmail,managerPhone,idLeague) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(sql);
            ps.setString(1,team.getName());
            ps.setString(2,team.getShortName());
            ps.setBlob(3,team.getShieldImage());
            ps.setString(4,team.getManagerName());
            ps.setString(5,team.getManagerEmail());
            ps.setString(6,team.getManagerPhone());
            ps.setInt(7,team.getIdLeague());

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            showSuccessAlert("Alta de Equipo", "¡EQUIPO AGREGADO CON ÉXITO!");


        } catch (SQLException ex) {
            showSQLErrorAlert(ex, "Fallo insertar equipo en BBDD");

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
