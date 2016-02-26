package clienteCorreo;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by 47257165p on 26/02/16.
 */
public class consultarCorreos {


    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args)
    {
        // Cogemos las propiedades del sistema y introducimos nuevas opciones
        Properties properties = System.getProperties();
        properties.put("mail.pop.host", "pop.gmail.com");
        properties.put("mail.pop.port", "995");
        properties.put("mail.pop.starttls.enable", "true");

        try
        {
            //Creamos la sesión con las propiedades
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("pop3s");

            //Cogemos el usuario y contraseña del usuario
            System.out.println("Introduce el correo electrónico:");
            String usuario = in.next();

            System.out.println("Introduce el correo electrónico:");
            String contraseña = in.next();

            //Nos conectamos a la cuenta de correo y cogemos la carpeta llamada Inbox cuyo contenido es la bandeja de entrada
            store.connect("pop.gmail.com", usuario, contraseña);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            //creamos el mensaje con el contenido de cada correo
            Message[] mensaje = folder.getMessages();

            System.out.println("Número de mensajes: "+mensaje.length);

            //Imprimimos los mensajes
            for (Message aMensaje : mensaje) {
                System.out.println("Mensaje de: " + aMensaje.getFrom()[0] + "\nAsunto: " + aMensaje.getSubject() + "\nContendio: " + aMensaje.getContent());
            }
            folder.close(false);
            store.close();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
}