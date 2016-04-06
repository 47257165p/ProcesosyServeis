package RMICalculator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 Created by EL TITO ALEJANDRO on 02/03/2016.
 */
public class RMICalcClient {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {


        RMIServerInterficie calcular;
        boolean keep = true;
        while (keep) {
            try {
                System.out.println("Localitzant registre d'objectes remots ...");
                Registry registry = LocateRegistry.getRegistry("localhost", 5555);
                calcular = (RMIServerInterficie) registry.lookup("interfaz");

                System.out.println("-ELIJA UNA OPCIÓN-\n1. Sumar\n2. Restar\n3. Dividir\n4. Multiplicar\n5. Salir");
                String respuesta = in.next();
                System.out.println("Obtenint l'objecte remot...");
                if (calcular != null) {
                    System.out.println("Introduce la operación a realizar: ");
                    String input = in.next();
                    System.out.println("Realitzant operacions...");
                    System.out.println("El resultat és:");
                    switch (respuesta) {
                        case "1":
                            System.out.println(calcular.suma(input));
                            break;
                        case "2":
                            System.out.println(calcular.resta(input));
                            break;
                        case "3":
                            System.out.println(calcular.division(input));
                            break;
                        case "4":
                            System.out.println(calcular.multiplicacion(input));
                            break;
                        case "5":
                            keep = false;
                            break;
                    }
                }

            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FIN. GRACIAS POR UTILIZAR EL PROGRAMA");
    }
}
