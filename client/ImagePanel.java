import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel { 

    public Image img;

    public ImagePanel(Image img) {
        super();
        this.img = img;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public Image getImage(Image img) {
        return this.img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(img,0,0,null);
    }
}

