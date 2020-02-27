package Programa;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class Cliente extends JFrame {

    JTextArea texto;
    JButton cargar;
    Container container;
    String precios;

    private ServerSocket servidor = null;

    public Cliente(String precios) throws IOException {
        this.precios = precios;
        servidor = new ServerSocket(5353);

        System.out.println("Esperando recepcion de archivos...");

        configurarVentana();
        iniciarServidor();
    }

    private void configurarVentana() {
        instancias();
        configurarContainer();
        this.setSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void configurarContainer() {
        container.setLayout(new BorderLayout());
        container.add(new JScrollPane(texto), BorderLayout.CENTER);
        container.add(cargar, BorderLayout.SOUTH);
    }

    private void instancias() {
        container = this.getContentPane();
        texto = new JTextArea();
        cargar = new JButton("Cargar");
    }

    public void iniciarServidor() {

        cargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

            }
        });

    }
}
