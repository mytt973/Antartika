import exemple.Intar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Map extends JFrame implements KeyListener {
    Intar iter;
    JPanel test1;
    JLabel lab1;
    Affiche panelaff;

    /**
     * @desciption permet de lancer de lancer la fenettre du jeux avec un controle sur les
     * touche du clavier et le mode pause
     */
    Map() {
        super("oklm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        panelaff = new Affiche();
        test1 = new RpzGraph();
        iter = new Intar();

        iter.init_liste();
        this.add(lab1 = new JLabel("lvl : 01"), BorderLayout.NORTH);
        this.add(panelaff, BorderLayout.CENTER);
        this.pack();
        this.addKeyListener(this);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        int toto = panelaff.getxx();
        System.out.println(toto + "it running");
    }



    public void pause(int i) {
        if (i == 1) {
            test1.setVisible(false);
            this.remove(test1);
            panelaff.starting();
        } else {
            test1.setVisible(true);
            this.add(test1, BorderLayout.WEST);
            panelaff.stoping();
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        String keyss = e.getKeyChar() + "";
        iter.mod_sens(keyss, 0);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        String keyss = e.getKeyChar() + "";
        //non spam
        if (keyss.equals("z")) {
            System.out.println(e.getKeyChar() + "release");
            iter.mod_sens(keyss, 1);
        }
        if (keyss.equals("p")) {
            System.out.println(e.getKeyChar() + "release");
            lab1.setText("sheld : 100");
            pause(0);

        }
        if (keyss.equals("o")) {
            System.out.println(e.getKeyChar() + "release");
            lab1.setText("sheld : 500");
            pause(1);

        }

    }


}
