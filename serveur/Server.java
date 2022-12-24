import java.net.*;
import java.io.*;

public class Server {
  public static void main(String[] args) {
    final int PORT = 2000;
    final int MAX_CLIENTS = 20;

    ServerSocket serverSocket = null;
    Socket clientSocket = null;

    try {
      serverSocket = new ServerSocket(PORT);
      System.out.println("Le serveur est en attente de connexion sur le port " + PORT + "...");

      // Accepte les connexions des clients jusqu'à ce qu'on atteigne le nombre maximal de clients
      for (int i = 0; i < MAX_CLIENTS; i++) {
        clientSocket = serverSocket.accept();
        System.out.println("Un nouveau client s'est connecté : " + clientSocket);

        // Crée un nouveau thread pour chaque client et lance le traitement de la requête
        ClientThread clientThread = new ClientThread(clientSocket);
        clientThread.start();
      }
    } catch (IOException e) {
      System.out.println("Erreur lors de la création du serveur : " + e);
    } finally {
      try {
        serverSocket.close();
      } catch (IOException e) {
        System.out.println("Erreur lors de la fermeture du serveur : " + e);
      }
    }
  }
}

class ClientThread extends Thread {
  private Socket socket;

  public ClientThread(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

      // Traite la requête du client ici...
      // Utilisez les objets in et out pour lire et écrire sur le socket

      in.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      System.out.println("Erreur lors de la communication avec le client : " + e);
    }
  }
}