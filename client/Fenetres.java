import java.awt.*;
import javax.swing.*;

public class Fenetres extends JFrame{
    
    //Attributs//
    public int HAUT = 700;
    public int LARG = 1300;
    public CardLayout carte;
    public Container panel;
    public ImagePanel panel_login, panel_username, panel_menu, panel_voeux, panel_creercompte, panel_affectations;
    public JButton bouton_login, bouton_retour1, bouton_retour2, bouton_retour3, bouton_retour4, bouton_connexion, bouton_voir, bouton_choisir, bouton_envoyer, bouton_creercompte, bouton_validcompte,bouton_deco;
    public JTextField text_nom, text_mdp, text_nouvnom, text_nouvmdp;
    public JComboBox<String> choix_p1, choix_p2, choix_p3, choix_p4, choix_p5, choix_p6, choix_p7, choix_p8, choix_s1, choix_s2, choix_s3, choix_s4;
    public String[] voeux_principaux = {"1","2","3","4","5","6","7","8"};
    public String[] voeux_secondaires = {"1","2","3","4"};
    public JLabel placeholder1,placeholder2,placeholder3,placeholder4,placeholder5,placeholder6;

    //Constructeurs//
    Fenetres(){
        super();


        //Récupération des images de fond dans le dossier "images"
        Image img1 = new ImageIcon(getClass().getResource("/images/login.png")).getImage();
        Image img2 = new ImageIcon(getClass().getResource("/images/username.png")).getImage();
        Image img3 = new ImageIcon(getClass().getResource("/images/choix.png")).getImage();
        Image img4 = new ImageIcon(getClass().getResource("/images/affectations.png")).getImage();


        //Initialisation des éléments graphiques
        this.panel_login = new ImagePanel(img1);
        this.panel_username = new ImagePanel(img2);
        this.panel_menu = new ImagePanel(img1);
        this.panel_voeux = new ImagePanel(img3);
        this.panel_creercompte = new ImagePanel(img2);
        this.panel_affectations = new ImagePanel(img4);
        this.carte = new CardLayout();
        this.bouton_login = new JButton("login");
        this.bouton_retour1 = new JButton("retour");
        this.bouton_retour2 = new JButton("retour");
        this.bouton_retour3 = new JButton("retour");
        this.bouton_retour4 = new JButton("retour");
        this.bouton_connexion = new JButton("connexion");
        this.bouton_voir = new JButton("voir affectation courante");
        this.bouton_choisir = new JButton("faire mes voeux");
        this.bouton_envoyer = new JButton("envoyer mes voeux");
        this.bouton_creercompte = new JButton("créer un compte");
        this.bouton_validcompte = new JButton("valider création compte");
        this.bouton_deco = new JButton("se déconnecter");
        this.text_nom = new JTextField();
        this.text_mdp = new JTextField();
        this.text_nouvnom = new JTextField();
        this.text_nouvmdp = new JTextField();
        JLabel label1 = new JLabel("Username", JLabel.CENTER);
        JLabel label2 = new JLabel("Mot de passe", JLabel.CENTER);
        JLabel label3 = new JLabel("Username", JLabel.CENTER);
        JLabel label4 = new JLabel("Mot de passe", JLabel.CENTER);
        JLabel labelp = new JLabel("Ordonner voeux principaux", JLabel.CENTER);
        JLabel labels = new JLabel("Ordonner voeux secondaires", JLabel.CENTER);
        JLabel labelp1 = new JLabel("Option P1: ", JLabel.CENTER);
        JLabel labelp2 = new JLabel("Option P2: ", JLabel.CENTER);
        JLabel labelp3 = new JLabel("Option P3: ", JLabel.CENTER);
        JLabel labelp4 = new JLabel("Option P4: ", JLabel.CENTER);
        JLabel labelp5 = new JLabel("Option P5: ", JLabel.CENTER);
        JLabel labelp6 = new JLabel("Option P6: ", JLabel.CENTER);
        JLabel labelp7 = new JLabel("Option P7: ", JLabel.CENTER);
        JLabel labelp8 = new JLabel("Option P8: ", JLabel.CENTER);
        JLabel labels1 = new JLabel("Option S1: ", JLabel.CENTER);
        JLabel labels2 = new JLabel("Option S2: ", JLabel.CENTER);
        JLabel labels3 = new JLabel("Option S3: ", JLabel.CENTER);
        JLabel labels4 = new JLabel("Option S4: ", JLabel.CENTER);
        JLabel labela1 = new JLabel("Options retenues: ", JLabel.CENTER);
        JLabel labela2 = new JLabel("Options retenues: ", JLabel.CENTER);
        JLabel labela3 = new JLabel("Affectations courantes: ", JLabel.CENTER);
        JLabel labela4 = new JLabel("Parcours préférentiel: ", JLabel.CENTER);
        JLabel labela5 = new JLabel("Parcours secondaire: ", JLabel.CENTER);
        placeholder1 = new JLabel("affectation1", JLabel.CENTER);
        placeholder2 = new JLabel("affectation2", JLabel.CENTER);
        placeholder3 = new JLabel("affectation3", JLabel.CENTER);
        placeholder4 = new JLabel("affectation4", JLabel.CENTER);
        placeholder5 = new JLabel("affectation1", JLabel.CENTER);
        placeholder6 = new JLabel("affectation2", JLabel.CENTER);
        this.panel = getContentPane();
        this.panel.setLayout(this.carte);
        this.choix_p1 = new JComboBox<String>(voeux_principaux); choix_p1.setSelectedItem("1");
        this.choix_p2 = new JComboBox<String>(voeux_principaux); choix_p2.setSelectedItem("2");
        this.choix_p3 = new JComboBox<String>(voeux_principaux); choix_p3.setSelectedItem("3");
        this.choix_p4 = new JComboBox<String>(voeux_principaux); choix_p4.setSelectedItem("4");
        this.choix_p5 = new JComboBox<String>(voeux_principaux); choix_p5.setSelectedItem("5");
        this.choix_p6 = new JComboBox<String>(voeux_principaux); choix_p6.setSelectedItem("6");
        this.choix_p7 = new JComboBox<String>(voeux_principaux); choix_p7.setSelectedItem("7");
        this.choix_p8 = new JComboBox<String>(voeux_principaux); choix_p8.setSelectedItem("8");
        this.choix_s1 = new JComboBox<String>(voeux_secondaires); choix_s1.setSelectedItem("1");
        this.choix_s2 = new JComboBox<String>(voeux_secondaires); choix_s2.setSelectedItem("2");
        this.choix_s3 = new JComboBox<String>(voeux_secondaires); choix_s3.setSelectedItem("3");
        this.choix_s4 = new JComboBox<String>(voeux_secondaires); choix_s4.setSelectedItem("4");


        //Organisation des éléments graphiques sur les différents panels
        panel_login.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1,1,1,1);
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 250;
        c.ipady = 70;
        panel_login.add(bouton_login,c);

