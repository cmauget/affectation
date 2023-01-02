import java.awt.event.*;
import java.io.*;

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

        if (source==Client.fenetre.bouton_connexion) {
            String nom_utilisateur = Client.fenetre.text_nom.getText();
            String mdp = Client.fenetre.text_mdp.getText();

            if (!verifConnexion(nom_utilisateur,mdp)) {
                JOptionPane.showMessageDialog(Client.fenetre,"Erreur: nom d'utilisateur ou mot de passe incorrect","Erreur",JOptionPane.INFORMATION_MESSAGE); 
            }
            else {
                Client.fenetre.prochaine_Fenetre();
                Client.utilisateur = mdp; //placeholder
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
                Client.fenetre.acceder_Fenetre("a");
            }
        }

        if (source==Client.fenetre.bouton_envoyer) {
            String choix_p1 = (String)Client.fenetre.choix_p1.getSelectedItem();
            String choix_p2 = (String)Client.fenetre.choix_p2.getSelectedItem();
            //if (true) {
                System.out.println(choix_p1);
                System.out.println(choix_p2);
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
            String msg = "Etudiant";
            Client.oos.writeObject(msg);
            Client.oos.flush();
            //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Client.oos.writeObject(etu);
            Client.oos.flush();
            //oos.close();
            Client.fenetre.acceder_Fenetre("b");
        }
    }

    catch (IOException ex)
    {
        ex.printStackTrace();
    }
    
    }
    public boolean verifConnexion(String nom, String mdp) throws IOException{
        //Etudiant e = new Etudiant(nom, mdp);
        //Client.com.EnvoyerEtudiant(e);
        //Client.com.RécupérerValidationAuth();
        return (("username".equals(nom)) && ("mdp".equals(mdp))); //placeholder
            
    }

    public boolean verifVoeux(String nom, String mdp) {
        return (("username".equals(nom)) && ("mdp".equals(mdp)));
    }
}

