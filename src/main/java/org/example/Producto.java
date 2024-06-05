package org.example;

public class Producto {
    private String proveedor;
    private String nombre;
    private float precio;
    private int stock;

    public Producto(String proveedor, String nombre, float precio, int stock) {
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
