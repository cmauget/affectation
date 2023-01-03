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

        //TEMPORAIRE
        liste_Choix_Traite=liste_Choix_Brut;

    }

    public void ajouterChoix(Choix c){
                this.liste_Choix_Brut.add(c);
                System.out.println("Choix enrengistrés");
    }



    public Choix retournerAffectation(Etudiant e){
        Choix target = new Choix();
        target.setId(e);
        int index = liste_Choix_Traite.indexOf(target);
        Choix choix = liste_Choix_Traite.get(index);
        return choix;
    }

    public boolean moduleOuvert(String module){
        return false;


    }
}
