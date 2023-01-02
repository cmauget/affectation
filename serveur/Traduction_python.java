
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
//import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Traduction_python {

    public int port;
    public int[][] cout;
    public int[][] affectation;
    private BufferedReader in;
    private ObjectOutputStream oos;
    public Socket socket;


    public Traduction_python(int port, int[][] cout){

        this.port=port;
        this.cout=cout;

        try{
            System.out.println("Demmarage du socket");
            this.socket = new Socket("localhost",2031);   
            this.in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException e) {         	
            e.printStackTrace();         
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Connecté");

    }

    public void setCout(int[][] cout){
        this.cout=cout;
    }


    public int[][] getAffectation(){
        return this.affectation;
    }


    public void sendCout(){

        try{
            this.oos.writeObject(this.cout);
            this.oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Tableau de cout envoyé");

    }


    public void receiveTab(int nbeleves) {

        String message_distant;
        message_distant="";

        try{
            for (int i=0;i<nbeleves;i++){
                message_distant =message_distant+this.in.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(message_distant);
        
        String input = message_distant;
        input = input.replace("[", "");
        input = input.replace("]", "");
        String[] rows = input.split(" ");
        int[][] array = new int[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++) {
            String[] columns = rows[i].split(",");
            for (int j = 0; j < columns.length; j++) {
                array[i][j] = Integer.parseInt(columns[j]);
            }
        }

        System.out.println("Tableau d'affectation :");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        this.affectation=array; 
    }

    public static int[][] run(int port, int[][] cout){

        Traduction_python t = new Traduction_python(port, cout);
        t.sendCout();
        System.out.println("En attente de réponse");
        t.receiveTab(4);
        try {
        t.socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return t.affectation;
    }

    public static void main(String[] args) {    

        int[][] array2 = {{1,2,3,4},{1,2,3,4},{3,4,2,1},{2,4,1,3}};
        int port=2039;
        int[][] aff;
        aff=run(port, array2);
    }
}