package Programa;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Client {

    public Client(String precios) {
        iniciarCliente(precios);
    }


    public static void iniciarCliente(String precios){
        try {
            String Host = "localhost";
            int puerto = 5556;//puerto remoto

            // Propiedades JSSE)
            System.setProperty("javax.net.ssl.trustStore", "src/AlmacenSrv");
            System.setProperty("javax.net.ssl.trustStorePassword", "1234567");

            System.out.println("PROGRAMA CLIENTE INICIADO....");

            SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);


            // CREO FLUJO DE SALIDA AL SERVIDOR
            DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

            // ENVIO UN SALUDO AL SERVIDOR
            flujoSalida.writeUTF(precios);

            // CREO FLUJO DE ENTRADA AL SERVIDOR
            DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

            // EL servidor ME ENVIA UN MENSAJE
            System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

            // CERRAR STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            Cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
