package RMIHello;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Dionis on 22/02/2016.
 */
public class RMIHelloServer implements RMIServerInterficie{

    @Override
    public String dirHello() throws RemoteException {
        System.out.println("Hello world!");
        return "Hello world!";
    }


    public static void main(String[] args) {
        System.out.println("Creant registre d'objectes remots");
        Registry reg = null;

        try{
            reg = LocateRegistry.createRegistry(5555);
            System.out.println(reg);
        }catch (Exception e){
            System.out.println("Error: No s'ha pogut crear el registre");
            e.printStackTrace();
        }

        System.out.println("Creant l'objecte servidor e inscribint-lo en el registre ...");
        RMIHelloServer serverObject = new RMIHelloServer();

        try{
            reg.rebind("dirHello",(RMIServerInterficie) UnicastRemoteObject.exportObject(serverObject,0));
        }catch (Exception e){
            System.out.println("No s'ha pogut inscriure l'objecte servidor");
            e.printStackTrace();
        }
    }
}
