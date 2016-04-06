package ExamenFinalM09.exercici4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by 47257165p on 09/03/16.
 */
public class HiloServidor4 {

    public HiloServidor4() {
    }

    public void run(Socket input) {
        try {
            InputStream iS = input.getInputStream();
            OutputStream oS = input.getOutputStream();


            //Al recibir una llamada de un cliente comprobamos si este cliente se trata de un get u otro
            byte[] mensaje = new byte[20];
            iS.read(mensaje);

            String mensajeLimpioString = new String(mensaje);
            System.out.println(mensajeLimpioString);

            //Si es get devuelve el código html
            if (mensajeLimpioString.contains("GET"))
            {
                String html = "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Mi título</title>\n" +
                        "    <b>Esto es un servidor WEB!</b>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "</body>\n" +
                        "</html>";

                oS.write(html.getBytes());
            }
            //Si no muestra un mensaje en el log.
            else
            {
                System.out.println("La petición recibida no corresponde a un GET");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
