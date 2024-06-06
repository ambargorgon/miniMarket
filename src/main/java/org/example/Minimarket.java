package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Minimarket {
    private CafeRestaurante cafe;
    private  ArrayList<Cliente> clientes;
    private ArrayList<Producto> productos;
    private ArrayList<Producto> productosComprados;
    private ArrayList<Empleado> empleados;

    public Minimarket(){
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.productosComprados = new ArrayList<>();
        inicializarArreglos();
    }

    public void iniciarCompra(){
        //Envio de cliente al azar y array de productos comprados
        new Ticket(clientes.get(new Random().nextInt(10)), obtenerProductos());
        //vaciar arreglo
        productosComprados.clear();
    }

    public void comprarProducto(){} //actualizar balance
    public void pagarSueldos(){}  //actualizar balance
    public void actualizarBalance(){}


    public void inicializarArreglos(){
        productos.add(new Producto("VerdeBio", "Te en hebras", 499F, 50));
        productos.add(new Producto("EcoFarm", "Aceite de Coco", 849.99F, 80));
        productos.add(new Producto("NaturAlimentos", "Miel", 600.99F, 100));
        productos.add(new Producto("BioFrutas", "Café en grano", 200.49F, 60));
        productos.add(new Producto("EcoHealth", "Taza de cerámica", 120.99F, 40));

        clientes.add(new Cliente("Juan Pérez", "juan@example.com", 5));
        clientes.add(new Cliente("María López", "maria@example.com", 3));
        clientes.add(new Cliente("Carlos Rodríguez", "carlos@example.com", 7));
        clientes.add(new Cliente("Ana Martínez", "ana@example.com", 2));
        clientes.add(new Cliente("Pedro Gómez", "pedro@example.com", 10));
        clientes.add(new Cliente("Laura Díaz", "laura@example.com", 8));
        clientes.add(new Cliente("Roberto Sánchez", "roberto@example.com", 4));
        clientes.add(new Cliente("Sofía Ramírez", "sofia@example.com", 6));
        clientes.add(new Cliente("David González", "david@example.com", 1));
        clientes.add(new Cliente("Elena Fernández", "elena@example.com", 9));

        empleados.add(new Empleado("Pedro García", 12345678, 250000));
        empleados.add(new Empleado("María López", 87654321, 280000));
        empleados.add(new Empleado("Juan Martínez", 13579246, 300000));
    }

    public ArrayList<Producto> obtenerProductos(){
        Scanner sc = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);
        boolean continuar = true;
        while(continuar){
            continuar = false;
            System.out.println("PRODUCTOS DISPONIBLES:");
            for(int i = 0; i <= productos.size()-1; i++){
                //MUESTRA DE PRODUCTOS
                System.out.println((i+1)+". "+ productos.get(i).getNombre() +" - $"+ productos.get(i).getPrecio());
            }
            System.out.println("Ingrese el numero del producto que desea añadir");
            int option = sc.nextInt();
            //AÑADIR PRODUCTO A NUEVO ARRAY
            productosComprados.add(productos.get(option-1));
            System.out.println("-----------------------------");
            System.out.println(productos.get(option-1).getNombre() +" añadido al carrito.");
            System.out.println("-----------------------------");
            System.out.println("Desea añadir otro producto? Ingrese s/n");
            String salir = scLine.nextLine();
            if(salir.equalsIgnoreCase("s")){
                continuar = true;
            }
        }
        return productosComprados;
    }

    public CafeRestaurante getCafe() {
        return cafe;
    }

    public void setCafe(CafeRestaurante cafe) {
        this.cafe = cafe;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}


