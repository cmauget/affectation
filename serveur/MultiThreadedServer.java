import java.io.*;
import java.net.*;

class MultiThreadedServer {
  public static void main(String[] args) throws IOException {
    try {
      ServerSocket serverSocket = new ServerSocket(2000);
      System.out.println("Lancement du serveur");
      while(true){
        Socket socket = serverSocket.accept();
        new Thread(new Accepter_clients(socket)).start();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
