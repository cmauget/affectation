import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class EcouteurDeBouton implements ActionListener { 

    public void actionPerformed(ActionEvent e){

        try {
        Object source = e.getSource();

        if (source==Client.fenetre.bouton_login) {
            Client.fenetre.prochaine_Fenetre();
        }

        if ((source==Client.fenetre.bouton_retour1) || (source==Client.fenetre.bouton_retour2) || (source==Client.fenetre.bouton_retour3)) {
            Client.fenetre.precedente_Fenetre();
        }

        if (source==Client.fenetre.bouton_retour4) {
            Client.fenetre.acceder_Fenetre("d");
        }

        if (source==Client.fenetre.bouton_deco) {
            Client.choix_fait = false;
            Client.fenetre.acceder_Fenetre("b");
        }

        if (source==Client.fenetre.bouton_connexion) {
            String nom_utilisateur = Client.fenetre.text_nom.getText();
            String mdp = Client.fenetre.text_mdp.getText();

            if (!verifConnexion(nom_utilisateur,mdp)) {
                JOptionPane.showMessageDialog(Client.fenetre,"Erreur: nom d'utilisateur ou mot de passe incorrect","Erreur",JOptionPane.INFORMATION_MESSAGE); 
            }
            else {
                Client.fenetre.acceder_Fenetre("d");
                Client.utilisateur.setLogin(nom_utilisateur);
                Client.utilisateur.setPassword(mdp); 
            }
        }

        if (source==Client.fenetre.bouton_choisir) {
            if (Client.choix_fait) //placeholder 
            {
                JOptionPane.showMessageDialog(Client.fenetre,"Erreur: vous avez déjà fait vos voeux","Erreur",JOptionPane.INFORMATION_MESSAGE); 
            }
            else {
                Client.fenetre.acceder_Fenetre("e");
            }
        }

        if (source==Client.fenetre.bouton_voir) {
            if (!Client.choix_fait) //placeholder 
            {
                JOptionPane.showMessageDialog(Client.fenetre,"Erreur: vous n'avez pas encore fait vos voeux","Erreur",JOptionPane.INFORMATION_MESSAGE); 
            }
            else {
                Etudiant etu = Client.utilisateur;
                String msg = "Demande Affectation";
                Client.oos.writeObject(msg);
                Client.oos.flush();
                Client.oos.writeObject(etu);
                Client.oos.flush();
                Choix affectation = (Choix) Client.oin.readObject();
                ArrayList<String> liste_options_pref = new ArrayList<>(8);
                liste_options_pref.add("Option1");
                liste_options_pref.add("Option2");
                liste_options_pref.add("Option3");
                liste_options_pref.add("Option4");
                liste_options_pref.add("Option5");
                liste_options_pref.add("Option6");
                liste_options_pref.add("Option7");
                liste_options_pref.add("Option8");
                ArrayList<String> liste_options_sec = new ArrayList<>(4);
                liste_options_sec.add("Option1");
                liste_options_sec.add("Option2");
                liste_options_sec.add("Option3");
                liste_options_sec.add("Option4");
                ArrayList<Integer> affectation_pref = affectation.getListe_choix_pref();
                ArrayList<Integer> affectation_sec = affectation.getListe_choix_sec();
                ArrayList<String> affectation_pref_trie = trierVoeux(affectation_pref, liste_options_pref);
                ArrayList<String> affectation_sec_trie = trierVoeux(affectation_sec, liste_options_sec);
                System.out.print("flag");
                Client.fenetre.updateFenetreAffectations(affectation_pref_trie.get(0), affectation_pref_trie.get(1), affectation_pref_trie.get(2), affectation_pref_trie.get(3), affectation_sec_trie.get(0), affectation_sec_trie.get(1));
                Client.fenetre.acceder_Fenetre("f");

            }
        }

        if (source==Client.fenetre.bouton_envoyer) {
            String choix_p1 = (String)Client.fenetre.choix_p1.getSelectedItem();
            String choix_p2 = (String)Client.fenetre.choix_p2.getSelectedItem();
            String choix_p3 = (String)Client.fenetre.choix_p3.getSelectedItem();
            String choix_p4 = (String)Client.fenetre.choix_p4.getSelectedItem();
            String choix_p5 = (String)Client.fenetre.choix_p5.getSelectedItem();
            String choix_p6 = (String)Client.fenetre.choix_p6.getSelectedItem();
            String choix_p7 = (String)Client.fenetre.choix_p7.getSelectedItem();
            String choix_p8 = (String)Client.fenetre.choix_p8.getSelectedItem();
            
            ArrayList<Integer> list_p = new ArrayList<>(8);
            list_p.add(Integer.parseInt(choix_p1));
            list_p.add(Integer.parseInt(choix_p2));
            list_p.add(Integer.parseInt(choix_p3));
            list_p.add(Integer.parseInt(choix_p4));
            list_p.add(Integer.parseInt(choix_p5));
            list_p.add(Integer.parseInt(choix_p6));
            list_p.add(Integer.parseInt(choix_p7));
            list_p.add(Integer.parseInt(choix_p8));

            System.out.println("Parcours préférentiel");
            for (int i=0; i < list_p.size(); i++){
                System.out.println(list_p.get(i));
            }

            String choix_s1 = (String)Client.fenetre.choix_s1.getSelectedItem();
            String choix_s2 = (String)Client.fenetre.choix_s2.getSelectedItem();
            String choix_s3 = (String)Client.fenetre.choix_s3.getSelectedItem();
            String choix_s4 = (String)Client.fenetre.choix_s4.getSelectedItem();

            ArrayList<Integer> list_s = new ArrayList<>(4);
            list_s.add(Integer.parseInt(choix_s1));
            list_s.add(Integer.parseInt(choix_s2));
            list_s.add(Integer.parseInt(choix_s3));
            list_s.add(Integer.parseInt(choix_s4));

            System.out.println("Parcours secondaire");
            for (int i=0; i < list_s.size(); i++){
                System.out.println(list_s.get(i));
            }

            if (verifVoeux(list_p)) {
                if (verifVoeux(list_s)) {
                    Choix choix = new Choix(Client.utilisateur,list_p,list_s);
                    String msg = "Choix";
                    Client.oos.writeObject(msg);
                    Client.oos.flush();
                    Client.oos.writeObject(choix);
                    Client.oos.flush();
                    Client.choix_fait = true;
                    JOptionPane.showMessageDialog(Client.fenetre,"Vos choix ont bien été pris en compte","Erreur",JOptionPane.INFORMATION_MESSAGE); 
                    Client.fenetre.acceder_Fenetre("d");
                }
                else {
                    JOptionPane.showMessageDialog(Client.fenetre,"Erreur: vous avez mal ordonné les voeux du parcours secondaire","Erreur",JOptionPane.INFORMATION_MESSAGE); 
                }
            }
            else {
                JOptionPane.showMessageDialog(Client.fenetre,"Erreur: vous avez mal ordonné les voeux du parcours principal","Erreur",JOptionPane.INFORMATION_MESSAGE); 
            }


            //if (true) {
              
                //JOptionPane.showMessageDialog(Client.fenetre,"Erreur: vous n'avez pas encore fait vos voeux","Erreur",JOptionPane.INFORMATION_MESSAGE); 
           // }
        
        }

        if (source==Client.fenetre.bouton_creercompte) {
            Client.fenetre.acceder_Fenetre("c");
        }

        if (source==Client.fenetre.bouton_validcompte) {
            String nom_utilisateur = Client.fenetre.text_nouvnom.getText();
            String mdp = Client.fenetre.text_nouvmdp.getText();
            Etudiant etu = new Etudiant(nom_utilisateur, mdp);
            String msg = "Nouv Etudiant";
            Client.oos.writeObject(msg);
            Client.oos.flush();
            //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Client.oos.writeObject(etu);
            Client.oos.flush();
            //oos.close();
            boolean existe = (boolean) Client.oin.readObject();
            if (existe) {
                JOptionPane.showMessageDialog(Client.fenetre,"Erreur: ce compte existe déjà","Erreur",JOptionPane.INFORMATION_MESSAGE); 
            }
            else {
                Client.fenetre.acceder_Fenetre("b");
            }
        }
    }

    catch (IOException | ClassNotFoundException ex)
    {
        ex.printStackTrace();
    }
    
    }
    public boolean verifConnexion(String nom, String mdp) throws IOException, ClassNotFoundException{
        try {
        Etudiant etu = new Etudiant(nom, mdp);
        String msg = "Connexion";
        Client.oos.writeObject(msg);
        Client.oos.flush();
        Client.oos.writeObject(etu);
        Client.oos.flush();
        boolean bool = (boolean) Client.oin.readObject();
        System.out.println(bool);
        return bool;
        }

        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
            
        }
            
    }

    public boolean verifVoeux(ArrayList<Integer> liste) {
        Set<Integer> set = new HashSet<>(liste); //On crée un set à partir de la liste
        return (set.size() == liste.size()); //Si la taille du set est égale à la taille de la liste, alors tous les éléments sont différents
        
    }

    public ArrayList<String> trierVoeux(ArrayList<Integer> liste_int, ArrayList<String> liste_str) {
        ArrayList<String> str_trie = new ArrayList<>();
        int indice=1;
        while (indice <= liste_int.size()){
            int i=0;
            while (!(liste_int.get(i)==indice)) {
                i=i+1;
            }
            indice=indice+1;
            String mot = liste_str.get(i);
            str_trie.add(mot);
        }
        return (str_trie);
    }
}