        panel_username.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 20;
        panel_username.add(label1,c);
        c.gridy=1;
        panel_username.add(text_nom,c);
        c.gridy = 2;
        panel_username.add(label2,c);
        c.gridy = 3;
        panel_username.add(text_mdp,c);
        c.gridy = 4;
        c.ipadx = 222;
        panel_username.add(bouton_connexion,c);
        c.gridy = 5;
        c.ipadx = 180;
        panel_username.add(bouton_creercompte,c);
        c.gridy = 6;
        c.ipadx = 250;
        panel_username.add(bouton_retour1,c);

        panel_menu.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 250;
        c.ipady = 70;
        panel_menu.add(bouton_choisir,c);
        c.gridy = 1;
        c.ipadx = 186;
        panel_menu.add(bouton_voir,c);
        c.gridy = 2;
        c.ipadx = 252;
        panel_menu.add(bouton_deco,c);

        panel_voeux.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 70;
        c.ipady = 20;
        panel_voeux.add(labelp1,c);
        c.gridy = 2;
        panel_voeux.add(labelp2,c);
        c.gridy = 3;
        panel_voeux.add(labelp3,c);
        c.gridy = 4;
        panel_voeux.add(labelp4,c);
        c.gridy = 5;
        panel_voeux.add(labelp5,c);
        c.gridy = 6;
        panel_voeux.add(labelp6,c);
        c.gridy = 7;
        panel_voeux.add(labelp7,c);
        c.gridy = 8;
        panel_voeux.add(labelp8,c);
        c.gridx = 1;
        c.gridy = 0;
        panel_voeux.add(labelp,c);
        c.gridy = 1;
        panel_voeux.add(choix_p1,c);
        c.gridy = 2;
        panel_voeux.add(choix_p2,c);
        c.gridy = 3;
        panel_voeux.add(choix_p3,c);
        c.gridy = 4;
        panel_voeux.add(choix_p4,c);
        c.gridy = 5;
        panel_voeux.add(choix_p5,c);
        c.gridy = 6;
        panel_voeux.add(choix_p6,c);
        c.gridy = 7;
        panel_voeux.add(choix_p7,c);
        c.gridy = 8;
        panel_voeux.add(choix_p8,c);
        c.gridx=2;
        c.gridy=1;
        panel_voeux.add(labels1,c);
        c.gridy = 2;
        panel_voeux.add(labels2,c);
        c.gridy = 3;
        panel_voeux.add(labels3,c);
        c.gridy = 4;
        panel_voeux.add(labels4,c);
        c.gridy = 9;
        panel_voeux.add(bouton_envoyer,c);
        c.gridx=3;
        c.gridy=0;
        panel_voeux.add(labels,c);
        c.gridy = 1;
        panel_voeux.add(choix_s1,c);
        c.gridy = 2;
        panel_voeux.add(choix_s2,c);
        c.gridy = 3;
        panel_voeux.add(choix_s3,c);
        c.gridy = 4;
        panel_voeux.add(choix_s4,c);
        c.gridx=4;
        c.gridy=9;
        c.ipadx = 70;
        c.ipady = 20;
        panel_voeux.add(bouton_retour2,c);

