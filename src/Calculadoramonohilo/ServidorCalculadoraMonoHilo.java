package Calculadoramonohilo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 47257165p on 10/02/16.
 */
public class ServidorCalculadoraMonoHilo {

    private static InetSocketAddress address = new InetSocketAddress("localhost", 5555);

    public static void main(String[] args) {

        try
        {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);
            Socket listener = serverSocket.accept();

            InputStream iS = listener.getInputStream();
            OutputStream oS = listener.getOutputStream();

            byte [] mensaje = new byte [20];
            iS.read(mensaje);

            int contador = 0;

            for (int i = 0; i<mensaje.length; i++)
            {
                if (mensaje[i] == 0)
                {
                    break;
                }
                else {
                    contador++;
                }
            }
            byte [] mensajeLimpio = new byte [contador];
            for (int i = 0; i < mensajeLimpio.length; i++) {
                mensajeLimpio[i] = mensaje [i];
            }

            String msj = new String(mensajeLimpio);
            System.out.println(mensajeLimpio);

        }
        catch (IOException e)
        {

        }
    }
}
