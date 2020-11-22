package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection databaseLink = null;
    private final String DATABASE_NAME = "bdligapp";
    private final String DATABASE_PASSWORD = "654654654Da";
    private final String DATABASE_USER = "fedecurto98";
    private final String LOCAL_DATABASE_USER = "root";
    private final String ONLINE_DATABASE_HOST = "db4free.net";
    private final String LOCAL_DATABASE_HOST = "localhost";
    private final String ONLINE_DATABASE_PORT = "3306";
    private final String ONLINE_DATABASE_URL = "jdbc:mysql://" + ONLINE_DATABASE_HOST + ":"+ ONLINE_DATABASE_PORT +"/" + DATABASE_NAME + "?useSSL=false";
    private final String DATABASE_URL = "jdbc:mysql://" + LOCAL_DATABASE_HOST + ":"+ ONLINE_DATABASE_PORT +"/" + DATABASE_NAME + "?useSSL=false";

   /* public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(this.ONLINE_DATABASE_URL, this.DATABASE_USER,this.DATABASE_PASSWORD);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return  databaseLink;
    }*/

    //LOCAL CONNECTION FOR TESTING AND DEMOS

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(this.DATABASE_URL, this.LOCAL_DATABASE_USER,this.DATABASE_PASSWORD);

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
