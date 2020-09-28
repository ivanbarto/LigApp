package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerQueries {

    private final int PLAYER_FEATURES = 11;

    private final String ADD_PLAYER_QUERY = "INSERT INTO player (firstName,lastName,DNI,birthDate,hasMedicalClearance,comments,isSuspended,numberOfSuspensionDays,idTeam,photo) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String REMOVE_PLAYER_QUERY = "DELETE FROM player WHERE idPlayer = ?";
    private final String REMOVE_TEAM_PLAYERS_QUERY = "DELETE FROM player WHERE idTeam = ?";
    private final String GET_ALL_PLAYERS_QUERY = "SELECT * FROM player";

    DatabaseConnection dbConnection;

    public PlayerQueries() {
        this.dbConnection = new DatabaseConnection();
    }

    public void addPlayer(Player player, boolean playerIsModified) {
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(ADD_PLAYER_QUERY);
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getDNI());
            ps.setDate(4, Date.valueOf(player.getBirthDate()));
            ps.setBoolean(5, player.getHasMedicalClearance());
            ps.setString(6, player.getComments());
            ps.setBoolean(7, player.getIsSuspended());
            ps.setString(8, player.getNumberOfSuspensionDays());
            ps.setInt(9, player.getIdTeam());
            ps.setString(10,player.getPhoto());

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            if (!playerIsModified) {
                showSuccessAlert("Alta de Jugador", "¡JUGADOR AGREGADO CON ÉXITO!");
            } else {
                showSuccessAlert("Alta de Jugador", "¡JUGADOR MODIFICADO!");
            }
        } catch (SQLException ex) {
            showSQLErrorAlert(ex, "Fallo insertar jugador en BBDD");
        }
    }

    public void removePlayer(int playerId/*, boolean playerIsModified*/) {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_PLAYER_QUERY);

            ps.setInt(1, playerId);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

           /* if (!playerIsModified) {
                showSuccessAlert("eliminar jugador", "¡JUGADOR ELIMINADO!");
            }*/
            showSuccessAlert("eliminar jugador", "¡JUGADOR ELIMINADO!");

        } catch (SQLException e) {
            showSQLErrorAlert(e, "Fallo eliminar jugador en BBDD");
        }
    }

    public void removeTeamPlayers(int teamId) {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_TEAM_PLAYERS_QUERY);

            ps.setInt(1, teamId);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

           /* if (!playerIsModified) {
                showSuccessAlert("eliminar jugador", "¡JUGADOR ELIMINADO!");
            }*/
            showSuccessAlert("eliminar jugadores", "SE ELIMINARON TODOS LOS JUGADORES ASOCIADOS AL EQUIPO");

        } catch (SQLException e) {
            showSQLErrorAlert(e, "Fallo eliminar jugadores en BBDD");
        }
    }



    public ObservableList<Player> getPlayers() {
        ObservableList<Player> playersList = FXCollections.observableArrayList();

        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_ALL_PLAYERS_QUERY);

            ResultSet playersTable = ps.executeQuery();

            while (playersTable.next()) {

                Player playerToAdd = new Player();
                playerToAdd.setIdPlayer((playersTable.getInt("idPlayer")));
                playerToAdd.setFirstName(playersTable.getString("firstName"));
                playerToAdd.setLastName(playersTable.getString("lastName"));
                playerToAdd.setDNI(playersTable.getString("DNI"));
                playerToAdd.setBirthDate(null);
                playerToAdd.setHasMedicalClearance(false);
                playerToAdd.setComments(playersTable.getString("comments"));
                playerToAdd.setIsSuspended(false);
                playerToAdd.setNumberOfSuspensionDays(playersTable.getString("numberOfSuspensionDays"));
                playerToAdd.setIdTeam(playersTable.getInt("idTeam"));

                playersList.add(playerToAdd);
            }

            ps.close();
            dbConnection.disconnect();

        } catch (SQLException e) {
            showSQLErrorAlert(e,"player list error");
        }

        return playersList;
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
