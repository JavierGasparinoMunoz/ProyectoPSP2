package Programa;

import java.io.IOException;

public class Entrada {
    public Entrada() throws IOException, InterruptedException {
        Amazon amazon = null;
        PcComponentes pCcomponentes = null;
        pCcomponentes = new PcComponentes(1); //se crea el hilo pcComponentes
        amazon = new Amazon(1); //se crea el hilo amazon
        pCcomponentes.start();
        amazon.start();

       /* Programa.Cliente cliente = new Programa.Cliente("C:\\Users\\javie\\OneDrive\\Escritorio\\cosa2.txt");
        cliente.enviarArchivo();*/
    }
}
