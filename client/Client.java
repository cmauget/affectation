import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;


public class Client {

  //Attributs//
  public static Fenetres fenetre;
  public static Etudiant utilisateur;
  public static Socket socket;
  public static ObjectOutputStream oos;
  public static ObjectInputStream oin;
  // on définit les attributs comme static pour pouvoir y accéder 
  //via la classe EcouteurDeBouton

  //Constructeur//
  Client() throws IOException{
    //Initialisation de la socket
      socket = new Socket("localhost",2000);
      oos = new ObjectOutputStream(socket.getOutputStream());
      oin = new ObjectInputStream(socket.getInputStream());

      //Initialisation de la fenêtre
      fenetre = new Fenetres();
      fenetre.setSize(1300,700);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //On ajoute un ecouteur sur la fenetre qui va venir fermer les sockets à la fermeture
      fenetre.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
          try {
            String msg = "Fermer Socket";
            oos.writeObject(msg);
            oos.flush();
            oos.close();
            oin.close();
            socket.close();
          }
          catch(IOException ex) {
            ex.printStackTrace();
          }
        }
      });
      fenetre.setVisible(true);

      //Initialisation de l'utilisateur
      utilisateur = new Etudiant();
  }

  public static void main(String[] args) throws IOException,java.io.EOFException,java.net.SocketException{
    try{
    new Client();
  }
  catch(java.io.EOFException | java.net.SocketException eof) {
    System.out.println("Nombre de connexions maximum au serveur atteint, veuillez recommencer ultérieurement");
  }
}
}
