package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.utils.FxDialogs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerQueries {

    private final String ADD_PLAYER_QUERY = "INSERT INTO player (firstName,lastName,DNI,birthDate,hasMedicalClearance,comments,isSuspended,numberOfSuspensionDays,idTeam,photo) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String REMOVE_PLAYER_QUERY = "DELETE FROM player WHERE idPlayer = ?";
    private final String REMOVE_TEAM_PLAYERS_QUERY = "DELETE FROM player WHERE idTeam = ?";
    private final String GET_TEAM_PLAYERS_QUERY = "SELECT * FROM player WHERE idTeam = ?";
    private final String GET_PLAYER_QUERY = "SELECT * FROM player WHERE idPlayer=?";
    private final String GET_ALL_PLAYERS_QUERY = "SELECT * FROM player";
    private final String UPDATE_PLAYER_QUERY = "UPDATE player SET firstName=?,lastName=?,DNI=?,birthDate=?,hasMedicalClearance=?,comments=?,isSuspended=?,numberOfSuspensionDays=?,idTeam=?,photo=? WHERE idPlayer=?";

    DatabaseConnection dbConnection;

    public PlayerQueries() {
        this.dbConnection = new DatabaseConnection();
    }

    public void addPlayer(Player player) {
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
            ps.setString(10, player.getPhoto());

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            FxDialogs.showInformation("Alta de Jugador","¡JUGADOR AGREGADO CON EXITO!");

        } catch (SQLException ex) {
            FxDialogs.showError("Fallo insertar jugador en BBDD", String.valueOf(ex));
        }
    }

    public void removePlayer(int idPlayer) {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_PLAYER_QUERY);

            ps.setInt(1, idPlayer);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            FxDialogs.showInformation("Eliminar jugador","¡JUGADOR ELIMINADO!");

        } catch (SQLException e) {
            FxDialogs.showError("Fallo eliminacion jugador BD",String.valueOf(e));
        }
    }

    public void removeTeamPlayers(int idTeam) {
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_TEAM_PLAYERS_QUERY);

            ps.setInt(1, idTeam);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            FxDialogs.showInformation("Eliminar jugadores","SE ELIMINARON TODOS LOS JUGADORES ASOCIADOS AL EQUIPO");

        } catch (SQLException e) {
            FxDialogs.showError("Fallo eliminacion jugadores de equipo BD",String.valueOf(e));
        }
    }

    public void updatePlayer(Player player) {
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(UPDATE_PLAYER_QUERY);
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getDNI());
            ps.setDate(4, Date.valueOf(player.getBirthDate()));
            ps.setBoolean(5, player.getHasMedicalClearance());
            ps.setString(6, player.getComments());
            ps.setBoolean(7, player.getIsSuspended());
            ps.setString(8, player.getNumberOfSuspensionDays());
            ps.setInt(9, player.getIdTeam());
            ps.setString(10, player.getPhoto());
            ps.setInt(11, player.getIdPlayer());

            ps.executeUpdate();
            ps.close();
            dbConnection.disconnect();


            FxDialogs.showInformation("Actualizacion de Jugador","¡JUGADOR ACTUALIZADO CON EXITO!");

        } catch (SQLException ex) {
            FxDialogs.showError("Fallo Actualizar Jugador",String.valueOf(ex));
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
                playerToAdd.setBirthDate(playersTable.getDate("birthDate").toLocalDate());
                playerToAdd.setHasMedicalClearance(playersTable.getBoolean("hasMedicalClearance"));
                playerToAdd.setComments(playersTable.getString("comments"));
                playerToAdd.setIsSuspended(playersTable.getBoolean("isSuspended"));
                playerToAdd.setNumberOfSuspensionDays(playersTable.getString("numberOfSuspensionDays"));
                playerToAdd.setIdTeam(playersTable.getInt("idTeam"));

                playersList.add(playerToAdd);
            }

            ps.close();
            dbConnection.disconnect();

        } catch (SQLException e) {
            FxDialogs.showError("Error en traer registros",String.valueOf(e));
        }

        return playersList;
    }

    public ObservableList<Player> getTeamPlayers(int idTeam) {
        ObservableList<Player> playersList = FXCollections.observableArrayList();

        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_TEAM_PLAYERS_QUERY);
            ps.setInt(1, idTeam);
            ResultSet playersTable = ps.executeQuery();

            while (playersTable.next()) {

                Player playerToAdd = new Player();
                playerToAdd.setIdPlayer((playersTable.getInt("idPlayer")));
                playerToAdd.setFirstName(playersTable.getString("firstName"));
                playerToAdd.setLastName(playersTable.getString("lastName"));
                playerToAdd.setDNI(playersTable.getString("DNI"));
                playerToAdd.setBirthDate(playersTable.getDate("birthDate").toLocalDate());
                playerToAdd.setHasMedicalClearance(playersTable.getBoolean("hasMedicalClearance"));
                playerToAdd.setComments(playersTable.getString("comments"));
                playerToAdd.setIsSuspended(playersTable.getBoolean("isSuspended"));
                playerToAdd.setNumberOfSuspensionDays(playersTable.getString("numberOfSuspensionDays"));
                playerToAdd.setIdTeam(playersTable.getInt("idTeam"));

                playersList.add(playerToAdd);
            }

            ps.close();
            dbConnection.disconnect();

        } catch (SQLException e) {
            FxDialogs.showError("Error en traer registros",String.valueOf(e));
        }

        return playersList;
    }

    public Player getPlayer(int idPlayer){
        Player player  = new Player();
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_PLAYER_QUERY);
            ps.setInt(1, idPlayer);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                player.setIdPlayer((rs.getInt("idPlayer")));
                player.setFirstName(rs.getString("firstName"));
                player.setLastName(rs.getString("lastName"));
                player.setDNI(rs.getString("DNI"));
                player.setBirthDate(rs.getDate("birthDate").toLocalDate());
                player.setHasMedicalClearance(rs.getBoolean("hasMedicalClearance"));
                player.setComments(rs.getString("comments"));
                player.setIsSuspended(rs.getBoolean("isSuspended"));
                player.setNumberOfSuspensionDays(rs.getString("numberOfSuspensionDays"));
                player.setIdTeam(rs.getInt("idTeam"));
                player.setPhoto(rs.getString("photo"));

            }

            ps.close();
            dbConnection.disconnect();

        } catch (SQLException e) {
            FxDialogs.showError("Error en traer registro",String.valueOf(e));
        }

        return player;
    }

}
