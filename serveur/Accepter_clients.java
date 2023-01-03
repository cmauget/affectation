import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

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
        boolean estconnecte = true;
        while(estconnecte) {
        String type = recevoirString();
        switch(type){
          case "Nouv Etudiant":
          {
            Etudiant etu = recevoirEtudiant();
            boolean existe = MultiThreadedServer.liste_etu.authEtudiant(etu);
            if (!existe) {
              System.out.println("Ajout d'un nouvel étudiant");
              MultiThreadedServer.liste_etu.creerEtudiant(etu);
              ArrayList<Etudiant> liste = MultiThreadedServer.liste_etu.getListe_Etudiants();
              System.out.println("voici les éléments de la liste:");
              for (int i=0;i<liste.size();i++) {System.out.println(liste.get(i).login + " " + liste.get(i).pswd);}
            }
            else {
              System.out.print("Ce compte existe déjà");
            }
            EnvoyerConnexion(existe);
            break;
          }
          case "Choix":
        {
          //String confirmation = recevoirString();
          Choix c = recevoirChoix();
          MultiThreadedServer.affectation.ajouterChoix(c);
          MultiThreadedServer.affectation.optimiserAffectations(2039);
          System.out.println("Les choix enrengistrés pour le moment sont les suivants:");
          System.out.println("voici les éléments de la liste:");
          ArrayList<Choix> liste = MultiThreadedServer.affectation.getListe_Choix_Brut();
          for (int i=0;i<liste.size();i++) {
            System.out.println("Etudiant : "+ liste.get(i).id.login);
            ArrayList<Integer> liste_pref = liste.get(i).getListe_choix_pref();
            for (int j=0;j<liste_pref.size();j++) {
              System.out.println("Choix préférentiels Option" + j + " : " + liste_pref.get(j));
            }
            ArrayList<Integer> liste_sec = liste.get(i).getListe_choix_sec();
            for (int k=0;k<liste_sec.size();k++) {
              System.out.println("Choix secondaire Option" + k + " : " + liste_sec.get(k));
            }
           
          }
        
          break;
        }

        case "Demande Affectation":
        {
          //String confirmation = recevoirString();
          //confirmation = recevoirString();
          Etudiant etu = recevoirEtudiant();
          MultiThreadedServer.affectation.optimiserAffectations(2039);
          EnvoyerAffectation(MultiThreadedServer.affectation.retournerAffectation(etu));
          break;
        }

        case "Connexion":
        {
          //String confirmation = recevoirString();
          //confirmation = recevoirString();
          Etudiant etu = recevoirEtudiant();
          //String confirmation = recevoirString();
          System.out.println("Verification login et pswd");
          System.out.println(MultiThreadedServer.liste_etu.authEtudiant(etu));
          EnvoyerConnexion(MultiThreadedServer.liste_etu.authEtudiant(etu));
          break;
        }

        case "Fermer Socket":
        {
          System.out.println("Fin de connexion");
          estconnecte = false;
          break;
        }

        default: {
          System.out.println("Demande non reconnue");
        }
      }
      //in.close();
      //System.out.println("fin de connexion");
      //oin.close();
      //out.close();
      //socket.close();
    }
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

    private void EnvoyerConnexion (boolean b) throws IOException, ClassNotFoundException{
      out.writeObject(b);
    }
}
