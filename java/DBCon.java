import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
    static Connection con;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseName = "abae";
            String username = "root"; 
            String password = "root";
            String url = "jdbc:mysql://localhost:1006/" + databaseName + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            
            // Get a connection
            con = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to MySQL database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}