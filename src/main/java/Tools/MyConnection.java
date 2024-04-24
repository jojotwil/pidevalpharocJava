package Tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
    private  String url="jdbc:mysql://localhost:3306/alphatroc";
    private String login="root";
    private String pwd="";
   private static Connection cnx;
    public static  MyConnection instace;
    private MyConnection() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd) ;
            System.out.println("connexion établie...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MyConnection getInstace() {
        if(instace==null){
            instace = new MyConnection();
        }
        return instace;
    }

    public Connection getCnx() {
        return cnx;
    }
    public static void closeconnection() {
        try {
            if (cnx != null && !cnx.isClosed()) {
                cnx.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
