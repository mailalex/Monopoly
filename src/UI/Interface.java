package UI;

import Jeu.Monopoly;
import java.io.PrintStream;
import java.util.Scanner;

public class Interface {
    public Monopoly monopoly;
    private final PrintStream so = System.out;
    private final Scanner sc = new Scanner(System.in);

    public Interface(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
    
    public void menu() {
        Boolean continuer = new Boolean(true);
        
        while (continuer) {            
            so.println("\n\n");
            so.println("\t*******************************");
            so.println("\t*\t      Menu            *");
            so.println("\t*  *  *  *  *  *  *  *  *  *  *");
            so.println("\t*1:\tCommencer à jouer     *");
            so.println("\t*2:\tAjouter un joueur     *");
            so.println("\t*3:\tSupprimer un joueur   *");
            so.println("\t*4:\tListe des joueurs     *");
            so.println("\t*5:\tQuitter               *");
            so.println("\t*******************************");

            so.print("      Votre Choix : ");
            int choix = sc.nextInt();

            switch (choix) {
                case 1:{
                    so.println("\n");
                    this.monopoly.débuterPartie();
                    continuer=true;
                    break;
                }
                case 2:{
                    monopoly.créerJoueur();
                    continuer=true;
                    break;
                }
                case 3:{
                    monopoly.supprimerJoueur();
                    continuer=true;
                    break;
                }
                case 4:{
                    this.monopoly.afficherJoueurs();
                    continuer=true;
                    break;
                }
                default:
                    continuer=false;
                    break;
            }
        }
    }
    
    public void afficherln(String i) {
        so.println(i);
    }
    
    public void afficher(String i) {
        so.print(i);
    }
    
    public String lire() {
        return sc.next();
    }
    
    public int lireInt() {
        return sc.nextInt();
    }
}