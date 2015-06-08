package Jeu;

import java.util.ArrayList;

public class Groupe {
    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private final ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

    public Groupe(int prixAchatMaison, int prixAchatHotel, CouleurPropriete couleur) {
        this.prixAchatMaison = prixAchatMaison;
        this.prixAchatHotel = prixAchatHotel;
        this.couleur = couleur;
    }
    
    public ArrayList<ProprieteAConstruire> getProprietes() {
        return proprietes;
    }

    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }

    public void setPrixAchatMaison(int prixAchatMaison) {
        this.prixAchatMaison = prixAchatMaison;
    }

    public int getPrixAchatHotel() {
        return prixAchatHotel;
    }

    public void setPrixAchatHotel(int prixAchatHotel) {
        this.prixAchatHotel = prixAchatHotel;
    }

    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
}
