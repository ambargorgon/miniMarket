package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Balance {
    private static final Logger logger = LogManager.getLogger(Balance.class);
    private Minimarket minimarket;
    private float monto;
    private String motivo;

    public Balance(){};

    public void sumarVentasDiarias(){
        Scanner scanner = new Scanner(System.in);
        LocalDate fecha = null;

        while (fecha == null) {
            try {
                System.out.println("Ingrese una fecha (dd-MM): ");
                String inputFecha = scanner.nextLine();
                // Añadir el año actual a la fecha ingresada para crear un objeto LocalDate
                String inputFechaConAnio = inputFecha + "-" + LocalDate.now().getYear();
                // Compatibilizar la fecha ingresada
                DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fecha = LocalDate.parse(inputFechaConAnio, fullFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Por favor, ingrese la fecha en el formato dd-MM.");
            }
        }
        // Query SQL para obtener las filas del dia indicado
        String query = "SELECT * FROM BALANCE WHERE MOTIVO = 'Venta' AND FECHA = ?";
        ResultSet rs = null;
        //Ejecucion de query
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            logger.info("Accediendo a base de datos..");
            // Establecer el parámetro de fecha en el query
            pst.setDate(1, java.sql.Date.valueOf(fecha));
            rs = pst.executeQuery();
            //IMPRIMIR
            System.out.println("---------VENTAS DEL DIA:----------");
            imprimir(rs);
            System.out.println("-------------------------------------");
        } catch(Exception e){
            logger.error(e);
        }
    };

    public void sumarVentasMensuales() {
        Scanner scanner = new Scanner(System.in);
        YearMonth yearMonth = null;

        while (yearMonth == null) {
            try {
                System.out.println("Ingrese un mes (MM): ");
                String inputMes = scanner.nextLine();
                // Añadir el año actual al mes ingresado
                String inputMesConAnio = inputMes + "-" + LocalDate.now().getYear();
                // Crear un YearMonth a partir del mes y año
                yearMonth = YearMonth.parse(inputMesConAnio, DateTimeFormatter.ofPattern("MM-yyyy"));
            } catch (DateTimeParseException e) {
                logger.error("Mes inválido. Por favor, ingrese el mes en el formato MM.");
            }
        }

        // Obtener el primer y último día del mes
        LocalDate primerDiaDelMes = yearMonth.atDay(1);
        LocalDate ultimoDiaDelMes = yearMonth.atEndOfMonth();

        // Query SQL para obtener las filas
        String query = "SELECT * FROM BALANCE WHERE MOTIVO = 'Venta' AND FECHA BETWEEN ? AND ?";
        ResultSet rs = null;
        //Ejecucion de query
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            logger.info("Accediendo a base de datos..");

            // Establecer los parámetros de fecha en el query
            pst.setDate(1, java.sql.Date.valueOf(primerDiaDelMes));
            pst.setDate(2, java.sql.Date.valueOf(ultimoDiaDelMes));
            rs = pst.executeQuery();
            System.out.println("---------VENTAS DEL MES:----------");
            imprimir(rs);
            System.out.println("-------------------------------------");
        } catch (Exception e) {
            logger.error(e);
        }
    }
    public void imprimir(ResultSet rs) throws SQLException {
        //Metodo para imprimir busquedas en tabla
        logger.info("Obteniendo valores de tabla...");
        while (rs.next()) {

            int id = rs.getInt("ID_REGISTRO");

            double monto = rs.getDouble("MONTO");
            LocalDate fechaVenta = rs.getDate("FECHA").toLocalDate();
            System.out.println("ID: " + id + ", Monto: " + monto + ", Fecha: " + fechaVenta);
        }
    }
    public void calcularBalance(){
        try {
            ResultSet rs;
            stmt = conn.createStatement();
            //Sumar valores positivos y Sumar valores negativos en variables
            String sql = "SELECT SUM(CASE WHEN Monto >= 0 THEN Monto ELSE 0 END) AS suma_positivos, " +
                    "SUM(CASE WHEN Monto < 0 THEN Monto ELSE 0 END) AS suma_negativos " +
                    "FROM BALANCE";
            rs = stmt.executeQuery(sql);
            logger.info("Accediendo a base de datos..");
            if (rs.next()) {
                double sumaPositivos = rs.getDouble("suma_positivos");
                double sumaNegativos = rs.getDouble("suma_negativos");
                //Encontrar balance
                double resultadoFinal = sumaPositivos + sumaNegativos;
                System.out.println("***************************");
                System.out.println("Ganancias Totales: "+ sumaPositivos);
                System.out.println("Perdidas Totales: "+ sumaNegativos);
                System.out.println("BALANCE FINAL: " + resultadoFinal);
                System.out.println("***************************");
            }
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    };

    /////////////////////Conexion con base de datos-Tabla Balance/////////////////////
    static final String JDBC_DRIVER = "org.h2.Driver";
    static Connection conn = null;
    static Statement stmt = null;

    static {
        try {
             final String DB_URL = "jdbc:h2:tcp://localhost/~/miniMarket";
            //  Credenciales
             final String USER = "sa";
             final String PASS = "1234";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        }catch(Exception e){
            logger.error(e);
        }
    }

    public void subirDatos(String motivo, float monto){
        try {
            logger.info("Insertando datos en la base de datos...");
            stmt = conn.createStatement();
            //Envio de fecha generada automaticamente, motivo y monto de la operacion.
            String sql = "insert into BALANCE " +
                    "(FECHA, MOTIVO, MONTO) values (+ NOW(),'" + motivo +"'," + monto + ")";
            stmt.executeUpdate(sql);
            logger.info("Se insertaron los datos de "+ motivo + " en la tabla Balance");
        }
        catch(Exception e){
            logger.error(e);
        }
    }
}
