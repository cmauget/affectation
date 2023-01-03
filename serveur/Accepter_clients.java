import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

//Classe pour les threads individuels pour chaque client
public class Accepter_clients implements Runnable {


    //Attributs
    private Socket socket;
    private ObjectInputStream oin;
    private ObjectOutputStream out;


    //Constructeur
    public Accepter_clients(Socket socket) throws IOException{
      try{
      this.socket = socket;
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
        //Booleen pour vérifier que le client n'a pas fermé sa connexion socket pour éviter les exceptions
        boolean estconnecte = true;
        while(estconnecte) {
        String type = recevoirString();
        //On a un switch qui change le type d'action en fonction du message string reçu de la part du client
        switch(type){
          case "Nouv Etudiant":
          {
            //Action si le client veut créer un nouveau profil étudiant

            Etudiant etu = recevoirEtudiant();

            //On vérifie d'abord que l'étudiant crée n'existe pas déjà
            boolean existe = MultiThreadedServer.liste_etu.authEtudiant(etu);
            if (!existe) {
              System.out.println("Ajout d'un nouvel étudiant");

              //On crée l'étudiant puis on l'ajoute à la liste
              MultiThreadedServer.liste_etu.creerEtudiant(etu);
              ArrayList<Etudiant> liste = MultiThreadedServer.liste_etu.getListe_Etudiants();

              // On print les éléments de notre liste sur le terminal pour vérifier
              System.out.println("voici les éléments de la liste:");
              for (int i=0;i<liste.size();i++) {System.out.println(liste.get(i).login + " " + liste.get(i).pswd);}
            }
            //Sinon on refuse la création car le compte existe deja
            else {
              System.out.print("Ce compte existe déjà");
            }

            //On valide ou non la connexion pour le client
            EnvoyerConnexion(existe);
            break;
          }

          //Cas ou le client veut envoyer ses choix
          case "Choix":
        {
          //On crée un objet choix sur le serveur et on lui donne la valeur du choix envoyé par le client
          Choix c = new Choix();
          c = recevoirChoix();
          MultiThreadedServer.affectation.ajouterChoix(c);
          MultiThreadedServer.liste_etu.setChoixfait(c.getId(), true);
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

        //Cas ou le client demande son affectation, on utilise d'abord la fonction d'optimisation puis on lui envoie ses résultats
        case "Demande Affectation":
        {
          Etudiant etu = recevoirEtudiant();
          System.out.println("etudiant affectation"+etu.login+etu.pswd);
          MultiThreadedServer.affectation.optimiserAffectations(2039);
          EnvoyerAffectation(MultiThreadedServer.affectation.retournerAffectation(etu));
          break;
        }

        //Cas ou l'étudiant désire se connecter à son compte, on vérifie que ses login et pswd sont corrects puis on lui renvoie le booleen de résultat
        case "Connexion":
        {
          Etudiant etu = recevoirEtudiant();
          System.out.println("Verification login et pswd");
          System.out.println(MultiThreadedServer.liste_etu.authEtudiant(etu));
          EnvoyerConnexion(MultiThreadedServer.liste_etu.authEtudiant(etu));
          break;
        }

        //Cas ou l'étudiant voudrait refaire ses choix alors qu'il les a deja fait
        case "Choixfait":
        {
          Etudiant etu = recevoirEtudiant();
          System.out.println("etudiant serveur:"+etu.login + etu.pswd);
          boolean choix_fait = MultiThreadedServer.liste_etu.getChoixfait(etu);
          EnvoyerChoixfait(choix_fait);
          break;
        }

        //Pour fermer la socket entre serveur et client sur le thread actuel promrent
        case "Fermer Socket":
        {
          System.out.println("Fin de connexion");
          estconnecte = false;
          break;
        }

        //Case defaut en cas de problème mais jamais rencontré
        default: {
          System.out.println("Demande non reconnue");
        }
      }
    }
      }
      catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }

      //Libere une place dans le semaphore quand un client quitte le serveur 
      finally {
        MultiThreadedServer.semaphore.release();
      }
    }


    //Méthodes

    //Méthode permettant simplement de recevoir un string par l'input stream
    private String recevoirString() throws IOException,ClassNotFoundException {
      Object object = oin.readObject();
      String type = (String) object;
      return type;
    }

    //Méthode permettant de recevoir un choix par l'input stream
    private Choix recevoirChoix() throws IOException,ClassNotFoundException{

      Object object = oin.readObject();
      Choix choix = (Choix) object;
      return choix;
    }


    //Méthode permettant de recevoir un objet étudiant par l'input stream
    private Etudiant recevoirEtudiant() throws IOException, ClassNotFoundException{
      Object object = oin.readObject();
      Etudiant etu = (Etudiant) object;
      System.out.println(etu);
      return etu;
    }

    //Méthode permettant d'envoyer un choix par l'output stream
    private void EnvoyerAffectation (Choix c) throws IOException, ClassNotFoundException{
      out.writeObject(c);
    }

    //Méthode permettant de retourner au client le booleen de validation ou non de son authentification
    private void EnvoyerConnexion (boolean b) throws IOException, ClassNotFoundException{
      out.writeObject(b);
    }

    //Méthode permettant de retourner au client le booleen lui indiquant si ses choix ont été faits ou non
    private void EnvoyerChoixfait (boolean b) throws IOException, ClassNotFoundException{
      out.writeObject(b);
    }
}
