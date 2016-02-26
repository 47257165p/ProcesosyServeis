package CalculadoraMultiHilo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by 47257165p on 26/02/16.
 */
public class HiloTrabajo extends Thread {

    public HiloTrabajo() {
    }

    public void run(Socket input) {
        try {
            InputStream iS = input.getInputStream();
            OutputStream oS = input.getOutputStream();


            //Creamos un array de bytes para parsearlo y limpiarlo
            byte[] mensaje = new byte[20];
            iS.read(mensaje);

            int contador = 0;

            for (byte aMensaje : mensaje) {
                if (aMensaje == 0) {
                    break;
                } else {
                    contador++;
                }
            }
            byte[] mensajeLimpio = new byte[contador];

            System.arraycopy(mensaje, 0, mensajeLimpio, 0, mensajeLimpio.length);

            String mensajeLimpioString = new String(mensajeLimpio);
            System.out.println("Cálculo a realizar = " + mensajeLimpioString);

            String resultado = StringEngineCalc(mensajeLimpioString);

            //tras parsearlo y realizar la operación lo enviamos al enviarCorreo
            oS.write(resultado.getBytes());
            System.out.println("Resultado del cálculo: " + resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que se encarga de realizar la operación
    public static String StringEngineCalc(String mensaje) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String calculo = "";
        try {
            calculo = engine.eval(mensaje).toString();
        } catch (ScriptException e) {
            System.out.println("Error al realizar el cálculo.");
        }
        return calculo;
    }
}

