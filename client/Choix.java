import java.util.*;
import java.io.Serializable;

public class Choix implements Serializable{

    public Etudiant id;
    public ArrayList<String> liste_choix_pref;
    public ArrayList<String> liste_choix_sec;

    public Choix(){
        this.id=new Etudiant();
        this.liste_choix_pref=new ArrayList<String>();
        this.liste_choix_sec=new ArrayList<String>();
    }

    public Choix(Etudiant id, ArrayList<String> liste_choix_pref, ArrayList<String> liste_choix_sec){
        this.id=id;
        this.liste_choix_pref=liste_choix_pref;
        this.liste_choix_sec=liste_choix_sec;
    }

    public void setId(Etudiant id){
        this.id=id;
    }
    public void setListe_choix_pref(ArrayList<String> liste_choix_pref){
        this.liste_choix_pref=liste_choix_pref;
    }
    public void setListe_choix_sec(ArrayList<String> liste_choix_sec){
        this.liste_choix_sec=liste_choix_sec;
    }
    public Etudiant getId(){
        return id;
    }
    public ArrayList<String> getListe_choix_pref(){
        return liste_choix_pref;
    }
    public ArrayList<String> getListe_choix_sec(){
        return liste_choix_sec;
    }


    //Méthode générique pour vérifier les doublons dans un ArrayList

    public boolean pasDeDoublon(ArrayList<String> l){
        // pour chaque élément du ArrayList, vérifie s'il se retrouve ensuite dans le ArrayList
        for (int i = 0; i < l.size(); i++)
        {
            for (int j = i + 1; j < l.size(); j++)
            {
                if (l.get(i) != null && l.get(i).equals(l.get(j))) {
                    return false;
                }
            }
        }

        // aucun doublon n'est trouvé
        return true;
    }

    public void setChoix_pref(String matiere, int place){
        this.liste_choix_pref.add(place,matiere);
    }

    public void setChoix_sec(String matiere, int place){
        this.liste_choix_sec.add(place,matiere);
    }

    public boolean choixValide(){
        return ((this.liste_choix_pref.size()==8))&&((this.liste_choix_sec.size()==4));
    }


}
