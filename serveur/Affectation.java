import java.util.*;

public class Affectation {

    //Attributs 
    public ArrayList<Choix> liste_Choix_Traite;
    public ArrayList<Choix> liste_Choix_Brut;

    //Méthodes 
    public Affectation(){
        this.liste_Choix_Traite = new ArrayList<Choix>();
        this.liste_Choix_Brut=new ArrayList<Choix>();
    }
    public Affectation(ArrayList<Choix> liste_Choix_Traite, ArrayList<Choix> liste_Choix_Brut){
        this.liste_Choix_Traite = liste_Choix_Traite;
        this.liste_Choix_Brut = liste_Choix_Brut;
    }

    public void setListeChoixTraite(ArrayList<Choix> liste_Choix_Traite){
        this.liste_Choix_Traite=liste_Choix_Traite;
    }

    public void setListeChoixBrut(ArrayList<Choix> liste_Choix_Brut){
        this.liste_Choix_Brut=liste_Choix_Brut;
    }


    public ArrayList<Choix> getListeChoixTraite(){
        return liste_Choix_Traite;
    }
    public ArrayList<Choix> getListe_Choix_Brut(){
        return liste_Choix_Brut;
    }

    public void optimiserAffectations(){
        //mettre choix traité si il est passé par l optimisation
        //mettre nombre de personnes par module au vu des affectations
    }

    public void ajouterChoix(Choix c){
        // if choix traite
            this.liste_Choix_Traite.add(c);
            //else (ca veut dire qu'il a pas ete traité)
                this.liste_Choix_Brut.add(c);
    }

    //public Choix retournerAffectation(Etudiant e){
    //    return ;
    //}

    public boolean moduleOuvert(String module){
        return false;
        

    }
}