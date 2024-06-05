package org.example;

import java.util.Scanner;

public class Interfaz {
    public static void menuInicial(){
        boolean continuar = true;
        while(continuar){
            Scanner sc = new Scanner(System.in);
            System.out.println("====================");
            System.out.println("ELIGE LA OPERACION QUE DESEA REALIZAR");
            System.out.println("1. Registrar venta");
            System.out.println("2. Pagar");
            System.out.println("3. Consultar Balance");
            System.out.println("4. Salir");
            System.out.println("====================");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("REGISTRAR VENTA");
                    System.out.println("1. Minimarket");
                    System.out.println("2. Cafeteria");
                    System.out.println("3. Volver atras");
                    break;
                case 2:
                    System.out.println("La opción es 2");
                    break;
                case 3:
                    System.out.println("La opción es 3");
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }
    };
}
