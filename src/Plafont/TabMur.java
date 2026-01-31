package Plafont;

import java.util.ArrayList;
import java.util.Random;

public class TabMur implements SurMur{
    private final ArrayList<Mur> leTabM;


    /**
     * @description initialise le tableau avec un mur part default à l'intérieur
     */
    public TabMur() {
        leTabM=new ArrayList<>();
        leTabM.add(new Mur());
    }

    public ArrayList<Mur> getLeTabM() {
        return leTabM;
    }

    /**
     * @description genre un mur aléatoire et un part default
     */
    public void generateAleatMur(){

        Random aleat = new Random();
        int b1=aleat.nextInt(100)*10;
        int b2=aleat.nextInt(50)*10;
        int b3=aleat.nextInt(60)*10;
        leTabM.add(new Mur(400,200,300,false));
        leTabM.add(new Mur(b1,b2,b3,true));
    }


    public void addMur(Mur mural){
        this.leTabM.add(mural);
    }

    /**
     * @description : verifie su le joueur ne touche pas le dessus de tous les murs
     * @param x Position X
     * @param y Position Y
     * @return true s'il est en contact avec un des murs
     */
    @Override
    public boolean SurSurface(int x,int y){
        boolean veriter=false;
        for (Mur mur : leTabM) {
            if (mur.SurSurface(x, y))
                veriter = true;
        }
        return veriter;
    }

    /**
     * @description : verify si le joueur ne touche pas le dessous de tous les murs
     * @param x Position X
     * @param y Position Y
     * @return true s'il est en contact avec un des murs
     */
    @Override
    public boolean SurDown(int x, int y){

        boolean veriter=false;
        for (Mur mur : leTabM) {
            if (mur.SurDown(x, y))
                veriter = true;
        }
        return veriter;
    }


    /*

    @Override
    public boolean SurFeltRight(int x, int y) {
    boolean result=false;
    if(!SurSurface(x,y)){
        for (int i=1;i<leTabM.size();i++) {//affiche tout ls mur
         if(leTabM.get(i).hitt_LF(x,y));
            result=true;
        }
    }
    return result;
    }
*/



}
