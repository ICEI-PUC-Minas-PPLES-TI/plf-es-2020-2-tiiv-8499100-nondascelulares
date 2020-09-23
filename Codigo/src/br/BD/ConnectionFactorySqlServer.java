package br.BD;

import javax.swing.*;
import java.sql.*;

public class ConnectionFactorySqlServer {

    private static final String URL = "jdbc:sqlserver://nondassql.database.windows.net:1433;database=nondasBD;encrypt=true;trustServerCertificate=false;"
                                       +"useBulkCopyForBatchInsert=true"; 
    private static final String USER = "nondasAdm";
    private static final String PASS = "Abc456@#";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {

        	JOptionPane.showMessageDialog(
        		    null, 
        		    "<html><body><p style='width: 200px;'>"+e.getMessage()+"</p></body></html>", 
        		    "Error", 
        		    JOptionPane.ERROR_MESSAGE);
            
            
            throw new RuntimeException("Erro ao Abrir a conexão com o Banco de Dados: " + e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException("Erro ao fechar a conexão com o Banco de Dados: " + e);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException("Erro ao fechar a conexão com o Banco de Dados: " + e);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException("Erro ao fechar a conexão com o Banco de Dados: " + e);
        }
    }
}
