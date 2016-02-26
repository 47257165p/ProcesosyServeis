package CalculadoraMultiHilo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 47257165p on 10/02/16.
 */
public class ClienteCalculadora {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce la operación a realizar:");
        String operacion = in.nextLine();

            try
            {
                //Creamos el socket con el que nos comunicaremos
                Socket socket = new Socket();
                InetSocketAddress address = new InetSocketAddress("localhost", 5555);
                socket.connect(address);
                InputStream iS = socket.getInputStream();
                OutputStream oS = socket.getOutputStream();

                //Enviamos la operación a realizar
                oS.write(operacion.getBytes());


                //Creamos un contador y un array de bytes para limpiarlo cuando recibamos el mensaje del servidor
                Integer contador=0;
                byte[] mensaje = new byte[50];

                iS.read(mensaje);
                for (byte aMensaje : mensaje) {
                    if (aMensaje == 0) {
                        break;
                    } else {
                        contador++;
                    }
                }
                byte[] mensajeLimpio = new byte[contador];
                System.arraycopy(mensaje, 0, mensajeLimpio, 0, mensajeLimpio.length);
                String mensajeLimpioString = new String (mensajeLimpio);

                //Después de limpiar el array de bytes lo mostramos parseándolo a String
                System.out.println("Operación = "+operacion);
                System.out.println("Resultado = "+mensajeLimpioString);
                iS.close();
                oS.close();
                socket.close();
                in.close();
            } catch(IOException ignored){}
        }
}
