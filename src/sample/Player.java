package sample;

import java.sql.Date;

public class Player {

    private int idPlayer;
    private String firstName;
    private String lastName;
    private String DNI;
    private Date birthDate;
    private String age;
    private boolean hasMedicalClearance;
    private String comments;
    private boolean isSuspended;
    private String numberOfSuspensionDays;
    private int idTeam;

    public Player() {
    }

    public Player(int idPlayer, String firstName, String lastName, String DNI, Date birthDate, String age, boolean hasMedicalClearance, String comments, boolean isSuspended, String numberOfSuspensionDays, int idTeam) {
        this.idPlayer = idPlayer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DNI = DNI;
        this.birthDate = birthDate;
        this.age = age;
        this.hasMedicalClearance = hasMedicalClearance;
        this.comments = comments;
        this.isSuspended = isSuspended;
        this.numberOfSuspensionDays = numberOfSuspensionDays;
        this.idTeam = idTeam;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isHasMedicalClearance() {
        return hasMedicalClearance;
    }

    public void setHasMedicalClearance(boolean hasMedicalClearance) {
        this.hasMedicalClearance = hasMedicalClearance;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public String getNumberOfSuspensionDays() {
        return numberOfSuspensionDays;
    }

    public void setNumberOfSuspensionDays(String numberOfSuspensionDays) {
        this.numberOfSuspensionDays = numberOfSuspensionDays;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
}

