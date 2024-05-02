package alpharoc.pidev.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {

    private  String url="jdbc:mysql://localhost:3306/alphatroc";
    private  String login="root";
    private  String pwd="";
    public  static MyConnexion instance;
    Connection cnx;

    private MyConnexion()
    {
        try {
            cnx =  DriverManager.getConnection(url,login,pwd);
            System.out.println("connextion etablie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static MyConnexion getInstance() {
        if(instance== null){
            instance=new MyConnexion();
        }
        return instance;
    }
}