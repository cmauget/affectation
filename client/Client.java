import java.net.*;
import java.io.*;

public class Client {
  public static void main(String[] args) {
    final String HOST = "localhost";
    final int PORT = 2000;

    Socket socket = null;

    try {
      socket = new Socket(HOST, PORT);
      System.out.println("Connexion au serveur réussie : " + socket);

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

      
      // Envoie une requête au serveur ici...
      // Utilisez les objets in et out pour lire et écrire sur le socket

      in.close();
      out.close();
      socket.close();
    } catch (UnknownHostException e) {
      System.out.println("Hôte inconnu : " + e);
    } catch (IOException e) {
      System.out.println("Erreur lors de la connexion au serveur : " + e);
    }
  }
}
