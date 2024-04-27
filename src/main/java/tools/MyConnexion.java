package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {
    private String url="jdbc:mysql://localhost:3306/alphatroc"; //nom de lapi
    private String login="root";

    private String pwd="";
    Connection cnx ;
    public static  MyConnexion instance ;
    private MyConnexion(){

        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("cnx etablie..");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnexion getInstance() {
        if(instance==null){
            instance= new MyConnexion(); //masemni fi west l classe najm naaml new MyConnexion;
        }
        return instance;
    }
}
