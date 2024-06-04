package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Registro del driver jdbc
            Class.forName(Balance.JDBC_DRIVER);

            //Abrir conexión
            System.out.println("Conectándose a la base de datos...");
            Balance.conn = DriverManager.getConnection(Balance.DB_URL,Balance.USER,Balance.PASS);

            //trabajar con la base

            //Limpiar el ambiente
            Balance.stmt.close();
            Balance.conn.close();
        } catch(SQLException se) {
            //administrar errores para JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //administrar errores para Class.forName
            e.printStackTrace();
        } finally {
            try{
                if(Balance.stmt!=null) {
                    Balance.stmt.close();
                }
            } catch(SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

            try {
                if(Balance.conn!=null) {
                    Balance.conn.close();
                }
            } catch(SQLException e){
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }
}