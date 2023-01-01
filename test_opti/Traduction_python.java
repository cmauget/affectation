
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
//import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Traduction_python {
    public synchronized static void main(String[] args) {    

        Socket socket;
        //BufferedReader in;
        //PrintWriter out;
        try {
            socket = new Socket("localhost",2020);   
            System.out.println("Demande de connexion");
            /*in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
            String message_distant = in.readLine();
            System.out.println(message_distant);*/
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            int[][] array = {{1,2,3,4},{1,2,3,4},{3,4,2,1},{2,4,1,3}};
            oos.writeObject(array);
            oos.close();
            
            try {
                Thread.sleep(30000); // 30 000 ms = 30 s
            } catch (InterruptedException e) {
                // gérer l'exception si nécessaire
            }
            /*in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
            String message_distant = in.readLine();
            System.out.println(message_distant);*/
            
            socket.close();    
        }catch (UnknownHostException e) {         	
            e.printStackTrace();         
        }catch (IOException e) {
        	e.printStackTrace();
        }
    }
}