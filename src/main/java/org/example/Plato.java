package org.example;

public class Plato {
    private String nombre;
    private float precio;
    private int cantVecesPedido;

    public Plato(String nombre, float precio, int cantVecesPedido) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantVecesPedido = cantVecesPedido;
    }

    public String getNombre() {
        return nombre;
    }


    public float getPrecio() {
        return precio;
    }

    public int getCantVecesPedido() {
        return cantVecesPedido;
    }

}
