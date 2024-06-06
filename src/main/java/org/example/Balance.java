package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Balance {
    private Minimarket minimarket;
    private float monto;
    private String motivo;


    //Constructor de cada fila de tabla Balance
    public Balance(float monto, String motivo){
        this.monto = monto;
        this.motivo = motivo;
        //Subir valores a tabla en BD
        subirDatos();
    }

    public void sumarVentasDiarias(){};
    public void sumarVentasMensuales(){};
    public void sumarPerdidas(){};
    public void sumarGanancias(){};
    public void calcularBalance(){};

    //Conexion con base de datos-Tabla Balance
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
            e.printStackTrace();
        }
    }

    public void subirDatos(){
        try {

            System.out.println("Insertando datos en la base de datos...");
            stmt = conn.createStatement();
            //Envio de fecha generada automaticamente, motivo y monto de la operacion.
            String sql = "insert into BALANCE " +
                    "(FECHA, MOTIVO, MONTO) values (+ NOW(),'" + this.motivo +"'," + this.monto + ")";
            stmt.executeUpdate(sql);
            System.out.println("Se insertaron los datos de "+ this.motivo);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
