package Programa;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    private String nombreArchivo = "";

    public Server(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public static void main(String[] args) throws IOException {
        int puerto = 5556;

        System.setProperty("javax.net.ssl.keyStore", "src/AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory
                .getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact
                .createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
        DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE

        for (int i = 1; i < 5; i++) {
            clienteConectado = (SSLSocket) servidorSSL.accept();
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());

            // EL CLIENTE ME ENVIA UN MENSAJE
            String recibido = flujoEntrada.readUTF();

            ArrayList<String> precios = new ArrayList<>();
            String precio = "";

            for (int z = 0; z < recibido.length(); z++) {
                int c = recibido.charAt(z);
                if (c == 65533) {
                    precios.add(precio + "€");
                    precio = "";

                }else{
                    precio += recibido.charAt(z);
                }
            }

            //OPEN SWING

            JFrame frame = new JFrame();
            JPanel p = new JPanel();

            p.setLayout(new BorderLayout());
            JTextArea txtArea = new JTextArea();
                p.add(new JScrollPane(txtArea), BorderLayout.CENTER);

            for(int z = 2; z < precios.size(); z++){
                txtArea.append(precios.get(z) +"\n");
            }

            frame.add(p);
            frame.setSize(new Dimension(1000,600));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setTitle("Servidor");
            frame.setVisible(true);


            //CLOSE SWING


            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            // ENVIO UN SALUDO AL CLIENTE
            flujoSalida.writeUTF("Saludos al cliente del servidor");
        }
        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();


    }

}