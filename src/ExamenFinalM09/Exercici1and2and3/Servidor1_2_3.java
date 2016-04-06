package ExamenFinalM09.Exercici1and2and3;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by 47257165p on 09/03/16.
 */
public class Servidor1_2_3 {

    private static InetSocketAddress address = new InetSocketAddress("localhost", 9090);
    public static BufferedWriter bufferedWriter;
    public static Date date = new Date();

    public static void main(String[] args) {

        File log = new File("/home/47257165p/IdeaProjects/Procesos y servicios/src/ExamenFinalM09/Exercici1and2and3/log.txt");
        if (!log.exists())
        {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(log, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //Creamos el socket con el que nos comunicaremos
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(address);

            bufferedWriter.write(date.getTime()+"LOG: Servidor1_2_3 escuchando.\n");

            System.out.println("Servidor1_2_3 escuchando...");

            //Hacemos un bucle infinito para que el servidor no deje de escuchar y creamos un hilo para cada input.
            while (true) {
                bufferedWriter.write(date.getTime()+"LOG: Servidor1_2_3 esperando.\n");
                Socket input = serverSocket.accept();
                HiloServidor hilo = new HiloServidor();
                hilo.run(input);
                input.close();
            }
        }
        catch (Exception ignored)
        {

        }
    }
}
