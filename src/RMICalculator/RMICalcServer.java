package RMICalculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 Created by EL TITO ALEJANDRO on 02/03/2016.
 */
public class RMICalcServer implements RMIServerInterficie {

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
        RMICalcServer serverObject = new RMICalcServer();

        try{
            reg.rebind("interfaz",(RMIServerInterficie) UnicastRemoteObject.exportObject(serverObject,0));
        }catch (Exception e){
            System.out.println("No s'ha pogut inscriure l'objecte servidor");
            e.printStackTrace();
        }
    }
    public static String stringEngineCalc(String mensaje) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String calculo = "";
        try {
            calculo = engine.eval(mensaje).toString();
        } catch (ScriptException e) {
            System.out.println("Error al realizar el cálculo.");
        }
        return calculo;
    }

    @Override
    public String suma(String mensaje) throws RemoteException {
        return stringEngineCalc(mensaje);
    }

    @Override
    public String resta(String mensaje) throws RemoteException {
        return stringEngineCalc(mensaje);
    }

    @Override
    public String division(String mensaje) throws RemoteException {
        return stringEngineCalc(mensaje);
    }

    @Override
    public String multiplicacion(String mensaje) throws RemoteException {
        return stringEngineCalc(mensaje);
    }
}
