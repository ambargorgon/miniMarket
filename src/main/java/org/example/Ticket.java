package org.example;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    static private Balance balance;
    private Cliente cliente;
    private Date fechaPedido;
    private ArrayList<Plato> comandaCafe;
    private ArrayList<Producto> productoMarket;
    private float precioTotal;

    //Constructor desde Cafeteria
    public Ticket(ArrayList<Plato> comandaCafe, Cliente cliente) {
        this.cliente = cliente;
        this.fechaPedido = new Date();
        this.comandaCafe = comandaCafe;
    }

    //Constructor minimarket
    public Ticket(Cliente cliente, ArrayList<Producto> productoMarket) {
        this.cliente = cliente;
        this.fechaPedido = new Date();
        this.productoMarket = productoMarket;
    }

    public void sumarTotal(){}; //actualizar precioTotal
    public void actualizarBalance(){};
    public void sumarGanancia(){};
}
