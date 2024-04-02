package servidormultihilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;

public class GestorHilos implements Runnable{

    private final Socket cliente;
    PrintWriter outWay;
    BufferedReader inWay;
    int contador;

    public GestorHilos(Socket cliente, int contador) throws IOException{
        this.cliente = cliente;
        this.contador = contador;
        outWay = new PrintWriter(cliente.getOutputStream(), true);
        inWay = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        System.out.println("Conexion recibida del cliente " + contador + ": " + cliente.getInetAddress().getHostAddress());
    }

    @Override
    public void run(){
        try{
            String rutaArchivo = Paths.get("ServidorMultiHilos\\archivote.csv").toAbsolutePath().toString();
            File archivo = new File(rutaArchivo);

            FileReader lectorArchivos;
            lectorArchivos = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(lectorArchivos);

            String lineaDeArchivo;
            while((lineaDeArchivo = buffer.readLine()) !=null){
                outWay.println(lineaDeArchivo);
            }

            outWay.println("AFD");
            buffer.close();
            lectorArchivos.close();

            System.out.println("Cliente " + contador + ": " + inWay.readLine());
          

            outWay.close();
            inWay.close();
            cliente.close();

        }catch (IOException ex){

        }
    }

}