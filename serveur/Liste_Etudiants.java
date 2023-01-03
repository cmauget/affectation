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
        System.out.println("Authentification en cours: " + e.login + " " + e.pswd);
        boolean bool = false;
        for (int i=0;i<this.liste_Etudiants.size();i++) {
            if ((e.getLogin().equals(this.liste_Etudiants.get(i).getLogin())) && (e.getPassword().equals(this.liste_Etudiants.get(i).getPassword()))) {
                bool = true;
            }
        }
        return (bool);
    }

    public boolean getChoixfait(Etudiant e){
        boolean bool = false;
        for (int i=0;i<this.liste_Etudiants.size();i++) {
            if ((e.getLogin().equals(this.liste_Etudiants.get(i).getLogin())) && (e.getPassword().equals(this.liste_Etudiants.get(i).getPassword()))) {
                bool = this.liste_Etudiants.get(i).getChoixfait();
            }
        }
        return bool;
    }

    public void setChoixfait(Etudiant e, boolean bool){
        for (int i=0;i<this.liste_Etudiants.size();i++) {
            if ((e.getLogin().equals(this.liste_Etudiants.get(i).getLogin())) && (e.getPassword().equals(this.liste_Etudiants.get(i).getPassword()))) {
               this.liste_Etudiants.get(i).setChoixfait(bool);
            }
        }
    }


    public String toString(){
        return liste_Etudiants.toString();
    }
}


