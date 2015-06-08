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
        
}