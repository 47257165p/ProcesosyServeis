package Cliente_Servidor_Ejemplo; /**
 * Created by dremon on 27/01/16.
 */


/**
 * TCP: Orientado a connexión segura (es decir, todo paquete enviado, tenía
 * un acuse de recibo por parte del receptor. Tanto emisor y receptor se comunican para
 * acordar los envios.
 */

/**
 * UDP: Envio rápido y por consiguiente sin acuse de recibo (a algo a alguna parte). Ejemplos
 * de conexiones UDP son las videoconferencias.
 */

/**
 * Cliente - Servidor1_2_3: Cliente era quien pedia información y el servidor quien tenia una serie
 * de recursos que puede ser solicitados.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket Stream (orientado a connexión segura)
 */

public class ClienteSocketStream {

    public static void main(String[] args) {
        System.out.println("Creado el socket enviarCorreo");
        Socket clienteSocket = new Socket();
        System.out.println("Estableciendo connexión");

        /**
         * Un puerto era un lugar lógico donde escuchar una connexión!
         * 65 mil puertos posibles. Hay algunos que estan reservados.
         * 80 que http
         * 8080 que el https
         */

        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

        try {
            /*
                Este commando connecta el socket con la dirección y el puerto especificados.
             */
            clienteSocket.connect(addr);

            /*
                Los dos comandos siguientes por donde va a escuchar y por donde va a hablar.
             */
            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

            System.out.println("enviando mensaje");

            String mensaje ="Mensaje a enviar!";

            /*Ponemos el mensaje en el canal
            * RECORDAR que hay que ponerlo en bits*/

            os.write(mensaje.getBytes());

            System.out.println("Mensaje enviado!");
            System.out.println("Hay que cerrar los sockets!");

            byte[] adeu = new byte[25];

            is.read(adeu);
            System.out.println(new String(adeu));

            /*Instrucción para cerrar*/
            clienteSocket.close();

            System.out.println("FINAL");



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
