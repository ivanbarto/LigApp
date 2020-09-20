package sample;

import javafx.beans.property.*;

import java.sql.Blob;

public class Team {
    private IntegerProperty idTeam;
    private StringProperty name;
    private StringProperty shortName;
    private ObjectProperty<Blob> shieldImage;
    private StringProperty managerName;
    private StringProperty managerEmail;
    private StringProperty managerPhone;
    private IntegerProperty idLeague;

    public Team() {
        this(0,null,null,null,null,null,null,0);
    }

    public Team(int idTeam, String name, String shortName, Blob shieldImage, String managerName, String managerEmail, String managerPhone, int idLeague) {
        this.idTeam = new SimpleIntegerProperty(idTeam);
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);
        this.shieldImage = new SimpleObjectProperty<>(shieldImage);
        this.managerName = new SimpleStringProperty(managerName);
        this.managerEmail = new SimpleStringProperty(managerEmail);
        this.managerPhone = new SimpleStringProperty(managerPhone);
        this.idLeague = new SimpleIntegerProperty(idLeague);
    }

    public int getIdTeam() {
        return idTeam.get();
    }

    public IntegerProperty idTeamProperty() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam.set(idTeam);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShortName() {
        return shortName.get();
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }

    public Blob getShieldImage() {
        return shieldImage.get();
    }

    public ObjectProperty<Blob> shieldImageProperty() {
        return shieldImage;
    }

    public void setShieldImage(Blob shieldImage) {
        this.shieldImage.set(shieldImage);
    }

    public String getManagerName() {
        return managerName.get();
    }

    public StringProperty managerNameProperty() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName.set(managerName);
    }

    public String getManagerEmail() {
        return managerEmail.get();
    }

    public StringProperty managerEmailProperty() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail.set(managerEmail);
    }

    public String getManagerPhone() {
        return managerPhone.get();
    }

    public StringProperty managerPhoneProperty() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone.set(managerPhone);
    }

    public int getIdLeague() {
        return idLeague.get();
    }

    public IntegerProperty idLeagueProperty() {
        return idLeague;
    }

    public void setIdLeague(int idLeague) {
        this.idLeague.set(idLeague);
    }
}
