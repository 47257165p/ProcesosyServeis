package ExamenFinalM09.exercici4;

import CalculadoraMultiHilo.HiloTrabajo;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 47257165p on 09/03/16.
 */
public class Servidor_4 {

    private static InetSocketAddress address = new InetSocketAddress("localhost", 9090);

    public static void main(String[] args) {

        try {
            //Creamos el socket con el que nos comunicaremos
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);

            System.out.println("Servidor escuchando...");

            while (true) {
                Socket input = serverSocket.accept();
                HiloServidor4 hilo = new HiloServidor4();
                hilo.run(input);
            }
        }
        catch (Exception ignored)
        {

        }
    }
}
