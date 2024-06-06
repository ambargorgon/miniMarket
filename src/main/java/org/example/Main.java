package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Registro del driver jdbc
            Class.forName(Balance.JDBC_DRIVER);

            //Abrir conexión
            System.out.println("Conectándose a la base de datos...");
//            Balance.conn = DriverManager.getConnection(Balance.DB_URL,Balance.USER,Balance.PASS);

            //trabajar con la base
            menuInicial();

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
    public static void menuInicial(){
        Minimarket miniMarket = new Minimarket();

        boolean continuar = true;
        while(continuar){
            Scanner sc = new Scanner(System.in);
            System.out.println("====================");
            System.out.println("ELIGE LA OPERACION QUE DESEA REALIZAR");
            System.out.println("1. Registrar venta Minimarket");
            System.out.println("2. Registrar pedido Cafe");
            System.out.println("3. Pagar");
            System.out.println("4. Consultar Balance");
            System.out.println("5. Salir");
            System.out.println("====================");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    miniMarket.iniciarCompra();
                    break;
                case 2:
                    System.out.println("La opción es 2");
                    break;
                case 3:
                    System.out.println("La opción es 3");
                    break;
                case 4:
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }
    }
}