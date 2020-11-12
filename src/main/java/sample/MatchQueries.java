package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.utils.FxDialogs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchQueries {

    private final String ADD_MATCH_QUERY = "INSERT INTO matches (accessCode,meeting,idTeam1,idTeam2,date,time,state) VALUES (?,?,?,?,?,?,?)";
    private final String REMOVE_MATCH_QUERY = "DELETE FROM matches WHERE id = ?";
    private final String GET_MATCH_QUERY = "SELECT * FROM matches WHERE id=?";
    private final String GET_ALL_MATCHES_QUERY = "SELECT * FROM matches";
    private final String UPDATE_MATCH_QUERY = "UPDATE matches SET accessCode=?,meeting=?,idTeam1=?,idTeam2=?,date=?,time=?,state=?,WHERE id=?";

    DatabaseConnection dbConnection;

    public MatchQueries() {
        this.dbConnection = new DatabaseConnection();
    }

    public void addMatch(Match match){
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(ADD_MATCH_QUERY);
            ps.setInt(1, match.getAccessCode());
            ps.setInt(2, match.getMeeting());
            ps.setInt(3, match.getIdTeam1());
            ps.setInt(4, match.getIdTeam2());
            ps.setDate(5,Date.valueOf(match.getDate()));
            ps.setString(6,match.getTime());
            ps.setString(7,match.getState());

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            FxDialogs.showInformation("Alta de Partido","¡PARTIDO AGREGADO CON EXITO!");

        } catch (SQLException ex) {
            FxDialogs.showError("Fallo insertar partido en BBDD", String.valueOf(ex));
        }
    }

    public void removeMatch(int idMatch){
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(REMOVE_MATCH_QUERY);

            ps.setInt(1, idMatch);

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            FxDialogs.showInformation("Eliminar partido","¡PARTIDO ELIMINADO!");

        } catch (SQLException e) {
            FxDialogs.showError("Fallo eliminacion partido BD",String.valueOf(e));
        }
    }

    public void updateMatch(Match match){
        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(UPDATE_MATCH_QUERY);
            ps.setInt(1, match.getAccessCode());
            ps.setInt(2, match.getMeeting());
            ps.setInt(3, match.getIdTeam1());
            ps.setInt(4, match.getIdTeam2());
            ps.setDate(5,Date.valueOf(match.getDate()));
            ps.setString(6,match.getTime());
            ps.setString(7,match.getState());
            ps.setInt(8,match.getIdMatch());

            ps.execute();
            ps.close();
            dbConnection.disconnect();

            FxDialogs.showInformation("Actualizacion de Partido","¡PARTIDO ACTUALIZADO CON EXITO!");

        } catch (SQLException ex) {
            FxDialogs.showError("Fallo Actualizar partido",String.valueOf(ex));
        }
    }

    public ObservableList<Match> getMatches(){
        ObservableList<Match> matchesList = FXCollections.observableArrayList();

        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_ALL_MATCHES_QUERY);

            ResultSet matchesTable = ps.executeQuery();

            while (matchesTable.next()) {

                Match matchToAdd = new Match();
                matchToAdd.setIdMatch(matchesTable.getInt("id"));
                matchToAdd.setAccessCode(matchesTable.getInt("accessCode"));
                matchToAdd.setMeeting(matchesTable.getInt("meeting"));
                matchToAdd.setIdTeam1(matchesTable.getInt("idTeam1"));
                matchToAdd.setIdTeam2(matchesTable.getInt("idTeam2"));
                matchToAdd.setDate(matchesTable.getDate("date").toLocalDate());
                matchToAdd.setTime(matchesTable.getString("time"));
                matchToAdd.setState(matchesTable.getString("state"));

                matchesList.add(matchToAdd);
            }

            ps.close();
            dbConnection.disconnect();

        } catch (SQLException e) {
            FxDialogs.showError("Error en traer registros",String.valueOf(e));
        }

        return matchesList;
    }

    public Match getMatch(int idMatch){
        Match match = new Match();

        try {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(GET_MATCH_QUERY);
            ps.setInt(1,idMatch);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Match matchToAdd = new Match();
                matchToAdd.setIdMatch(rs.getInt("id"));
                matchToAdd.setAccessCode(rs.getInt("accessCode"));
                matchToAdd.setMeeting(rs.getInt("meeting"));
                matchToAdd.setIdTeam1(rs.getInt("idTeam1"));
                matchToAdd.setIdTeam2(rs.getInt("idTeam2"));
                matchToAdd.setDate(rs.getDate("date").toLocalDate());
                matchToAdd.setTime(rs.getString("time"));
                matchToAdd.setState(rs.getString("state"));

            }

            ps.close();
            dbConnection.disconnect();

        } catch (SQLException e) {
            FxDialogs.showError("Error en traer registro",String.valueOf(e));
        }

        return match;
    }
}
