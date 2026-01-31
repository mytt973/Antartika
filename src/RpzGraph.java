import javax.swing.*;
import java.awt.*;

/**
 *
 * @desciption Classe qui affiche le menu pause via JPanel
 */
public class RpzGraph extends JPanel {
    private JLabel text;
    RpzGraph(){
        super();
        text = new JLabel(" PV: 1.5");
        this.setPreferredSize(new Dimension(400,300));
        this.setBackground(Color.red);
        this.add(text);
        this.setVisible(false);

    }

}
