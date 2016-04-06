package ExamenFinalM09.Exercici1and2and3;

import java.io.*;
import java.net.Socket;

/**
 * Created by 47257165p on 09/03/16.
 */
public class HiloServidor extends Thread{


    public HiloServidor() {
    }

    public void run(Socket input) {
        try {

            Servidor1_2_3.bufferedWriter.write(Servidor1_2_3.date.getTime()+"LOG: Recibida llamada de cliente.\n");
            //Con el socket creado le enviamos el código html al cliente web
            InputStream iS = input.getInputStream();
            OutputStream oS = input.getOutputStream();

            //Creamos el file a través del html
            File file = new File ("/home/47257165p/IdeaProjects/Procesos y servicios/src/ExamenFinalM09/Exercici1and2and3/html1.html");

            BufferedReader br = new BufferedReader(new FileReader (file));
            String html = "";

            //Escribimos un string con el código del html recibido
            while (br.readLine()!=null)
            {
                html=html+br.readLine();
            }

            //Añadimos la ip tal y como pide el ejercicio 2 y enviamos el código html al cliente
            html=html+"\n<b>IP = "+input.getInetAddress()+"</b>";
            oS.write(html.getBytes());
            Servidor1_2_3.bufferedWriter.write(Servidor1_2_3.date.getTime()+"LOG: Información enviada.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
