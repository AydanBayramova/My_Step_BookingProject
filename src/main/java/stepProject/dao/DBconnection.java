package stepProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Aba32835");
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}