        panel_creercompte.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 20;
        c.ipadx = 250;
        panel_creercompte.add(label3,c);
        c.gridy=1;
        panel_creercompte.add(text_nouvnom,c);
        c.gridy = 2;
        panel_creercompte.add(label4,c);
        c.gridy = 3;
        panel_creercompte.add(text_nouvmdp,c);
        c.gridy = 4;
        c.ipadx = 128;
        panel_creercompte.add(bouton_validcompte,c);
        c.gridy = 5;
        c.ipadx = 250;
        panel_creercompte.add(bouton_retour3,c);

        panel_affectations.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 70;
        c.ipady = 20;
        panel_affectations.add(labela1,c);
        c.gridx = 1;
        c.gridy = 1;
        panel_affectations.add(labela4,c);
        c.gridy = 2;
        panel_affectations.add(placeholder1,c);
        c.gridy = 3;
        panel_affectations.add(placeholder2,c);
        c.gridy = 4;
        panel_affectations.add(placeholder3,c);
        c.gridy = 5;
        panel_affectations.add(placeholder4,c);
        c.gridx = 2;
        c.gridy = 0;
        panel_affectations.add(labela3,c);
        c.gridx = 2;
        c.gridy = 2;
        panel_affectations.add(labela5,c);
        c.gridx = 3;
        c.gridy = 1;
        panel_affectations.add(labela2,c);
        c.gridy = 2;
        panel_affectations.add(placeholder5,c);
        c.gridy = 3;
        panel_affectations.add(placeholder6,c);
        c.gridx = 4;
        c.gridy = 6;
        panel_affectations.add(bouton_retour4,c);


