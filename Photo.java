
import java.awt.*;
import javax.swing.*;

public class Photo extends JPanel {
    private Image bimage;

    public Photo(String image) {
        bimage =  new javax.swing.ImageIcon("../Convo app/images/"+image).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D 	g2 = (Graphics2D) g;
        g2.drawImage(bimage, 0, 0,700,400, null);
    }



    public void loadImage(Image i) {
        bimage = i;
        repaint();
    }

}
