import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class heading {
    private String name;
    public heading(String name){
        this.name = name;
    }

    public JPanel panel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        JLabel label = new JLabel(name);
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.setBackground(Color.PINK);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        return panel;
    }
}
