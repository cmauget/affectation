

public class Liste_EtudiantsTest{
    public static void main(String[] args) {
        Etudiant e = new Etudiant("facundo","aaa");
        Etudiant e2 = new Etudiant("manon","wsh");
        Liste_Etudiants liste_Etudiants = new Liste_Etudiants();
        
        liste_Etudiants.creerEtudiant(e);
        liste_Etudiants.creerEtudiant(e2);
        System.out.println(liste_Etudiants);
        System.out.println("l etudiant " + e + liste_Etudiants.authEtudiant(e));
        System.out.println(liste_Etudiants.authEtudiant(e2));
    }
}