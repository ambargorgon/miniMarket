package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    //Declaracion de Log4j
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            // Registro del driver jdbc
            Class.forName(Balance.JDBC_DRIVER);

            //Abrir conexión
            logger.info("Conectándose a la base de datos...");
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
                logger.error("ERROR: " + e.getMessage());
            }
            try {
                if(Balance.conn!=null) {
                    Balance.conn.close();
                }
            } catch(SQLException e){
                logger.error("ERROR: " + e.getMessage());
            }
        }
        logger.info("Adios!");
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
            System.out.println("5. Consultar estadísticas");
            System.out.println("6. Salir");
            System.out.println("====================");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    miniMarket.iniciarCompra();
                    break;
                case 2:
                    miniMarket.getCafe().iniciarCompraCafe();
                    break;
                case 3:
                    miniMarket.menuPagar();
                    break;
                case 4:
                    miniMarket.menuBalance();
                    break;
                case 5:
                    miniMarket.consultarEstadisticas();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }
    }
}