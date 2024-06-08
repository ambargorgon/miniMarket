package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CafeRestaurante {
    private ArrayList<Plato> menu;
    private ArrayList<Cliente> clientes;
    private ArrayList<Plato> comandaPedida;

    public CafeRestaurante(ArrayList<Cliente> clientes){
        this.menu = new ArrayList<Plato>();
        this.clientes = clientes;
        this.comandaPedida = new ArrayList<Plato>();
        inicializarArreglos();
    }

    public void inicializarArreglos(){
        menu.add(new Plato("Ensalada César", 2500,2));
        menu.add(new Plato("Huevos revueltos", 2000,8));
        menu.add(new Plato("Waffles salados", 4500,5));
        menu.add(new Plato("Tostadas con palta", 5000,2));
        menu.add(new Plato("Tostado de JyQ", 4000,4));
    }
    public void iniciarCompraCafe(){
        //Envio de cliente al azar y array de comanda pedida
        new Ticket(obtenerComanda(), clientes.get(new Random().nextInt(10)));
        //vaciar arreglo
        comandaPedida.clear();
    }

    public ArrayList<Plato> obtenerComanda(){
        Scanner sc = new Scanner(System.in);
        Scanner scLine = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){
            continuar = false;
            System.out.println("PLATOS DEL MENÚ:");
            for(int i = 0; i <= menu.size()-1; i++){
                //MUESTRA DE PRODUCTOS
                System.out.println((i+1)+". "+ menu.get(i).getNombre() +" - $"+ menu.get(i).getPrecio());
            }

            System.out.println("Ingrese el numero del producto que desea añadir");
            int option = sc.nextInt();
            //AÑADIR PRODUCTO A NUEVO ARRAY
            comandaPedida.add(menu.get(option-1));
            System.out.println("-----------------------------");
            System.out.println("[" + menu.get(option-1).getNombre() +"] añadido al carrito.");
            System.out.println("-----------------------------");

            System.out.println("Desea añadir otro producto? Ingrese s/n");
            String salir = scLine.nextLine();

            if(salir.equalsIgnoreCase("s")){
                continuar = true;
            }
        }
        return comandaPedida;
    }

    //GETTERS & SETTERS
    public ArrayList<Plato> getMenu() {
        return menu;
    }

}
