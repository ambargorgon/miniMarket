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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantVecesPedido() {
        return cantVecesPedido;
    }

    public void setCantVecesPedido(int cantVecesPedido) {
        this.cantVecesPedido = cantVecesPedido;
    }
}
