package UI;

import Jeu.Carreau;
import Jeu.CarreauPropriete;
import Jeu.Joueur;
import Jeu.Monopoly;
import Jeu.ProprieteAConstruire;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
    public Monopoly monopoly;
    private final PrintStream so = System.out;
    private final Scanner sc = new Scanner(System.in);

    public Interface(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
    
    public void menu() {
        Boolean continuer = true;
        
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
                    continuer=false;
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
        afficherln("Cash restant: "+j.getCash());
    }
    public void afficherImpossibleConstruire(){
        afficherln("Impossible de construire une maison");
    }
    public void afficherDemandeNom(){
        afficherln("Nom de joueur : ");
    }
    public void afficherNomJoueur(Joueur j){
        afficherln("Au tour de "+j.getNomJoueur());
        afficherln("Cash: "+j.getCash());
    }
    public void afficherJoueurs(Monopoly m) {
        afficherln("Nom des joueurs : ");
        for (String nomJoueur : m.getJoueurs().keySet()) {
            afficherln(nomJoueur);
        }
    }
    public void afficherDéplacement(Joueur j, int dé1, int dé2){
        if(dé1==dé2){
            afficherln("Double "+dé1+"!");
        }
        afficherln(j.getNomJoueur()+" se déplace de "+ (dé1+dé2) +" et est arrivé sur le carreau "+monopoly.getCarreaux().get(j.getPositionJoueur()).getNomCarreau()+" , case numéro "+(j.getPositionJoueur()-1));
    }
    public void afficherCarteSortiePrison(){
        afficherln("Utiliser la carte libérer de prison?(oui/non)");
    }
    public void afficherOrdrePassageJoueur(Object[] max, int i){
        afficherln((String) max[0] + " joue à la place : " + i);
    }
    public void afficherSortiePrison(Joueur j){
         afficherln(j.getNomJoueur()+"a fait un double et est sorti de prison");
    }
    public void afficherJoueurLoyer(Joueur j, CarreauPropriete c){
        afficherln(j.getNomJoueur()+" est tombé sur la case de "+c.getProprietaire().getNomJoueur()+" et doit lui payer "+c.calculLoyer());
        afficherCashRestant(j);
    }
    public void afficherJoueurMort(Joueur j){
        afficherln("Le joueur "+j.getNomJoueur()+" n'a plus d'argent, il est donc éliminé est toutes ces propriétés sont de nouveau à vendre.");
    }
    public void afficerPassageCaseDépart(){
        afficherln("Vous êtes passé par la case départ, recevez 200€");
    }
    
    //Demande
    
    public void demandeAchatPropriete(Jeu.ProprieteAConstruire a){
        afficherln("Voulez-vous acheter le terrain " + a.getNomCarreau() + " au prix de " + a.getPrixAchat() + " du groupe " + a.getGroupe().getCouleur().toString() + "?(oui/non)");
    }
    public void demandeAchat(Jeu.CarreauPropriete a){
        afficherln("Voulez-vous acheter le terrain " + a.getNomCarreau() + " au prix de " + a.getPrixAchat() + "?(oui/non)");
    }
    public void demandeConstruction(Jeu.ProprieteAConstruire a){
        afficherln("La rue "+a.getGroupe().getCouleur().toString()+" est composée de: ");
        for(int i = 0 ;i<=a.getGroupe().getProprietes().size();i++){
            afficherln(a.getGroupe().getProprietes().get(i).getNomCarreau()+" : "+
                    a.getGroupe().getProprietes().get(i).getNbMaisons()+" maisons, "+a.getGroupe().getProprietes().get(i).getNbHotel()+" hotels.");
            afficherln("Prix de construction: "+a.getGroupe().getPrixAchatMaison());
            afficherln("Voulez vous construire sur cette rue?");
            }
    }
    public void demandeOuConstruire(Jeu.ProprieteAConstruire a){
        afficherln("Sur quel propriété voulez vous construire parmis :");
        for(int i = 0 ;i<= a.terrainsConstructibles().size();i++){
            afficherln(a.terrainsConstructibles().get(i).getNomCarreau()+" terrain num "+i);
        }
        afficherln("saisissez le numéro de la propriété sur laquel vous voulez construire ou 0 pour arêter les constructions.");
    }
    
    //Lecture
    
    public boolean lireRéponse(){
        String s;
        s = lire();
        s = s.toLowerCase();
        while(!(s.equals("oui") || s.equals("non"))){
            afficherln("Saisie incorecte, répondez par oui ou non");
            s = lire();
            s = s.toLowerCase();
        }
        return(s.equalsIgnoreCase("oui"));
    }
    public int lireOuConstruire(Jeu.ProprieteAConstruire a){
        int j =lireInt();
        while(a.terrainsConstructibles().size()<=j||j<=0){
            afficherln("Saisie incorecte");
            j = lireInt();
        }
        return j;
    }
}
