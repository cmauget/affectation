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

    public void optimiserAffectations(int port){
        
        int nb_eleves;
        int nb_mat;
        int[][] tab_affect;
        int[][] tab_affect2;
        Choix temp;
        Choix temppouretresurecamaere;
        int i=0;
        int j=0;
        ArrayList<Integer> liste_temp;
        ArrayList<Integer> liste_temp2;
        Traduction_python t = new Traduction_python();
        
        nb_eleves=this.liste_Choix_Brut.size();

        nb_mat=8;

        //System.out.println(nb_mat);

        int[][] tab_cout= new int[nb_eleves][nb_mat];

        System.out.println("flag1"+this.liste_Choix_Brut.get(0).liste_choix_pref.get(3));
        for (i=0;i<nb_eleves;i++){
            temppouretresurecamaere=this.liste_Choix_Brut.get(i);
            liste_temp=new ArrayList<>(temppouretresurecamaere.getListe_choix_pref());
            int[] array = new int[nb_mat];
            for (j = 0; j < nb_mat; j++) {
                array[j] = liste_temp.get(j);
                //System.out.println("les arrays wsh");
                System.out.print(array[j]);
            }
            System.out.println();
            tab_cout[i]=array;
        }

        
        t = Traduction_python.run_pref(port, tab_cout, nb_eleves);
        tab_affect = t.getAffectationopti();
        tab_affect2 = t.getAffectationsecopti();
        System.out.println("flag3"+this.liste_Choix_Brut.get(0).liste_choix_pref.get(3));

        this.liste_Choix_Traite = new ArrayList<Choix>();

        /*
        ArrayList<Integer> tamerlapute = new ArrayList<>();
        tamerlapute.add(69);
        this.liste_Choix_Traite.get(0).setListe_choix_pref(tamerlapute);
        System.out.println(this.liste_Choix_Traite.get(0).liste_choix_pref.get(0));
        System.out.println(this.liste_Choix_Brut.get(0).liste_choix_pref.get(0));
        System.out.println("flag4"+this.liste_Choix_Brut.get(0).liste_choix_pref.get(3));
        */

        for (i=0; i<nb_eleves; i++) {
            //temp = this.liste_Choix_Brut.get(i);
            ArrayList<Integer> liste_temp_pouretresur=new ArrayList<Integer>();
            ArrayList<Integer> liste_temp_pouretresur2=new ArrayList<Integer>();
            for (j=0;j<nb_mat;j++) {
                liste_temp_pouretresur.add(tab_affect[i][j]);
                liste_temp_pouretresur2.add(tab_affect2[i][j]);
            }
            temp=this.liste_Choix_Brut.get(i);
            System.out.println("flag6"+this.liste_Choix_Brut.get(0).liste_choix_pref.get(3));
            //temp.setListe_choix_pref(liste_temp_pouretresur);
            System.out.println("flag7"+this.liste_Choix_Brut.get(0).liste_choix_pref.get(3));
            //temp.setListe_choix_sec(liste_temp_pouretresur2);
            Choix choixajout = new Choix(temp.getId(),liste_temp_pouretresur, liste_temp_pouretresur2);
            this.liste_Choix_Traite.add(choixajout);
        }

        System.out.println("flag5"+this.liste_Choix_Brut.get(0).liste_choix_pref.get(3));
    }


    public void ajouterChoix(Choix c){
                this.liste_Choix_Brut.add(c);
                System.out.println("Choix enrengistrés");
    }



    public Choix retournerAffectation(Etudiant e){
        /* 
        Choix target = new Choix();
        target.setId(e);
        int index = liste_Choix_Traite.indexOf(target);
        Choix choix = liste_Choix_Traite.get(index);
        return choix;
        */
        Choix choix = new Choix();
        for (int i=0;i<this.liste_Choix_Traite.size();i++) {
            if ((e.getLogin().equals(this.liste_Choix_Traite.get(i).getId().getLogin())) && (e.getPassword().equals(this.liste_Choix_Traite.get(i).getId().getPassword()))) {
               choix = this.liste_Choix_Traite.get(i);
            }
        }
        return choix;
    }

    public boolean moduleOuvert(String module){
        return false;


    }
}