        //Ajout d'écouteurs sur les boutons
        bouton_connexion.addActionListener(new EcouteurDeBouton());
        bouton_login.addActionListener(new EcouteurDeBouton());
        bouton_retour1.addActionListener(new EcouteurDeBouton());
        bouton_retour2.addActionListener(new EcouteurDeBouton());
        bouton_retour3.addActionListener(new EcouteurDeBouton());
        bouton_retour4.addActionListener(new EcouteurDeBouton());
        bouton_choisir.addActionListener(new EcouteurDeBouton());
        bouton_voir.addActionListener(new EcouteurDeBouton());
        bouton_envoyer.addActionListener(new EcouteurDeBouton());
        bouton_creercompte.addActionListener(new EcouteurDeBouton());
        bouton_validcompte.addActionListener(new EcouteurDeBouton());
        bouton_deco.addActionListener(new EcouteurDeBouton());


        //Ajout des différents panels au CardboardLayout
        this.panel.add("a",panel_login); this.panel.add("b",panel_username); this.panel.add("c",panel_creercompte); this.panel.add("d",panel_menu); this.panel.add("e",panel_voeux); this.panel.add("f",panel_affectations);
    }
    
    public void prochaine_Fenetre() {
        this.carte.next(panel);
    }

    public void precedente_Fenetre() {
        this.carte.previous(panel);
    }

    public void acceder_Fenetre(String lettre) {
        this.carte.show(panel, lettre);
    }

    public void updateFenetreAffectations(String affect1, String affect2, String affect3, String affect4, String affect5, String affect6) {
        /*
        L'Affichage de la fenêtre d'affectations étant amené a changé en fonction de l'affectation courante 
        il est nécessaire d'avoir une fonction permettant de rafraîchir son affichage en affichant le résultat
        du dernier calcul d'affectation
         */
        JLabel label1 = new JLabel(affect1, JLabel.CENTER);
        JLabel label2 = new JLabel(affect2, JLabel.CENTER);
        JLabel label3 = new JLabel(affect3, JLabel.CENTER);
        JLabel label4 = new JLabel(affect4, JLabel.CENTER);
        JLabel label5 = new JLabel(affect5, JLabel.CENTER);
        JLabel label6 = new JLabel(affect6, JLabel.CENTER);
        JLabel labela1 = new JLabel("Options retenues: ", JLabel.CENTER);
        JLabel labela2 = new JLabel("Options retenues: ", JLabel.CENTER);
        JLabel labela3 = new JLabel("Affectations courantes: ", JLabel.CENTER);
        JLabel labela4 = new JLabel("Parcours préférentiel: ", JLabel.CENTER);
        JLabel labela5 = new JLabel("Parcours secondaire: ", JLabel.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        panel_affectations.removeAll();
        panel_affectations.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 70;
        c.ipady = 20;
        panel_affectations.add(labela1,c);
        c.gridx = 1;
        c.gridy = 1;
        panel_affectations.add(labela4,c);
        c.gridy = 2;
        panel_affectations.add(label1,c);
        c.gridy = 3;
        panel_affectations.add(label2,c);
        c.gridy = 4;
        panel_affectations.add(label3,c);
        c.gridy = 5;
        panel_affectations.add(label4,c);
        c.gridx = 2;
        c.gridy = 0;
        panel_affectations.add(labela3,c);
        c.gridx = 2;
        c.gridy = 2;
        panel_affectations.add(labela5,c);
        c.gridx = 3;
        c.gridy = 1;
        panel_affectations.add(labela2,c);
        c.gridy = 2;
        panel_affectations.add(label5,c);
        c.gridy = 3;
        panel_affectations.add(label6,c);
        c.gridx = 4;
        c.gridy = 6;
        panel_affectations.add(bouton_retour4,c);
        this.panel.add("f",panel_affectations);
    }
}
