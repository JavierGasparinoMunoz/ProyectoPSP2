package Programa;

import java.io.*;

public class PcComponentes extends Thread {
    int hilo;

    public PcComponentes(int hilo) throws InterruptedException, IOException {
        this.hilo = hilo;
        EjecutarPcComponentes();
        EjecutarLectura();
    }

    private void EjecutarPcComponentes() throws InterruptedException {
        String cmd3 = "start https://www.pccomponentes.com/buscar/?query=auriculares+bluetooth"; //Comando para arrancar pcComponentes
        //Runtime.getRuntime().exec(cmd3);
        ProcessBuilder pb3 = new ProcessBuilder("cmd", "/C", cmd3);
        //pb3.start();
        Process process3;
        try {
            //process3 = Runtime.getRuntime().exec("start https://github.com/");
            process3 = pb3.start();
            InputStream inputstream3 = process3.getInputStream();
            BufferedInputStream bufferedinputstream3 = new BufferedInputStream(inputstream3);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        sleep(2000);
    }

    public static void EjecutarLectura() throws IOException {

        //String command = "powershell.exe  your command";
        //Getting the version
        String command = "powershell.exe ../resources/command2.ps1";
        // Executing the command
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        // Getting the results
        powerShellProcess.getOutputStream().close();
        String total = "";
        String line;
        System.out.println("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));

        while ((line = stdout.readLine()) != null) {
            total += line + "\n";
        }

        stdout.close();
        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));

        stderr.close();
        System.out.println("Done");


        String precios = importarTXT("C:\\Users\\javie\\OneDrive\\Escritorio\\cosa2.txt").toString();
        System.out.println(precios);

        Client cli = new Client(precios);



    }
    public static StringBuilder importarTXT(String ruta) {
        StringBuilder stringB = new StringBuilder();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {

            fr = new FileReader(new File(ruta));
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                stringB.append(linea);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return stringB;
    }
}
