package Jeu;

import java.util.ArrayList;

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
}