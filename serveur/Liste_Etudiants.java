package affectation.serveur;

import java.util.*;

public class Liste_Etudiants{
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
}


