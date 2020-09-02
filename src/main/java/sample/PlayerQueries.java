package sample;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerQueries {

    private final String ADD_PLAYER_QUERY = "INSERT INTO player (firstName,lastName,DNI,birthDate,age,hasMedicalClearance,comments,isSuspended,numberOfSuspensionDays,idTeam) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String REMOVE_PLAYER_QUERY = "DELETE FROM player WHERE idPlayer = ?";

    DatabaseConnection dbConnection;

    public PlayerQueries() {
        this.dbConnection = new DatabaseConnection();
    }

    public void addPlayer(Player player, boolean playerIsModified){
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(ADD_PLAYER_QUERY);
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getDNI());
            ps.setDate(4, player.getBirthDate());
            ps.setString(5, player.getAge());
            ps.setBoolean(6, player.getHasMedicalClearance());
            ps.setString(7,player.getComments());
            ps.setBoolean(8, player.getIsSuspended());
            ps.setString(9, player.getNumberOfSuspensionDays());
            ps.setInt(10,player.getIdTeam());

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            if (!playerIsModified){
                showSuccessAlert("Alta de Jugador","¡JUGADOR AGREGADO CON ÉXITO!");
            }else {
                showSuccessAlert("Alta de Jugador","¡JUGADOR MODIFICADO!");
            }
        } catch (SQLException ex) {
            showSQLErrorAlert(ex,"Fallo insertar jugador en BBDD");
        }
    }

    public void removePlayer(int playerId, boolean playerIsModified){
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_PLAYER_QUERY);

            ps.setInt(1,playerId);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            if(!playerIsModified){
                showSuccessAlert("eliminar jugador", "¡JUGADOR ELIMINADO!");
            }

        }catch (SQLException e){
            showSQLErrorAlert(e,"Fallo eliminar jugador en BBDD");
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
