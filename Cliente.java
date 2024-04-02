import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Cliente{

    public static void main(String[] args){
        int PUERTO = 9000;
        try{
            Socket socket = new Socket("localhost", PUERTO);
            PrintWriter outWay = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inWay = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
            String lineaRecibida;
            while(!(lineaRecibida = inWay.readLine()).equalsIgnoreCase("AFD")){
                System.out.println("Servidor: " + lineaRecibida);
            }

            outWay.println("Recepcion de datos correcta...");

            outWay.close();
            inWay.close();
            socket.close();

        }catch(IOException EX){

        }
    }


}
