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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }
}
