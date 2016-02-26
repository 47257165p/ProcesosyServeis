package Cliente_Servidor_Ejemplo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dremon on 27/01/16.
 */
public class ServidorSocketStream {

    public static void main(String[] args) {

        System.out.println("Creando servidor");

        /*El constructor del serversocket es diferente que el del enviarCorreo.
        Tiene métodos que el normal no tiene.
         */

        try {
            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost",5555);

            /*
            bind: significa vincular
             */
            /*
            El servidor escucha en la dirección que le digamos.
             */
            /**
             * El servidor tiene que ser la misma màquina que ejecuta el programa servidor?
             */

            serverSocket.bind(addr);
            System.out.println("Escuchando");

            /**
             * Aquí és donde el servidor se quedará escuchando!
              */

            Socket socketdeescucha = serverSocket.accept();

            System.out.println("Se ha recibido una llamada!");


            InputStream is = socketdeescucha.getInputStream();
            OutputStream os = socketdeescucha.getOutputStream();


            byte[] mensaje = new byte[25];

            is.read(mensaje);

            System.out.println("mensaje recibido: "+new String(mensaje));


            System.out.println("Cerrando!");

            os.write("Adeu!".getBytes());

            socketdeescucha.close();
            serverSocket.close();

            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
