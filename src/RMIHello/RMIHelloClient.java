package RMIHello;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by dremo on 22/02/2016.
 */
public class RMIHelloClient {

    public static void main(String[] args) {
        RMIServerInterficie hello = null;

        try{
            System.out.println("Localitzant registre d'objectes remots ...");
            Registry registry = LocateRegistry.getRegistry("localhost",5555);
            System.out.println("Obtenint l'objecte remot...");
            hello = (RMIServerInterficie) registry.lookup("dirHello");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(hello!= null){
            System.out.println("Realitzant operacions");

            try{
                System.out.println("El resultat és:");
                System.out.println(hello.dirHello());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FI");

    }

}
