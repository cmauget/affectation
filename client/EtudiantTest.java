import java.util.*;

public class EtudiantTest{
    public static void main(String[] args) {
        Etudiant e = new Etudiant();
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("entrer ton nom et ton pswd");
        String str1 = scanner1.nextLine();
        String str2 = scanner2.nextLine();
        System.out.println("hey "+str1 +" mdp: "+str2);
        e.setLogin(str1);
        e.setPassword(str2);
        System.out.println(e.getLogin());
        System.out.println(e.getPassword());
    }

}
