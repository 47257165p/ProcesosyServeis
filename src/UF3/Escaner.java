package UF3;

import java.net.Socket;

/**
 * Created by 47257165p on 10/02/16.
 */
public class Escaner {

    public static void main(String[] args) {
        String host = "localhost";
        Socket socket;

        System.out.println("Lista de IP's abiertas en localhost:\n");

        for (int i = 0; i < 10000; i++) {
            try
            {
                socket = new Socket(host, i);
                System.out.println(i);
            }
            catch (Exception e)
            {

            }
        }
    }
}
