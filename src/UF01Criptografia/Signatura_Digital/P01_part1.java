package UF01Criptografia.Signatura_Digital;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.security.*;
//import javax.crypto; -> cypher

/**
 * Created by Dionis on 25/03/2016. Properly modified by Alejandro ;)
 */
public class P01_part1 {

    public static final String PRIVATE_KEY_FILE = "private.key";

    public static final String FITXER_PLA = "Sparring.pdf";
    public static final String FITXER_SIGNAT = "firmat.pdf";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {

        KeyPair keyPair;
        PrivateKey prik;

        File f = new File(FITXER_PLA);

        if(!Utils.areKeysPresent())
        {
            keyPair = Utils.generateKey();
            prik = keyPair.getPrivate();
        }
        else
        {
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            prik = (PrivateKey) inputStream.readObject();
        }

        byte[] digestionat = Utils.digestiona(f,"MD5");
        byte[] encryptdigestionat = Utils.signar(digestionat,prik);
        System.out.println("Longitud del fitxer: "+f.length());
        System.out.println("Longitud de la firma: "+encryptdigestionat.length);
        Utils.write(FITXER_SIGNAT,Utils.concatenateByteArrays(Utils.read(f),encryptdigestionat));
    }

}
