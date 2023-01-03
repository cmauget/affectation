import java.util.*;
import java.io.Serializable;

public class Choix implements Serializable{
    /*
    Il est important de préciser que les listes liste_choix_pref et liste_choix_sec sont des listes d'entier
    dont chaque indice correspond à une matiere et dont chaque élément correspond à l'ordre de préférence 
    pour la matière donnée.
    Par exemple 
    Si l'on considère que l'on peut étudier les matières suivantes dans notre parcours secondaire:
    {Option1, Option2, Option3, Option4}
    Alors la liste_choix_sec suivante:
    {2,1,4,3}
    signifie que l'étudiant à ordonner ces voeux de la manière suivante:
    {Option2, Option1, Option4, Option3}
     */ 
    public Etudiant id;
    public ArrayList<Integer> liste_choix_pref;
    public ArrayList<Integer> liste_choix_sec;

    public Choix(){
        this.id=new Etudiant();
        this.liste_choix_pref=new ArrayList<Integer>();
        this.liste_choix_sec=new ArrayList<Integer>();
    }

    public Choix(Etudiant id, ArrayList<Integer> liste_choix_pref, ArrayList<Integer> liste_choix_sec){
        this.id=id;
        this.liste_choix_pref=liste_choix_pref;
        this.liste_choix_sec=liste_choix_sec;
    }

    public void setId(Etudiant id){
        this.id=id;
    }
    public void setListe_choix_pref(ArrayList<Integer> liste_choix_pref){
        this.liste_choix_pref=liste_choix_pref;
    }
    public void setListe_choix_sec(ArrayList<Integer> liste_choix_sec){
        this.liste_choix_sec=liste_choix_sec;
    }
    public Etudiant getId(){
        return id;
    }
    public ArrayList<Integer> getListe_choix_pref(){
        return liste_choix_pref;
    }
    public ArrayList<Integer> getListe_choix_sec(){
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

    public void setChoix_pref(int place, int matiere){
        this.liste_choix_pref.add(matiere,place);
    }

    public void setChoix_sec(int place, int matiere){
        this.liste_choix_sec.add(matiere,place);
    }

    public boolean choixValide(){
        return ((this.liste_choix_pref.size()==8))&&((this.liste_choix_sec.size()==4));
    }

    //Redefinition equals
    public boolean equals(Object o){
      if (this==o) return true;
      if (o == null || getClass()!= o.getClass()) return false;
      Choix choix = (Choix) o;
      return id == choix.id;
    }

}
