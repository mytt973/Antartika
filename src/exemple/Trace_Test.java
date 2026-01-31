package exemple;

import java.util.ArrayList;
import java.util.List;

public class Trace_Test {
    /**
     *
     * Test pour voir si la fonction Trace fonctionne parfaitement
     */
    public static void main(String[] args) {
        String test= "64"; // on choisi un nombre en entré, exemple avec 2⁶
        ArrayList<String> list = new ArrayList<>();
        List<String> read;
        list.add(test);
        Trace.ecrireFichier("empli.txt",list);
        read =Trace.lireFichier("empli.txt");
        String nbTxt= read.get(0);
        int nb = Integer.parseInt(nbTxt);
        nb=nb+5;
        System.out.println(read+" + 5 = "+nb);
    }
}
