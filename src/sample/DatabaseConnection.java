package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection databaseLink = null;
    private String databaseName = "bdligapp";
    private String databaseUser = "fedecurto98";
    private String databasePassword = "654654654Da";
    private String host = "db4free.net";
    private String port = "3306";
    private String url = "jdbc:mysql://" + host + ":"+ port +"/" + databaseName + "?useSSL=false";

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(this.url,this.databaseUser,this.databasePassword);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return  databaseLink;
    }

    public void disconnect (){
        try{
            this.databaseLink.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
