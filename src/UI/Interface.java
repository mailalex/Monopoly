package UI;

import Jeu.Joueur;
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
                    afficherJoueurs(monopoly);
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
    
    //Affichage
    
    public void afficherPasAssezCash(){
        afficherln("Pas assez d'argent.");
    }
    public void afficherCashRestant(Joueur j){
        afficherln("Argent restant : "+j.getCash());
    }
    public void afficherDemandeNom(){
        afficherln("Nom de joueur : ");
    }
    public void afficherNomJoueur(Joueur j){
        afficherln(""+j.getCash());
    }
    public void afficherJoueurs(Monopoly m) {
        for (String nomJoueur : m.getJoueurs().keySet()) {
            afficherln(nomJoueur);
        }
    }
    public void afficherCarteSortiePrison(){
        afficherln("Utiliser la carte libérer de prison?(oui/non)");
    }
    public void afficherPositionJoueur(Joueur j){
        afficherln("\t" + monopoly.getCarreaux().get(j.getPositionJoueur()).getNomCarreau());
    }
    public void afficherOrdrePassageJoueur(Object[] max, int i){
        afficherln((String) max[0] + " joue à la place : " + i);
    }
    public void afficherDemandeAchatPropriete(Jeu.ProprieteAConstruire a){
        afficherln("Voulez-vous acheter le terrain " + a.getNomCarreau() + "au prix de " + a.getPrixAchat() + "du groupe" + a.getGroupe().getCouleur().toString() + "?(oui/non)");
    }
    public void afficherDemandeAchat(Jeu.CarreauPropriete a){
        afficherln("Voulez-vous acheter le terrain " + a.getNomCarreau() + "au prix de " + a.getPrixAchat() + "?(oui/non)");
    }
    
    //Lecture
    
    public boolean lireOui(){
        String s;
        s = lire();
        while(!s.equalsIgnoreCase("oui")||!s.equalsIgnoreCase("non")){
            afficherln("Saisie incorecte, répondez par oui ou non");
            s = lire();
        }
        return(s.equalsIgnoreCase("oui"));
    }
    
}