package org.example;

public class Empleado {
    private String nombre;
    private int DNI;
    private float sueldo;

    public Empleado(String nombre, int DNI, float sueldo) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSueldo() {
        return sueldo;
    }

    public int getDNI(){return DNI;}
}
