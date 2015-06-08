package Jeu;

import java.util.ArrayList;
import UI.Interface;

public class ProprieteAConstruire extends CarreauPropriete {
    
        private int nbMaisons = 0;
	private int nbHotel = 0;
	private int loyerMaison;
	private final Groupe groupePropriete;

    public ProprieteAConstruire(int loyerMaison, Groupe groupePropriete, int prixLoyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixLoyer, prixAchat, numero, nomCarreau, monopoly);
        this.loyerMaison = loyerMaison;
        this.groupePropriete = groupePropriete;
    }
    
    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getLoyerMaison() {
        return loyerMaison;
    }

    public Groupe getGroupe() {
        return groupePropriete;
    }

    public void setNbMaison(int nbMaison) {
        this.nbMaisons = nbMaison;
    }

    public int getNbHotel() {
        return nbHotel;
    }

    public void setNbHotel(int nbHotel) {
        this.nbHotel = nbHotel;
    }
    
    public void achatPropriété(Joueur j){
        if(prixAchat>j.getCash()){
            monopoly.interface_3.afficherln("Pas assez d'argent pour acheter "+getNomCarreau());
        }
    monopoly.interface_3.afficherln("Voulez-vous acheter le terrain "+getNomCarreau()+ "au prix de "+getPrixAchat()+"du groupe"+groupePropriete.getCouleur().toString()+"?(oui/non)");
    if(monopoly.interface_3.lire()=="oui"){
        j.retirerCash(getPrixAchat());j.addPropriete(this);
         monopoly.interface_3.afficherln("Argent restant : "+j.getCash());
    }
            }
    
    public void construire(){}
    
    public void payerLoyer(Joueur j){}

    @Override
    public void calculLoyer() {
       
    }

    @Override
    public void action(Joueur j) {
   if(proprietaire==null){
       achatPropriété(j);
   }else if(proprietaire==j){
       construire();
   }else{payerLoyer(j);
       
   }
    }
}