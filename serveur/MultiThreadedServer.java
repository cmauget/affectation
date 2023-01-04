import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class MultiThreadedServer implements Runnable{

  public static Liste_Etudiants liste_etu;
  public static Affectation affectation;
  public ServerSocket serverSocket;
  public Socket socket;
  static final int MAX_CONNECTIONS = 20;

  //Objet semaphore qui permet de limiter les connexions (ici à 20 utilisateurs)
  public static Semaphore semaphore;


  public MultiThreadedServer(ServerSocket s) {
    liste_etu = new Liste_Etudiants();
    affectation = new Affectation();
    Etudiant e = new Etudiant("admin", "pswd");
    liste_etu.creerEtudiant(e);
    serverSocket = s;
  }

  //Note : On a ici utilisé un thread mais ce n'était pas forcément nécessaire pour le bon fonctionnement du programme
  public void run() {
    try {
      //Création du sémaphore qui va permettre de limiter le nombre de connexions
      semaphore = new Semaphore(MAX_CONNECTIONS);
      while(true){
        socket = serverSocket.accept();
        //On ne connecte le client que si le semaphore indique que le nombre maximum n'est pas atteint
        if (semaphore.tryAcquire()){
        System.out.println("new connexion");
        //On indique la nouvelle connexion dans le terminal et on lance le thread Accepter_Clients pour ce client
        new Thread(new Accepter_clients(socket)).start();
        }
        //Sinon on n'accepte pas la connexion
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
