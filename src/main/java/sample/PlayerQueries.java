package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerQueries {

    private final int PLAYER_FEATURES = 11;

    private final String ADD_PLAYER_QUERY = "INSERT INTO player (firstName,lastName,DNI,birthDate,age,hasMedicalClearance,comments,isSuspended,numberOfSuspensionDays,idTeam) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String REMOVE_PLAYER_QUERY = "DELETE FROM player WHERE idPlayer = ?";
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
            ps.setDate(4, player.getBirthDate());
            ps.setString(5, player.getAge());
            ps.setBoolean(6, player.getHasMedicalClearance());
            ps.setString(7, player.getComments());
            ps.setBoolean(8, player.getIsSuspended());
            ps.setString(9, player.getNumberOfSuspensionDays());
            ps.setInt(10, player.getIdTeam());

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

    public void removePlayer(int playerId, boolean playerIsModified) {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_PLAYER_QUERY);

            ps.setInt(1, playerId);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            if (!playerIsModified) {
                showSuccessAlert("eliminar jugador", "¡JUGADOR ELIMINADO!");
            }

        } catch (SQLException e) {
            showSQLErrorAlert(e, "Fallo eliminar jugador en BBDD");
        }
    }

    public ObservableList<Player> getPlayers() {
        ArrayList<Object> objects = new ArrayList<>();
        ObservableList<Player> playersList = FXCollections.observableArrayList();

        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_ALL_PLAYERS_QUERY);

            ResultSet playersTable = ps.executeQuery();

            while (playersTable.next()) {
                Object[] player = new Object[11];

                for (int playerIndex = 0; playerIndex < PLAYER_FEATURES; playerIndex++) {
                    player[playerIndex] = playersTable.getObject(playerIndex + 1);
                }


                Player playerToAdd = new Player();
                playerToAdd.setIdPlayer((int)player[0]);
                playerToAdd.setFirstName((String)player[1]);
                System.out.print((String)player[1]);
                System.out.print(" ");
                playerToAdd.setLastName((String)player[2]);
                System.out.print((String)player[2]);

                playerToAdd.setDNI((String)player[3]);
                //playerToAdd.setBirthDate(Date.valueOf((String)player[4]));
                playerToAdd.setBirthDate(null);
                playerToAdd.setAge((String)player[5]);
                //playerToAdd.setHasMedicalClearance((boolean)player[6]);
                playerToAdd.setHasMedicalClearance(false);
                playerToAdd.setComments((String)player[7]);
                playerToAdd.setNumberOfSuspensionDays((String)player[9]);
                playerToAdd.setIdTeam((int)player[10]);
                System.out.println("");

               /* Player playerToAdd = new Player();
                playerToAdd.setIdPlayer((playersTable.getInt("idPlayer")));
                playerToAdd.setFirstName(playersTable.getString("firstName"));
                playerToAdd.setLastName(playersTable.getString("lastName"));
                playerToAdd.setDNI(playersTable.getString("DNI"));
                playerToAdd.setBirthDate(null);
                playerToAdd.setAge(playersTable.getString("age"));
                playerToAdd.setHasMedicalClearance(false);
                playerToAdd.setComments(playersTable.getString("comments"));
                playerToAdd.setSuspended(false);
                playerToAdd.setNumberOfSuspensionDays(playersTable.getString("numberOfSuspensionDays"));
                playerToAdd.setIdTeam(playersTable.getInt("idTeam"));
*/
                playersList.addAll(playerToAdd);
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
