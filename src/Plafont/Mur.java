package Plafont;

/**
 * @class Mur
 * @description classe de base pour creer un mur (rouge)
 * avec des coordoner X,Y une longeur et un sens(debout ou allonger) car ils on tous 60px de largeur
 */
public class Mur  implements SurMur {

    protected  int cox;
    protected  int coy;
    protected  int size;
    protected final boolean sensUp;

    public void setCox(int cox) {
        this.cox = cox;
    }

    public void setCoy(int coy) {
        this.coy = coy;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCox() {
        return cox;
    }

    public int getCoy() {
        return coy;
    }

    public int getSize() {
        return size;
    }

    public boolean getSensUp() {
        return sensUp;
    }



    public  int getWidth(){
        if(!sensUp)
            return size;
        return 60;
    }


    public  int getHeight(){
    if(sensUp)
        return size;
    return 60;

    }

    /**
     * constructeur pour le mur par default (le sol)
     */
    public Mur() {
        this.sensUp=false;
        this.cox=0;
        this.coy=500;
        this.size=1200;
    }

    public Mur(int cox, int coy, int size, boolean sensUp) {
        this.cox = cox;
        this.coy = coy;
        this.size = size;
        this.sensUp = sensUp;
    }

/**
 * @return true si le joueur touche le dessus du mur
 * */
    @Override
    public boolean SurSurface(int pozX, int pozY){

        int xMax=cox+size;
        if (sensUp)
            xMax=cox+60;
        if (pozY==coy){
            return pozX >= cox && pozX <= xMax;
        }
        return false;
    }

    /**
     * @return true si le joueur touche le dessous du mur
     */
    @Override
    public boolean SurDown(int pozX, int pozY){
        int bas = pozY-getHeight();
        return SurSurface(pozX, bas);
    }


    /**
     *
     * @return true si le joueur touche la gauche ou la droite du mur
     */
    public boolean hitt_LF(int pozX,int pozY){
        pozX-=10;
        int xm=this.cox+getWidth();
        int ym=this.coy+getHeight();
        if(pozX<xm && pozX+20>this.cox)
            return pozY>this.coy+20 && pozY<ym;//recherche pk stp car decalage sur saut +(decalage de sursuface_push)
        return false;
    }
}
/*
u=r*i
u=12v
i=10
r1+r2=1.2w
U2=r1/r1+r2
u2=5v
*/
