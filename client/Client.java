import java.util.Scanner;
import java.io.*;
import java.net.*;



public class Client {

  public static void main(String[] args) throws IOException,ClassNotFoundException{
    try{

    Scanner scanner = new Scanner(System.in);
    System.out.println("Avez vous deja un compte (o/n)?");
    String input = scanner.nextLine();
    switch(input){

    case "n":
    {
      //CREATION SOCKET
      Socket socket = new Socket("localhost",2000);
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());

      //CREATION ETUDIANT
      System.out.println("Entrer un login");
      String login = scanner.nextLine();
      System.out.println("Entrer un mdp");
      String mdp = scanner.nextLine();
      Etudiant etu = new Etudiant(login,mdp);

      //ENVOI DE LETUDIANT


      String msg = "Etudiant";
      oos.writeObject(msg);
      oos.flush();
      //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(etu);
      oos.flush();
      //oos.close();

      //CREATION DU CHOIX
      Choix c = new Choix();
      c.setId(etu);
      System.out.println("CHOIX DU PARCOURS PREFERENTIEL");
      for (int i=0;i<4;i++){
        System.out.println("Entre votre " + (i+1) + " -eme matiere");
        String matiere = scanner.nextLine();
        c.setChoix_pref(matiere,i);
      }
      System.out.println("CHOIX DU PARCOURS SECONDAIRE");
      for (int i=0;i<2;i++){
        System.out.println("Entre votre " + (i+1) + " -eme matiere");
        String matiere = scanner.nextLine();
        c.setChoix_sec(matiere,i);
      }

      //ENVOI DU CHOIX
      //PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      msg = "choix";
      oos.writeObject(msg);
      oos.flush();
      //out.flush();
      //out.close();
      //ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
      oos.writeObject(c);
      oos.flush();
      //oos.close();


      //ENVOI DE LA DEMANDE D'AFFECTATION
      //PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      msg = "Demande Affectation";
      oos.writeObject(msg);
      oos.flush();

      msg = "Etudiant";
      oos.writeObject(msg);
      oos.flush();
      //out.println("Etudiant");
      //out.flush();
      //out.close();
      //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(etu);
      oos.flush();

      //oos.close();

      //CREATION DU CHOIX RECU
      Choix c_recu = new Choix();

      //RECUPERATION DE L'AFFECTATION


      Choix choix = (Choix) oin.readObject();
      oin.close();
      c_recu = choix;

      //AFFICHAGE C RECU
      System.out.println(c_recu);

      //FERMETURE Socket
      socket.close();
      }
      }
    }

    catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
      }
  }

}
