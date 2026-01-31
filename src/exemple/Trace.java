package exemple;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @classe GestionFichiers
 *
 * @description Classe qui gere les acces aux fichiers textes
 *
 * @author Julien
 *
 */

public class Trace {

    /**
     * cette fonction va lire le contenu d'un fichier
     * @param nomFichier : nom du fichier en entree
     * @return resultat : liste des lignes
     */
    public static List<String> lireFichier(String nomFichier){
        BufferedReader fluxEntree=null;
        String ligneLue;
        List<String> lignes = new ArrayList<String>();
        try{
            fluxEntree = new BufferedReader(new FileReader(nomFichier));
            ligneLue = fluxEntree.readLine();
            while(ligneLue!=null){
                lignes.add(ligneLue);
                ligneLue = fluxEntree.readLine();
            }
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        try{
            fluxEntree.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return lignes;
    }

    /**
     * Ce procédure écrit dans le fichier les lignes de la liste
     * @param nomFichier : nom du fichier dans lequel écrire
     * @param lignes : liste des lignes à écrire
     */
    public static void ecrireFichier(String nomFichier, List<String> lignes){
        Writer fluxSortie=null;
        try{
            fluxSortie = new PrintWriter(new BufferedWriter(new FileWriter(
                    nomFichier)));
            for(int i=0;i<lignes.size()-1;i++){
                fluxSortie.write(lignes.get(i)+"\n");
            }
            fluxSortie.write(lignes.get(lignes.size()-1));
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        try{
            fluxSortie.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}