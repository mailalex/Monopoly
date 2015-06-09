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

    public void achatPropriété(Joueur j) {
        if (prixAchat >= j.getCash()) {
            monopoly.interface_3.afficherPasAssezCash();
        }
        monopoly.interface_3.afficherDemandeAchat(this);
        if (monopoly.interface_3.lireOui()) {
            j.retirerCash(getPrixAchat());
            j.addPropriete(this);
            monopoly.interface_3.afficherCashRestant(j);
        }
    }
  
    public void construire() {
        if(proprietaire.possèdeRue(groupePropriete)){
         }
    }

    @Override
    public void calculLoyer() {

    }

    @Override
    public void action(Joueur j) {
        if (proprietaire == null) {
            achatPropriété(j);
        } else if (proprietaire == j) {
            construire();
        } else {
            j.payerLoyer(proprietaire, this);

        }
    }
}
