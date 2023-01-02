import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Accepter_clients implements Runnable {


    //Attributs
    private Socket socket;
    //private Liste_Etudiants liste_etu;
    //private Affectation affectation;
    //private BufferedReader in;
    private ObjectInputStream oin;
    private ObjectOutputStream out;


    //Constructeur
    public Accepter_clients(Socket socket) throws IOException{
      try{
      this.socket = socket;
      //liste_etu = new Liste_Etudiants();
      //affectation = new Affectation();
      //this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      this.oin = new ObjectInputStream(this.socket.getInputStream());
      this.out = new ObjectOutputStream(this.socket.getOutputStream());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    }

    //Redéfinition run()
    public void run(){
      try {
        System.out.println("nouveau thread étudiant");
        String type = recevoirString();
        switch(type){
          case "Etudiant":
          {
            Etudiant etu = recevoirEtudiant();
            System.out.print("test");
            MultiThreadedServer.liste_etu.creerEtudiant(etu);

          }
          case "Choix":
        {
          String confirmation = recevoirString();
          Choix c = recevoirChoix();
          MultiThreadedServer.affectation.ajouterChoix(c);
          MultiThreadedServer.affectation.optimiserAffectations();
        }

        case "Demande Affectation":
        {
          String confirmation = recevoirString();
          confirmation = recevoirString();
          Etudiant etu = recevoirEtudiant();
          EnvoyerAffectation(MultiThreadedServer.affectation.retournerAffectation(etu));
        }
        break;
      }
//in.close();
      oin.close();
      out.close();
      socket.close();
      }
      catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }


    //Méthodes

    private String recevoirString() throws IOException,ClassNotFoundException {
      //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      Object object = oin.readObject();
      String type = (String) object;
      //in.close();
      return type;
    }

    private Choix recevoirChoix() throws IOException,ClassNotFoundException{
      //ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());

      Object object = oin.readObject();
      Choix choix = (Choix) object;
      //oin.close();
      return choix;
    }

    private Etudiant recevoirEtudiant() throws IOException, ClassNotFoundException{

      //ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
      Object object = oin.readObject();
      Etudiant etu = (Etudiant) object;
      //in.close();
      System.out.println(etu);
      return etu;
    }

    private void EnvoyerAffectation (Choix c) throws IOException, ClassNotFoundException{
      //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(c);
      //out.close();
    }
}
