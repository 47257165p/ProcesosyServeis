package CalculadoraMultiHilo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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

            System.out.println("Servidor calculadora escuchando...");

            while (true) {
                Socket input = serverSocket.accept();
                HiloTrabajo hilo = new HiloTrabajo();
                hilo.run(input);
            }
        }
        catch (Exception ignored)
        {

        }
    }
}
