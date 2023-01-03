import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel { 

    //Attributs//
    public Image img;

    //Constructeur//
    public ImagePanel(Image img) {
        super();
        this.img = img;
    }

    //Méthodes//
    public void setImage(Image img) {
        this.img = img;
    }

    public Image getImage(Image img) {
        return this.img;
    }

    @Override
    public void paintComponent(Graphics g) {
        //Permet d'avoir un fond de panel customisé
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(img,0,0,null);
    }
}

