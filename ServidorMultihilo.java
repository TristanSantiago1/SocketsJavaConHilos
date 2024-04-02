package servidormultihilos;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorMultihilo{

   

    public static void main(String[] args){
        int PUERTO = 9000;
        int contador = 0;      

        try(ServerSocket socket = new ServerSocket(PUERTO)){
            System.out.println("Servidor escuchando en el puerto: " + PUERTO + "....");


            while(true){
                contador++;
                GestorHilos cliente = new GestorHilos(socket.accept(), contador);
                Thread hilo = new Thread(cliente);
                hilo.start();
            }

        } catch(IOException ex){
            System.out.println(ex);
        }

    }
  


}