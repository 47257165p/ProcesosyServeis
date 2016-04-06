package UF01Criptografia.Signatura_Digital;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

/**
 * Created by 47257165p on 06/04/16.
 */
public class Utils {

    public static byte[] digestiona(File f, String algoritme) throws NoSuchAlgorithmException, IOException
    {
        MessageDigest messageDigest = MessageDigest.getInstance(algoritme);
        messageDigest.update(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
        return messageDigest.digest();
    }

    public static boolean areKeysPresent()
    {
        File keyFile = new File("private.key");
        return keyFile.exists();
    }

    public static KeyPair generateKey() throws NoSuchAlgorithmException {

        File privateKey = new File("private.key");
        File publicKey = new File("public.key");

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        try
        {
            saveKeyFile(privateKey, keyPair.getPrivate());
            saveKeyFile(publicKey, keyPair.getPublic());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return keyPair;
    }

    public static byte[] signar(byte[] text, PrivateKey key)
    {

    }

    public static void write(String f, byte[] byteArray)
    {

    }

    public static void concatenateByteArrays()
    {

    }

    public static byte[] read(File file) throws IOException
    {

    }

    public static void saveKeyFile (File file, Key object) throws IOException
    {
        file.createNewFile();

        FileOutputStream fileoutputstream = new FileOutputStream(file, false);
        ObjectOutputStream objectoutputstream = new ObjectOutputStream(fileoutputstream);

        objectoutputstream.writeObject(object);
        objectoutputstream.flush();
        objectoutputstream.close();
        fileoutputstream.flush();
        fileoutputstream.close();
    }
}
