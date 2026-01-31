package Plafont;

import Plafont.Mur;

public class Antiter extends Mur{
    /**
     * @description l equivalent des mur bleu dans le jeux
     */
    private int pv;
    private int att;

    private String type;


    public Antiter(int cox, int coy, int size, boolean sensUp, int pv, int att,  String type) {
        super(cox, coy, size, sensUp);
        this.pv = pv;
        this.att = att;
        this.type = type;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }



    @Override
    public String toString() {
        return "Antiter{" +
                "pv=" + pv +
                ", att=" + att +
                ", type='" + type + '\'' +
                '}';
    }


    /**
     *
     * @return true si le joueur est bien en contact avec l Antiter
     */
    public boolean teste(int x,int W,int co){
        int xm=co+W;
        boolean xT= x<xm || x+50<xm;
        boolean xD= x>co || x+50>co;
        return xT && xD;

    }

    /**
     * @descpition gere les conditions et les actions de l antiter si il doit ce deplacer ou attirer le joueur
     * @param x position X
     * @param y position Y
     * @param action condition (=='v') pour retourner une valeur != 0
     * @return valeur auquelle le joueur sera deplacer (afin de l attirer au milieu de l antiter)
     */
   public int effectAntiter(int x, int y,char action){

       if(teste(x,getWidth(),this.cox)&&teste(y,getHeight(),this.coy))
    {
        byte result=10;
        if(x<=this.cox)
            System.out.println("<");
        else
            result=-10;
        doin(result,action);

        if (action=='v')
            return  result;
    }
    return 0;
    }

    /**
     * @desciption les actions que fait l antiter
     * avec 'v' retire des pv, et sans 'v' pousse l antiter plus loin
     * @param sens regarde si le joueur est du coter gauche ou droite de l objet
     * @param act condition (=='v')
     */
    private void doin(int sens, char act){
        if (act=='v')
        {
            this.pv -= 1;
        }
        else
        this.cox+=(sens*3);
    }
}