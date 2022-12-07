import java.util.*;
import java.io.Serializable;

public class Liste_Etudiants implements Serializable{
    //Attribut
    public ArrayList<Etudiant> liste_Etudiants;

    //Constructeurs
    public Liste_Etudiants(){
        this.liste_Etudiants = new ArrayList<Etudiant>();
    }

    public Liste_Etudiants(ArrayList<Etudiant> liste_Etudiants){
        this.liste_Etudiants = liste_Etudiants;
    }

    public void setListe_Etudiants(ArrayList<Etudiant> liste_Etudiants){
        this.liste_Etudiants = liste_Etudiants;
    }

    public ArrayList<Etudiant> getListe_Etudiants(){
        return liste_Etudiants;
    }
    public void creerEtudiant(Etudiant e){
        this.liste_Etudiants.add(e);
    }
    public boolean authEtudiant(Etudiant e){
        return (liste_Etudiants.contains(e));
    }
}


