
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
//import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Traduction_python {

    public int port;
    public int[][] cout;
    public int[][] affectation;
    public int[][] affectation_sec;
    private BufferedReader in;
    private ObjectOutputStream oos;
    public Socket socket;

    public Traduction_python(){
        this.port=0;
    }

    public Traduction_python(int port, int[][] cout){

        this.port=port;
        this.cout=cout;

        try{
            System.out.println("Demmarage du socket");
            this.socket = new Socket("localhost",port);   
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

    public int[][] getAffectationsec(){
        return this.affectation_sec;
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


    public void receiveTab(int nbeleves, int parcours) {

        String message_distant;
        int nb_cours;
        message_distant="";

        try{
            for (int i=0;i<(nbeleves*2);i++){
                message_distant =message_distant+this.in.readLine();
                //System.out.println(i);
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
        System.out.println(input);
        System.out.println(nbeleves);
        int[][] array = new int[nbeleves*2][8];
        for (int i = 0; i < nbeleves*2; i++) {
            String[] columns = rows[i].split(",");
            for (int j = 0; j < 8; j++) {
                array[i][j] = Integer.parseInt(columns[j]);
            }
        }

        

       
        this.affectation=Arrays.copyOfRange(array, 0, nbeleves);
        System.out.println("Tableau d'affectation :");
        for (int i = 0; i < this.affectation.length; i++) {
            for (int j = 0; j < this.affectation[i].length; j++) {
                System.out.print(this.affectation[i][j] + " ");
            }
            System.out.println();
        }

    
        this.affectation_sec=Arrays.copyOfRange(array, nbeleves, 2*nbeleves);
        System.out.println("Tableau d'affectation :");
        for (int i = 0; i < this.affectation_sec.length; i++) {
            for (int j = 0; j < this.affectation_sec[i].length; j++) {
                System.out.print(this.affectation_sec[i][j] + " ");
            }
            System.out.println();
        }
        
    }

    public static Traduction_python run_pref(int port, int[][] cout, int nb){

        Traduction_python t = new Traduction_python(port, cout);
        t.sendCout();
        System.out.println("En attente de réponse");
        t.receiveTab(nb,1);
        try {
        t.socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    

    public static void main(String[] args) {    

        
        int[][] array2 = {{1,2,3,4,1,2,3,4},{3,4,2,1,2,4,1,3}};
        int port=2039;
        Traduction_python t = new Traduction_python();
        t=run_pref(port, array2,2);
    }
}