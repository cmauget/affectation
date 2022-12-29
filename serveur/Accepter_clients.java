import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Accepter_clients implements Runnable{


    //Attributs
    private Socket socket;
    private Liste_Etudiants liste_etu;
    private Affectation affectation;


    //Constructeur
    public Accepter_clients(Socket socket){
      this.socket = socket;
    }

    //Redéfinition run()
    public void run(){
      try {
        String type = recevoirString();
        switch(type){
        case "Choix":
        {
          Choix c = recevoirChoix();
          this.affectation.ajouterChoix(c);
          this.affectation.optimiserAffectations();
        }
        break;
        case "Etudiant":
        {
          Etudiant etu = recevoirEtudiant();
          this.liste_etu.creerEtudiant(etu);
        }
        break;
        case "Demande Affectation":
        {
          String confirmation = recevoirString();
          Etudiant etu = recevoirEtudiant();
          EnvoyerAffectation(affectation.retournerAffectation(etu));
        }
        break;
      }
      }
      catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }


    //Méthodes

    private String recevoirString() throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String type = in.readLine();
      in.close();
      return type;
    }

    private Choix recevoirChoix() throws IOException,ClassNotFoundException{
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object object = in.readObject();
      Choix choix = (Choix) object;
      in.close();
      return choix;
    }

    private Etudiant recevoirEtudiant() throws IOException, ClassNotFoundException{

      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      System.out.println("TEST");
      Object object = in.readObject();
      Etudiant etu = (Etudiant) object;
      in.close();
      System.out.println(etu);
      return etu;
    }

    private void EnvoyerAffectation (Choix c) throws IOException, ClassNotFoundException{
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(c);
      out.close();
    }
}
