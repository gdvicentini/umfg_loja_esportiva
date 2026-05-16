package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/loja_esportiva";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
//    private static final String URL = "jdbc:postgresql://localhost:5432/loja_esportiva";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "123";
//
//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao conectar no banco", e);
//        }
//    }
}