import java.util.Scanner;
import java.io.*;



public class Client {

  public static void main(String[] args) throws IOException,ClassNotFoundException{
    try{

    Scanner scanner = new Scanner(System.in);
    System.out.println("Avez vous deja un compte (o/n)?");
    String input = scanner.nextLine();
    switch(input){

    case "n":
    {
      System.out.println("Entrer un login");
      String login = scanner.nextLine();
      System.out.println("Entrer un mdp");
      String mdp = scanner.nextLine();
      Etudiant etu = new Etudiant(login,mdp);
      Communication_Client com = new Communication_Client();
      com.EnvoyerEtudiant(etu);
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
      com.EnvoyerChoix(c);
      com.EnvoyerDemandeAffectation(etu);
      Choix c_recu = new Choix();
      c_recu = com.RecupererAffectation();
      System.out.println(c_recu);
      }
      }
    }

    catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
      }
  }

}
