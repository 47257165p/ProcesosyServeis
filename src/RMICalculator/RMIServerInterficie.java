package RMICalculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 Created by EL TITO ALEJANDRO on 02/03/2016.
 */
public interface RMIServerInterficie extends Remote{

    String suma(String mensaje) throws RemoteException;
    String resta(String mensaje) throws RemoteException;
    String division(String mensaje) throws RemoteException;
    String multiplicacion(String mensaje) throws RemoteException;

}
