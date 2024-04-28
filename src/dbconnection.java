
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class dbconnection {

    Connection con;
    Statement st;

    dbconnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema_db", "root", "1234");
            st = con.createStatement();
        } catch (Exception e) {
            //System.out.println(e);
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dbconnection obj = new dbconnection();
    }
}
