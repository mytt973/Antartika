package exemple;
import java.util.ArrayList;


/**
 *
 * @description sert d'interface entre le l application et le fichier texte (via Trace),
 * gere toutes les lectures ou ecritures de keyli.txt
 *
 */
public class Intar {

   private ArrayList<String> inliste;


    /**
     * @description  initiallise la class avec une liste de 2 elements
     */
    public void init_liste(){

        inliste = new ArrayList<>();
        inliste.add("1");inliste.add("1");
    }

    /**
     * @description enregistre dans le fichier texte,
     * les informations donné par les touche du clavier
     * @param input1 valeur du a changer
     * @param nb sur quelle element du tableau il faut changer
     */
    public void mod_sens(String input1,int nb)
    {
        inliste.set(nb,input1);
        Trace.ecrireFichier("keyli.txt",inliste);
        inliste.set(1,"s");
    }

    /**
     * @description calcule la position du joueur en fonction de sa direction
     * @param droite char equivalent pour aller a droite
     * @param gauche char equivalent pour aller a gauche
     * @param nb_get sur quelle element du tableau il faut lire
     * @return la position du joueur
     */
    public int push_nb(String droite, String gauche, int nb_get){
        String sens=Trace.lireFichier("keyli.txt").get(nb_get);
        int nb=0;
        if(sens.equals(droite))
            nb=-20;
        else if(sens.equals(gauche))
            nb=20;
        return nb;
    }

    /**
     * @description permet de lire un element de keyli.txt
     * @param nb_get sur quelle element du tableau il faut lire
     * @return l element choisie sur le fichier texte
     */
    public String lect(int nb_get){
      return   Trace.lireFichier("keyli.txt").get(nb_get);
    }

}
