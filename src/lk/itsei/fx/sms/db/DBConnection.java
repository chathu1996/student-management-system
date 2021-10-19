package lk.itsei.fx.sms.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null) {
            connection = DriverManager.getConnection (
                    "jdbc:mysql://localhost:3307/studentmanagementsystem", "root", "1234"
            );
        }
        return connection;
    }

    public void showMessage() {
        System.out.println ("Successfully connect to database");
    }
}
