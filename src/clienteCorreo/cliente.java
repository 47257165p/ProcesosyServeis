package clienteCorreo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by 47257165p on 24/02/16.
 */
public class cliente {

    private static Scanner in = new Scanner (System.in);
    public static void main(String [] args)
    {
        // Recipient's email ID needs to be mentioned.
        String to = "dremon.iespoblenou@gmail.com";
        //dremon.iespoblenou@gmail.com

        // Sender's email ID needs to be mentioned
        System.out.println("Introduce tu correo");
        String from = in.next();

        System.out.println("Introduce la contraseña de tu correo");
        String password = in.next();

        // Assuming you are sending email from localhost
        String host = "localhost";


        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Alejandro Mola");
            message.setText("Hello there i'm the best");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", from, password);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
