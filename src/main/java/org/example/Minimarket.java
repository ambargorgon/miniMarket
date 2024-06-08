package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Minimarket {
    private static final Logger logger = LogManager.getLogger(Minimarket.class);
    private Balance balance;
    private CafeRestaurante cafe;
    private  ArrayList<Cliente> clientes;
    private ArrayList<Producto> productos;
    private ArrayList<Producto> productosComprados;
    private ArrayList<Empleado> empleados;

    public Minimarket(){
        this.balance = new Balance();
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.productosComprados = new ArrayList<>();
        inicializarArreglos();
        this.cafe = new CafeRestaurante(clientes);  //el café comparte el mismo arreglo de clientes que tiene minimarket
    }

    public void iniciarCompra(){
        //Envio de cliente al azar y array de productos comprados
        new Ticket(clientes.get(new Random().nextInt(10)), obtenerProductos());
        //vaciar arreglo
        productosComprados.clear();
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
        logger.info("Compra Realizada");
        return productosComprados;
    }
        public void menuPagar(){
            Scanner sc = new Scanner(System.in);

            System.out.println("*** PAGAR ***");
            System.out.println("¿Qué desea pagar?");
            System.out.println("1. Sueldo empleado");
            System.out.println("2. Proveedor");
            System.out.println("3. Volver al Menu");
            System.out.println("=================");
            int opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    pagarSueldo();
                    break;
                case 2:
                    pagarProveedor();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }

    public void menuBalance(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*** CONSULTAR BALANCE ***");
        System.out.println("¿Qué desea consultar?");
        System.out.println("1. Ventas Diarias");
        System.out.println("2. Ventas Mensuales");
        System.out.println("3. Balance");
        System.out.println("4. Volver al Menu");
        System.out.println("=================");
        int option = sc.nextInt();

        switch (option){
            case 1:
                balance.sumarVentasDiarias();
                break;
            case 2:
                balance.sumarVentasMensuales();
                break;
            case 3:
                balance.calcularBalance();
            case 4:
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }
        public void pagarSueldo(){
            Scanner sc = new Scanner(System.in);
            System.out.println("*** PAGAR SUELDO ***");

            for (int i=1; i <= empleados.size(); i++){
                System.out.println(i + ". " + empleados.get(i-1).getNombre() + " DNI: "+empleados.get(i-1).getDNI()+ "\n");
            }

            int opcion = sc.nextInt();

            balance.subirDatos("Pago", -empleados.get(opcion-1).getSueldo());    //agregar fila a la tabla de balance
            System.out.println("Pago realizado.");
            logger.info(-empleados.get(opcion-1).getSueldo()+" debitados");
        }

        public void pagarProveedor(){
            Scanner sc = new Scanner(System.in);
            System.out.println("*** PAGAR PROVEEDOR ***");

            for (int i=1; i <= productos.size(); i++){
                System.out.println(i + ". " + productos.get(i-1).getProveedor() + "Producto: "+productos.get(i-1).getNombre()+"\n");
            }

            int opcion = sc.nextInt();

            float totalAPagar = -(productos.get(opcion-1).getPrecio() *  productos.get(opcion-1).getStock());
            System.out.println("Pago de $"+totalAPagar*-1+" realizado. Stock de [" + productos.get(opcion-1).getNombre() + "] actualizado.");

            logger.info(-(productos.get(opcion-1).getPrecio() *  productos.get(opcion-1).getStock()));
            balance.subirDatos("Pago", totalAPagar);    //agregar fila a la tabla de balance

        }

        public void consultarEstadisticas(){
            Scanner sc = new Scanner(System.in);

            System.out.println("*** CONSULTAR ESTADISTICAS ***");
            System.out.println("1. Plato más pedido");
            System.out.println("2. Cliente más frecuente");
            System.out.println("3. Volver al menu");
            System.out.println("--------------------");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    obtenerPlato();
                    break;
                case 2:
                    obtenerCliente();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

            System.out.println("--------------------");

        }

    public void obtenerCliente(){
        Cliente mayor = clientes.get(0);
        for (Cliente cliente : clientes) {
            if (cliente.getContadorVisitas() > mayor.getContadorVisitas()) {
                mayor = cliente;
            }
        }
        System.out.println("CLIENTE MAS FRECUENTE");
        System.out.println("Nombre: "+ mayor.getNombre()+ " Email: "+ mayor.getEmail()+ " Cantidad de Visitas: "+ mayor.getContadorVisitas());
        System.out.println("-----------------------");
    }

    public void obtenerPlato(){
        Plato mayor = cafe.getMenu().get(0);
        for (Plato plato : cafe.getMenu()) {
            if (plato.getCantVecesPedido() > mayor.getCantVecesPedido()) {
                mayor = plato;
            }
        }
        System.out.println("PLATO MAS PEDIDO");
        System.out.println("Nombre: "+ mayor.getNombre()+ " Precio: "+ mayor.getPrecio()+ " Veces Pedido: "+ mayor.getCantVecesPedido());
        System.out.println("-----------------------");
    }

    public void inicializarArreglos(){
        logger.info("Arreglos Inicializados");
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

        empleados.add(new Empleado("Pedro García", 12345678, 25000));
        empleados.add(new Empleado("María López", 87654321, 28000));
        empleados.add(new Empleado("Juan Martínez", 13579246, 30000));
    }

    // GETTERS & SETTER
    public CafeRestaurante getCafe() {
        return cafe;
    }
}


