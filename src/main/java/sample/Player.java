package sample;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Player {

    private final IntegerProperty idPlayer;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty DNI;
    private final ObjectProperty<LocalDate> birthDate;
    private final BooleanProperty hasMedicalClearance;
    private final StringProperty comments;
    private final BooleanProperty isSuspended;
    private final StringProperty numberOfSuspensionDays;
    private final IntegerProperty idTeam;
    private String photo;


    public Player(){
        this(0,null,null,null,null,false,null,false,null,0, null);
    }

    public Player(int idPlayer, String firstName, String lastName, String DNI, LocalDate birthDate, boolean hasMedicalClearance, String comments, boolean isSuspended, String numberOfSuspensionDays, int idTeam, String photo){
        this.idPlayer = new SimpleIntegerProperty(idPlayer);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.DNI = new SimpleStringProperty(DNI);
        this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
        this.hasMedicalClearance = new SimpleBooleanProperty(hasMedicalClearance);
        this.comments = new SimpleStringProperty(comments);
        this.isSuspended = new SimpleBooleanProperty(isSuspended);
        this.numberOfSuspensionDays = new SimpleStringProperty(numberOfSuspensionDays);
        this.idTeam = new SimpleIntegerProperty(idTeam);
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String base64Encoded){
        this.photo = base64Encoded;
    }

    public int getIdPlayer() {
        return idPlayer.get();
    }

    public IntegerProperty idPlayerProperty() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer.set(idPlayer);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getDNI() {
        return DNI.get();
    }

    public StringProperty DNIProperty() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI.set(DNI);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public boolean getHasMedicalClearance() {
        return hasMedicalClearance.get();
    }

    public BooleanProperty hasMedicalClearanceProperty() {
        return hasMedicalClearance;
    }

    public void setHasMedicalClearance(boolean hasMedicalClearance) {
        this.hasMedicalClearance.set(hasMedicalClearance);
    }

    public String getComments() {
        return comments.get();
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public boolean getIsSuspended() {
        return isSuspended.get();
    }

    public BooleanProperty isSuspendedProperty() {
        return isSuspended;
    }

    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended.set(isSuspended);
    }

    public String getNumberOfSuspensionDays() {
        return numberOfSuspensionDays.get();
    }

    public StringProperty numberOfSuspensionDaysProperty() {
        return numberOfSuspensionDays;
    }

    public void setNumberOfSuspensionDays(String numberOfSuspensionDays) {
        this.numberOfSuspensionDays.set(numberOfSuspensionDays);
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

}

