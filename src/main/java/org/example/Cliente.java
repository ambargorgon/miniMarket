package org.example;

public class Cliente {
    private String nombre;
    private String email;
    private int contadorVisitas;

    public Cliente(String nombre, String email, int contadorVisitas){
        this.nombre = nombre;
        this.email = email;
        this.contadorVisitas = contadorVisitas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContadorVisitas() {
        return contadorVisitas;
    }

    public void setContadorVisitas(int contadorVisitas) {
        this.contadorVisitas = contadorVisitas;
    }
}
