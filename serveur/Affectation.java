import java.util.*;

public class Affectation {

    //Attributs 
    public ArrayList<Choix> liste_Choix_Traité;
    public ArrayList<Choix> liste_Choix_Brut;

    //Méthodes 
    public Affectation(){
        this.liste_Choix_Traité = new ArrayList<Choix>();
        this.liste_Choix_Brut=new ArrayList<Choix>();
    }

    public void setListeChoixTraité(ArrayList<Choix> liste_choix_Traité){
        this.liste_Choix_Traité=liste_choix_Traité;
    }

    public void setListeChoixBrut(ArrayList<Choix> liste_choix_Brut){
        this.liste_Choix_Brut=liste_choix_Brut;
    }


    public ArrayList<Choix> getListeChoixTraité(){
        return liste_Choix_Traité;
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
            this.liste_Choix_Traité.add(c);
            //else (ca veut dire qu'il a pas ete traité)
                this.liste_Choix_Brut.add(c);
    }

    public Choix retournerAffectation(Etudiant e){

    }

    public boolean moduleOuvert(String module){
        

    }