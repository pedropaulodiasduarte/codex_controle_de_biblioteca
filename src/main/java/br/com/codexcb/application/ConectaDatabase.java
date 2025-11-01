package br.com.codexcb.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/clientefiel";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        // Agora, se o módulo estiver configurado corretamente,
        // a exceção ClassNotFoundException não será mais necessária.
        // O DriverManager fará o trabalho.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}