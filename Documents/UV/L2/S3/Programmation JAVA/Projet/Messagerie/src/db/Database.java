package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/Messagerie"; // Change l'URL si nécessaire
    private static final String USER = "postgres"; // Remplace par ton nom d'utilisateur
    private static final String PASSWORD = "123456AZERTY"; // Remplace par ton mot de passe

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}