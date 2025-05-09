package vallegrade.edu.pe.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

 private static final String URL = "jdbc:mysql://localhost:33062/studentsDb"; //Cambiar puerto y nombre de bd
 private static final String USER = "root";
 private static final String PASSWORD = "Valle123";

 public static Connection getConnection() throws SQLException {
     return DriverManager.getConnection(URL, USER, PASSWORD);
 }
}





