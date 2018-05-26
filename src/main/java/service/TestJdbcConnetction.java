package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJdbcConnetction {

    private final String URL = "jdbc:postgresql://localhost:5432/bdtest";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection myCreateConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД Установлено!");
                System.out.println("-----------------------------------");
            }
            //connection.close();
            //if (connection.isClosed()) {
            //System.out.println("Соединение с БД Закрыто!");
            // }
        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера");
        }
        return connection;
    }

    public Connection createConnection() {
        // Connection connection = null;
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    URL, USERNAME,
                    PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        return connection;
    }
}
