package ru.aston.davydovskaya_av.task4.configs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MySqlConfig {

    private static Connection connection;

    public static Connection createConnection() throws SQLException {
        if (connection == null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

            connection = DriverManager.getConnection(
                    resourceBundle.getString("db.url") + resourceBundle.getString("db.name"),
                    resourceBundle.getString("db.user"),
                    resourceBundle.getString("db.password"));
        }

        return connection;
    }
}
