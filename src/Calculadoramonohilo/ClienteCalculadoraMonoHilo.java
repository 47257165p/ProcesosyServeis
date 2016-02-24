package Calculadoramonohilo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 47257165p on 10/02/16.
 */
public class ClienteCalculadoraMonoHilo {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce la operación a realizar:");
        String operacion = in.nextLine();

            try
            {
                Socket socket = new Socket();
                InetSocketAddress address = new InetSocketAddress("localhost", 5555);
                socket.connect(address);
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                output.write(operacion.getBytes());


                Integer contador=0;
                byte[] mensaje = new byte[50];

                input.read(mensaje);
                for (byte aMensaje : mensaje) {
                    if (aMensaje == 0) {
                        break;
                    } else {
                        contador++;
                    }
                }
                byte[] mensajeLimpio = new byte[contador];
                System.arraycopy(mensaje, 0, mensajeLimpio, 0, mensajeLimpio.length);
            } catch(IOException ignored){}
        }
}
