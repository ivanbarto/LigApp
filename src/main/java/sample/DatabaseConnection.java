package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection databaseLink = null;
    private final String DATABASE_NAME = "bdligapp";
    private final String DATABASE_PASSWORD = "654654654Da";
    private final String DATABASE_USER = "fedecurto98";
    private final String DATABASE_HOST = "db4free.net";
    private final String DATABASE_PORT = "3306";
    private final String DATABASE_URL = "jdbc:mysql://" + DATABASE_HOST + ":"+ DATABASE_PORT +"/" + DATABASE_NAME + "?useSSL=false";

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(this.DATABASE_URL, DATABASE_USER,this.DATABASE_PASSWORD);

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
