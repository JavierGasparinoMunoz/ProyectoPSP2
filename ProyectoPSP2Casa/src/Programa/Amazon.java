package Programa;

import java.io.*;

public class Amazon extends Thread {
    int hilo;

    public Amazon(int hilo) throws InterruptedException, IOException {
        this.hilo = hilo;
        EjecutarAmazon();
        sleep(50000);
        EjecutarLectura();

    }

    private void EjecutarAmazon() throws InterruptedException {
        String cmd3 = "start https://www.amazon.es/s?k=auriculares+bluetooth"; //Comando para arrancar amazon
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

    public static void EjecutarLectura() throws IOException, InterruptedException {
        //String command = "powershell.exe  your command";
        //Getting the version
        String command = "powershell.exe  \"C:\\Users\\javie\\OneDrive\\Escritorio\\command3.ps1\" ";
        // Executing the command
        Process powerShellProcess = Runtime.getRuntime().exec(command);
        // Getting the results
        powerShellProcess.getOutputStream().close();
        String line;
        System.out.println("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                powerShellProcess.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
        }
        stdout.close();
        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
                powerShellProcess.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
        stderr.close();
        System.out.println("Done");
    }
}


