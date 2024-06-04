package org.example;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

public class Balance {
    private Minimarket minimarket;
    private float monto;
    private String motivo;
    private Date fecha;


    //Constructor de cada fila de tabla Balance
    public Balance(float monto, String motivo, Date fecha){
        this.monto = monto;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public void sumarVentasDiarias(){};
    public void sumarVentasMensuales(){};
    public void sumarPerdidas(){};
    public void sumarGanancias(){};
    public void calcularBalance(){};

    //Conexion con base de datos-Tabla Balance
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:tcp://localhost/~/miniMarket";
    //  Credenciales
    static final String USER = "sa";
    static final String PASS = "1234";
    static Connection conn = null;
    static Statement stmt = null;


}
