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
        calcularPrecioTotal();
        generarTicket();
    }

    //Constructor minimarket
    public Ticket(Cliente cliente, ArrayList<Producto> productoMarket) {
        this.cliente = cliente;
        this.fechaPedido = new Date();
        this.productoMarket = productoMarket;
        calcularPrecioTotal();
        generarTicket();
    }

    public void generarTicket(){
        System.out.println("========== TICKET DE COMPRA ==========");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Fecha: " + this.fechaPedido);
        System.out.println("Productos comprados:");
        for (Producto producto : productoMarket) {
            System.out.println("- " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
        }
        System.out.println("======================================");
        System.out.println("Precio total: $" + precioTotal);
        System.out.println("======================================");
    }
    private void calcularPrecioTotal() {
        precioTotal = 0;
        for (Producto producto : productoMarket) {
            precioTotal += producto.getPrecio();
        }
    }

    public void sumarTotal(){}; //actualizar precioTotal
    public void actualizarBalance(){};
    public void sumarGanancia(){};
}
