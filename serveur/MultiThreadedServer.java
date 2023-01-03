import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class MultiThreadedServer implements Runnable{

  public static Liste_Etudiants liste_etu;
  public static Affectation affectation;
  public ServerSocket serverSocket;
  public Socket socket;
  static final int MAX_CONNECTIONS = 20;
  public static Semaphore semaphore;


  public MultiThreadedServer(ServerSocket s) {
    liste_etu = new Liste_Etudiants();
    affectation = new Affectation();
    Etudiant e = new Etudiant("admin", "pswd");
    liste_etu.creerEtudiant(e);
    serverSocket = s;
  }


  public void run() {
    try {
      semaphore = new Semaphore(MAX_CONNECTIONS);
      while(true){
        socket = serverSocket.accept();
        if (semaphore.tryAcquire()){
        System.out.println("new connexion");
        new Thread(new Accepter_clients(socket)).start();
        }
        else {
          socket.close();
        }
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
