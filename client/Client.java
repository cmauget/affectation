import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;


public class Client {

  public static Fenetres fenetre;
  public static Etudiant utilisateur; 
  public static Socket socket;
  public static ObjectOutputStream oos;
  public static ObjectInputStream oin;

  Client() throws IOException{
      socket = new Socket("localhost",2000);
      oos = new ObjectOutputStream(socket.getOutputStream());
      oin = new ObjectInputStream(socket.getInputStream());

      fenetre = new Fenetres();
      fenetre.setSize(1300,700);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

      utilisateur = new Etudiant();
  }

  public static void main(String[] args) throws IOException,java.io.EOFException{
    try{
    new Client();
  }
  catch(java.io.EOFException eof) {
    System.out.println("Nombre de connexions maximum au serveur atteint, veuillez recommencer ultérieurement");
  }
}
}
