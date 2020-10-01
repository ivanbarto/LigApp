package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamQueries {

    private final String ADD_TEAM_QUERY = "INSERT INTO team (name,shortName,shieldImage,managerName,managerEmail,managerPhone,idLeague) VALUES (?,?,?,?,?,?,?)";
    private final String GET_ALL_TEAMS_QUERY = "SELECT * FROM team";
    private final String GET_TEAM_QUERY = "SELECT * FROM team WHERE idTeam=?";
    private final String REMOVE_TEAM_QUERY = "DELETE FROM team WHERE idTeam = ?";
    private final String UPDATE_TEAM_QUERY = "UPDATE team SET name=?,shortName=?,shieldImage=?,managerName=?,managerEmail=?,managerPhone=?,idLeague=? WHERE idTeam=?";

    DatabaseConnection dbConnection;

    public TeamQueries() { this.dbConnection = new DatabaseConnection(); }

    public ObservableList<Team> getTeams(){
        ArrayList<Object> objects = new ArrayList<>();
        ObservableList<Team> teamsList = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_ALL_TEAMS_QUERY);

            ResultSet teamsTable = ps.executeQuery();

            while (teamsTable.next()){
                Team teamToAdd = new Team();
                teamToAdd.setIdTeam(teamsTable.getInt("idTeam"));
                teamToAdd.setName(teamsTable.getString("name"));
                teamToAdd.setShortName(teamsTable.getString("shortName"));
                teamToAdd.setShieldImage(teamsTable.getBlob("shieldImage"));
                teamToAdd.setManagerName(teamsTable.getString("managerName"));
                teamToAdd.setManagerEmail(teamsTable.getString("managerEmail"));
                teamToAdd.setManagerPhone(teamsTable.getString("managerPhone"));
                teamToAdd.setIdLeague(teamsTable.getInt("idLeague"));

                teamsList.add(teamToAdd);
            }

            ps.close();
            dbConnection.disconnect();

        }catch (SQLException e){
            showSQLErrorAlert(e,"teams list error");
        }

        return teamsList;
    }

    public Team getTeam(int idTeam){
        Team team = new Team();

        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_TEAM_QUERY);
            ps.setInt(1, idTeam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                team.setIdTeam(rs.getInt("idTeam"));
                team.setName(rs.getString("name"));
                team.setShortName(rs.getString("shortName"));
                team.setShieldImage(rs.getBlob("shieldImage"));
                team.setManagerName(rs.getString("managerName"));
                team.setManagerEmail(rs.getString("managerEmail"));
                team.setManagerPhone(rs.getString("managerPhone"));
                team.setIdLeague(rs.getInt("idLeague"));

            }

            ps.close();
            dbConnection.disconnect();

        }catch (SQLException e){
            showSQLErrorAlert(e,"get teams error");
        }

        return team;
    }

    public void addTeam(Team team) {
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(ADD_TEAM_QUERY);
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
            showSQLErrorAlert(ex, "Fallo agregar equipo en BBDD");
        }
    }

    public void removeTeam(int teamId){
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_TEAM_QUERY);

            ps.setInt(1, teamId);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            showSuccessAlert("eliminar equipo", "¡EQUIPO ELIMINADO!");


        } catch (SQLException e) {
            showSQLErrorAlert(e, "Fallo eliminar equipo en BBDD");
        }
    }

    public void updateTeam(Team team){
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(UPDATE_TEAM_QUERY);
            ps.setString(1,team.getName());
            ps.setString(2,team.getShortName());
            ps.setBlob(3,team.getShieldImage());
            ps.setString(4,team.getManagerName());
            ps.setString(5,team.getManagerEmail());
            ps.setString(6,team.getManagerPhone());
            ps.setInt(7,team.getIdLeague());
            ps.setInt(8,team.getIdTeam());

            ps.executeUpdate();
            ps.close();
            dbConnection.disconnect();

            showSuccessAlert("Modificación de Equipo", "¡EQUIPO MODIFICADO CON ÉXITO!");


        } catch (SQLException ex) {
            showSQLErrorAlert(ex, "Fallo modificar equipo en BBDD");

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
