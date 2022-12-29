import java.net.*;
import java.io.*;

public class Communication_Client{

  //Attributs
  final String HOST = "localhost";
  final int PORT = 2000;
  public Socket socket;


  //Constructeur
  public Communication_Client() throws IOException{
    try {
      socket = new Socket(HOST,PORT);
    }
    catch (IOException e){
      e.printStackTrace();
    }

    }

  //Méthodes

  public void EnvoiTypage(String s) throws IOException{
    try {
      //socket = new Socket(HOST, PORT);
      PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      out.println(s);
      out.flush();
      out.close();
      //socket.close();
      }
      catch (UnknownHostException e) {
      System.out.println("Hôte inconnu : " + e);
      }
      catch (IOException e) {
      System.out.println("Erreur lors de la connexion au serveur : " + e);
      }
    }

  public void EnvoyerChoix(Choix c) throws IOException{
    try{
    EnvoiTypage("Choix");
    //socket = new Socket(HOST, PORT);
    ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
    oos.writeObject(c);
    oos.close();
    //socket.close();
  }
    catch (UnknownHostException e) {
    System.out.println("Hôte inconnu : " + e);
    }
    catch (IOException e) {
    System.out.println("Erreur lors de la connexion au serveur : " + e);
    }
  }

  public void EnvoyerEtudiant(Etudiant e) throws IOException{
    try{
    EnvoiTypage("Etudiant");
    //socket = new Socket(HOST, PORT);
    ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
    oos.writeObject(e);
    oos.close();
    //socket.close();
  }
    catch (UnknownHostException err) {
    System.out.println("Hôte inconnu : " + err);
    }
    catch (IOException err) {
    System.out.println("Erreur lors de la connexion au serveur : " + err);
    }
  }

  public Choix RecupererAffectation() throws IOException, ClassNotFoundException{
    try{
    socket = new Socket(HOST, PORT);
    ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
    Choix choix = (Choix) oin.readObject();
    oin.close();
    //socket.close();
    return choix;
  }
    catch (UnknownHostException err) {
    System.out.println("Hôte inconnu : " + err);
    Choix c = new Choix();
    return c;
    }
    catch (IOException err) {
    System.out.println("Erreur lors de la connexion au serveur : " + err);
    Choix c = new Choix();
    return c;
    }
    }

  public void EnvoyerDemandeAffectation (Etudiant e) throws IOException{
    try {
      socket = new Socket(HOST, PORT);
      PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      out.println("Demande Affectation");
      EnvoyerEtudiant(e);
      out.flush();
      out.close();
      }
      catch (UnknownHostException err) {
      System.out.println("Hôte inconnu : " + err);
      }
      catch (IOException err) {
      System.out.println("Erreur lors de la connexion au serveur : " + err);
      }
  }

  public boolean RécupérerValidationAuth() throws IOException,ClassNotFoundException{
    try{
    socket = new Socket(HOST, PORT);
    ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
    boolean b = (boolean) oin.readObject();
    oin.close();
    //socket.close();
    return b;
  }
    catch (UnknownHostException err) {
    System.out.println("Hôte inconnu : " + err);
    Choix c = new Choix();
    return false;
    }
    catch (IOException err) {
    System.out.println("Erreur lors de la connexion au serveur : " + err);
    Choix c = new Choix();
    return false;
    }
  }
}
