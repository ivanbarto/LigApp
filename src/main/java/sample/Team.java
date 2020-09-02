package sample;

import java.sql.Blob;

public class Team {
    private int idTeam;
    private String name;
    private String shortName;
    private Blob shieldImage;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    private int idLeague;

    public Team() {
    }

    public Team(int idTeam, String name, String shortName, Blob shieldImage, String managerName, String managerEmail, String managerPhone, int idLeague) {
        this.idTeam = idTeam;
        this.name = name;
        this.shortName = shortName;
        this.shieldImage = shieldImage;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.managerPhone = managerPhone;
        this.idLeague = idLeague;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Blob getShieldImage() {
        return shieldImage;
    }

    public void setShieldImage(Blob shieldImage) {
        this.shieldImage = shieldImage;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public int getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(int idLeague) {
        this.idLeague = idLeague;
    }
}
