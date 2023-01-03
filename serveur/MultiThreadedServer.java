import java.io.*;
import java.net.*;

class MultiThreadedServer implements Runnable{

  public static Liste_Etudiants liste_etu;
  public static Affectation affectation;
  public ServerSocket serverSocket;
  public Socket socket;


  public MultiThreadedServer(ServerSocket s) {
    liste_etu = new Liste_Etudiants();
    affectation = new Affectation();
    Etudiant e = new Etudiant("r", "f");
    liste_etu.creerEtudiant(e);
    serverSocket = s;
  }


  public void run() {
    try {
      while(true){
        socket = serverSocket.accept();
        System.out.println("new connexion");
        new Thread(new Accepter_clients(socket)).start();
      }
    }
      catch(IOException ex) {
        ex.printStackTrace();
      }
    }



  public static void main(String[] args) throws IOException{
      ServerSocket serverSocket = new ServerSocket(2000);
      new Thread(new MultiThreadedServer(serverSocket)).start();
   
  }
}
