package br.com.codexcb.application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/clientefiel";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    //Para ser singleton, devo então ter o construtor privado, para que não ocorra instanciações duplas.
    private ConectaDatabase() {
    }

    public static Connection getConnection() throws SQLException {
        //Constante que, limita em 2segundos como tempo máximo, para teste de conexão ao banco de dados
        final int VALIDATION_TIMEOUT_SECONDS = 2;

        if (connection == null) {
            // Agora, se o módulo estiver configurado corretamente,
            // a exceção ClassNotFoundException não será mais necessária.
            // O DriverManager fará o trabalho.
            //antes o do JDBC 4, precisava usar class.forName para carregar o driver corretamente.
            initiateConnection();
        }
        //Reconecta ao banco caso tenha sido fechado
        // isClosed(): Checa se o .close() foi chamado
        // !isValid(t): Tenta se comunicar com o banco para garantir que a conexão está ativa
        else if (connection.isClosed() || !connection.isValid(VALIDATION_TIMEOUT_SECONDS)) {
            initiateConnection();
        }

        return connection;
    }

    //método que inicia conexão com banco de dados
    private static void initiateConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
}