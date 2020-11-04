package sample;

import javafx.beans.property.*;

import java.sql.Blob;
import java.time.LocalDate;

public class Match {
    private IntegerProperty idMatch;
    private IntegerProperty accessCode;
    private IntegerProperty meeting;
    private IntegerProperty idTeam1;
    private IntegerProperty idTeam2;
    private ObjectProperty<LocalDate> date;
    private StringProperty time;
    private StringProperty state;

    public Match() {
        this(0,0,0,0,0,null,null,null);
    }

    public Match(int idMatch, int accessCode, int meeting, int idTeam1, int idTeam2, LocalDate date, String time, String state) {
        this.idMatch = new SimpleIntegerProperty(idMatch);
        this.accessCode = new SimpleIntegerProperty(accessCode);
        this.meeting = new SimpleIntegerProperty(meeting);
        this.idTeam1 = new SimpleIntegerProperty(idTeam1);
        this.idTeam2 = new SimpleIntegerProperty(idTeam2);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
    }

    public int getIdMatch() {
        return idMatch.get();
    }

    public IntegerProperty idMatchProperty() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch.set(idMatch);
    }

    public int getAccessCode() {
        return accessCode.get();
    }

    public IntegerProperty accessCodeProperty() {
        return accessCode;
    }

    public void setAccessCode(int accessCode) {
        this.accessCode.set(accessCode);
    }

    public int getMeeting() {
        return meeting.get();
    }

    public IntegerProperty meetingProperty() {
        return meeting;
    }

    public void setMeeting(int meeting) {
        this.meeting.set(meeting);
    }

    public int getIdTeam1() {
        return idTeam1.get();
    }

    public IntegerProperty idTeam1Property() {
        return idTeam1;
    }

    public void setIdTeam1(int idTeam1) {
        this.idTeam1.set(idTeam1);
    }

    public int getIdTeam2() {
        return idTeam2.get();
    }

    public IntegerProperty idTeam2Property() {
        return idTeam2;
    }

    public void setIdTeam2(int idTeam2) {
        this.idTeam2.set(idTeam2);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }
}
