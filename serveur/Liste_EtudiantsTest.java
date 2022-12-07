import java.util.*;

public class Liste_EtudiantsTest{
    public static void main(String[] args) {
        Etudiant e = new Etudiant("facundo","aaa");
        ArrayList<Etudiant> l1 = new ArrayList<Etudiant>();
        
        l1.creerEtudiant(e);
        System.out.println(l1);

    }
}