package org.example;

import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private Balance balance = new Balance();
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
        calcularPrecioTotal("Cafe");
        generarTicket("Cafe");
    }

    //Constructor minimarket
    public Ticket(Cliente cliente, ArrayList<Producto> productoMarket) {
        this.cliente = cliente;
        this.fechaPedido = new Date();
        this.productoMarket = productoMarket;
        calcularPrecioTotal("Minimarket");
        generarTicket("Minimarket");
    }

    public void generarTicket(String origen) {

        System.out.println("========== TICKET DE COMPRA ==========");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Fecha: " + this.fechaPedido);
        System.out.println("======================================");
        if (origen.equals("Minimarket")) {
            System.out.println("Productos:");
            for (Producto producto : productoMarket) {
                System.out.println("- " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
            }
        } else if (origen.equals("Cafe")) {
            System.out.println("Platos:");
            for (Plato plato : comandaCafe) {
                System.out.println("- " + plato.getNombre() + " - Precio: $" + plato.getPrecio());
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("Precio total: $" + precioTotal);
        System.out.println("======================================");

        //Envio al balance de monto y motivo
        balance.subirDatos("Venta", precioTotal);
    }

    private void calcularPrecioTotal(String origen) {
        precioTotal = 0;
        if (origen.equals("Minimarket")) {
            for (Producto producto : productoMarket) {
                precioTotal += producto.getPrecio();
            }
        } else if (origen.equals("Cafe")) {
            for (Plato plato : comandaCafe) {
                precioTotal += plato.getPrecio();
            }
        }
    }

}
