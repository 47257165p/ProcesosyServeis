package Calculadora;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by 47257165p on 10/02/16.
 */
public class ServidorCalculadora {

    private static InetSocketAddress address = new InetSocketAddress("localhost", 5555);

    public static void main(String[] args) {

        try {
            //Creamos el socket con el que nos comunicaremos
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);

            System.out.println("Servidor1_2_3 calculadora escuchando...");
            Socket listener = serverSocket.accept();

            InputStream iS = listener.getInputStream();
            OutputStream oS = listener.getOutputStream();


            //Creamos un array de bytes para parsearlo y limpiarlo
            byte[] mensaje = new byte[20];
            iS.read(mensaje);

            int contador = 0;

            for (byte aMensaje : mensaje) {
                if (aMensaje == 0) {
                    break;
                } else {
                    contador++;
                }
            }
            byte[] mensajeLimpio = new byte[contador];

            System.arraycopy(mensaje, 0, mensajeLimpio, 0, mensajeLimpio.length);

            String mensajeLimpioString = new String(mensajeLimpio);
            System.out.println("Cálculo a realizar = "+mensajeLimpioString);

            String resultado = StringEngineCalc(mensajeLimpioString);

            //tras parsearlo y realizar la operación lo enviamos al enviarCorreo
            oS.write(resultado.getBytes());
            System.out.println("Resultado del cálculo: "+resultado);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que se encarga de realizar la operación
    public static String StringEngineCalc(String mensaje) {
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
}
