package Jeu;

import java.util.ArrayList;

public class Groupe {
    private int prixAchatMaison;
    private CouleurPropriete couleur;
    private final ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

    public Groupe(int prixAchatMaison, int prixAchatHotel, CouleurPropriete couleur) {
        this.prixAchatMaison = prixAchatMaison;
        this.couleur = couleur;
    }
   
    public boolean toutConstruit(){
        boolean b =true;
        for(int i = 0 ;i<=proprietes.size();i++){
            if(proprietes.get(i).getNbHotel()==0){
                b=false;
            }
        }
        return b;
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

    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
}