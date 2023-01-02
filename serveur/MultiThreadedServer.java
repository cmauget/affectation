import java.io.*;
import java.net.*;

class MultiThreadedServer {

  public static Liste_Etudiants liste_etu;
  public static Affectation affectation;

  public static void main(String[] args) throws IOException {
    try {
      liste_etu = new Liste_Etudiants();
      affectation = new Affectation();
      
      ServerSocket serverSocket = new ServerSocket(2000);
      System.out.println("Lancement du serveur");
      while(true){
        Socket socket = serverSocket.accept();
        System.out.println("new connexion");
        new Thread(new Accepter_clients(socket)).start();
        System.out.println("new connexion");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
