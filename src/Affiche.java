import Plafont.Mur;
import Plafont.TabMur;
import Plafont.Antiter;
import exemple.Intar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Affiche extends JPanel implements ActionListener {


    public int x=200;
    public int y=500;//le min go setup
    private int saut=-1;

    private int speedOfJump=20;
    private int iniSaut=y;//insetup iniSaut= tableDeMur.get(0).getCoy:
    private boolean emp=false;

    private Timer temps;
    private String situation="s";
    private final Intar inop;
    private final TabMur tableuxDEMur;
    private  final Antiter o2p;
    private  final Antiter o3p;
    Image imfixe;
    Image imup;
    Image imleft;
    Image imright;


    /**
     * @desciption Initialise les murs, certaines variables, le personage choisi, la taille de la fenêtre
     * et le timer
     */
    Affiche(){
        o2p = new Antiter(500,500,60,false,15,5,"1erM");
        o3p = new Antiter(300,500,60,false,15,5,"2emM");
        inop=new Intar();
        inop.init_liste();
        tableuxDEMur = new TabMur();
        tableuxDEMur.generateAleatMur();
        tableuxDEMur.addMur(new Mur(800,400,130,true));
        AllImage("img/mouv_main_z.jpg","img/mouv_main_q.jpg","img/mouv_main_d.jpg","img/fixe_main.jpg");
       // AllImage("img/mouv_dina_z.jpg","img/mouv_dina_d.jpg","img/mouv_dina_q.jpg","img/mouv_dina_fixe.jpg");
        this.setPreferredSize(new Dimension(1000,800));
        this.setBackground( Color.white);
        System.out.println(tableuxDEMur.getLeTabM().get(2).getCox()+"avec "+tableuxDEMur.getLeTabM().get(2).getWidth());
        this.temps = new Timer(50, this);
         temps.start();
    }


    /**
     * @description Dessine les murs et le joueur sur la fenêtre
     *
     *
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g)
    {
    super.paint(g);
        Graphics2D donner = (Graphics2D) g;
        donner.drawImage(getImgS(),x,y,null); //donner.fillOval(x,y,50,50);
        donner.setColor(Color.blue);
        donner.fillRect(o2p.getCox(),o2p.getCoy()-10,o2p.getWidth(),o2p.getHeight());
        donner.fillRect(o3p.getCox(),o3p.getCoy()-10,o3p.getWidth(),o3p.getHeight());
         //donner.drawImage(new ImageIcon("img/mouv_dina_fixe.jpg").getImage(),o2p.getCox(),o2p.getCoy(),null);
        for (int i=0;i<tableuxDEMur.getLeTabM().size();i++){//affiche tout ls mur
           Mur op=tableuxDEMur.getLeTabM().get(i);
            //donner.drawRect(op.getCox()+20,op.getCoy()+50,op.getWidth()+20,op.getHeight()-30);
            donner.setColor(Color.red);
            donner.fillRect(op.getCox()+20,op.getCoy()+50,op.getWidth()+10,op.getHeight()-30);
        }
        String setinfo=o3p.toString();
        donner.drawString(setinfo,10,10);
    }


    /**
     * @description Calcule la position du joueur et des Antiter en fonction de leurs environments
     * avant de redessiner la fenêtre
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {//toutes les ms
        int xx=x;
        int yy=y;
              x+=inop.push_nb("q","d",0);
              setSituation(inop.lect(0));
        siSauter();
        Gravity(); //antiMur();
        inop.mod_sens("s",0);
        if(x==xx && y==yy )
            setSituation("s");
        for(int v=1; v<tableuxDEMur.getLeTabM().size();v++){  //a mur tuche
            x+=hitt_mur(tableuxDEMur.getLeTabM().get(v),x,y);
        }
        int vv=o3p.effectAntiter(x,y,'v');
        x+=vv;
        o3p.setPv(o3p.getPv()+vv);
        x+=o2p.effectAntiter(x,y,' ');
        repaint();
    }

    /**
     * Gestion du mode pause
     */
    public void starting(){this.temps.restart();}
public void stoping(){this.temps.stop();}


    /**
     *
     * @description vérifie si le joueur ne touche pas les coter du mur
     * @param exxe contient les infos du mur en question
     * @param x position du joueur X
     * @param y position du joueur Y
     * @return le nb de pixel pr décaler le joueur si besoins
     */
    public int hitt_mur(Mur exxe,int x, int y ){
            if(!exxe.SurSurface(x,y)){
                if(exxe.hitt_LF(x,y)) {
                    if (exxe.getCox() <= x-50)
                        return 30;
                    else
                        return -30;
                }
            }
            return 0;
        }


    /**
     * Applique un système de saut, sauf quand il touche un mur par le dessus
     */
    public void siSauter(){
        if(emp){//si z
            saut=-1;//saut
            y=y+saut*this.speedOfJump;
            setSituation("z");
            if(y==iniSaut-300 || tableuxDEMur.SurDown(x,y))//redessandre
            {
                saut=1;
                emp=false;
            }
        }
        else
            emp=("z".equals(inop.lect(1)));
    }

    /**
     * Applique un système de graviter au joueur
     * il tombe toujours sauf s'il est dans un saut ou sur un mur
     */
    private void Gravity(){
        if(!(tableuxDEMur.SurSurface(x,y))&& saut==1)//si sol
        y=y+saut*this.speedOfJump;
        else if(tableuxDEMur.SurSurface(x,y) && iniSaut!=y)//si att nouvelle case
            iniSaut=y;//initialise la hauteur
        //  todo double saut

    }


    /**
     * Enregistre les images du personnage dans Affiche.java,
     * c'est pour changer de perso plus rapidement
     */
    public void AllImage(String u,String l, String r, String f) {
        this.imfixe = new ImageIcon(f).getImage();
        this.imup = new ImageIcon(u).getImage();
        this.imleft = new ImageIcon(l).getImage();
        this.imright = new ImageIcon(r).getImage();
    }

    /**
     * Prend en compte dans quelle situation est le joueur (se déplace vers la gauche,
     * la droite, est en train de sauter ou ne bouge pas)
     * @param situation l'état du joueur dans keyli.txt
     */
    public void setSituation(String situation) {//si x<y
        this.situation = situation;
    }


    /**
     * Change l'image du personnage en fonction de la situation
     * @return l'image voulu
     */
    public Image getImgS(){
        Image result=this.imfixe;
        if (situation.equals("d"))
            result=this.imright;
        if (situation.equals("q"))
            result=this.imleft;

        if (situation.equals("z"))
            result=this.imup;
        return result;
    }
public int getxx(){return this.y;}



  //  public void antiMur(){} //left and right

}
