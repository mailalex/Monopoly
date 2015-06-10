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
   
    public boolean rueConstructible(){
        int nombreMaison=0;
        int nombreHotel=0;
        boolean b;
        int i;
        for(i=0;i<=getProprietes().size();i++){
            nombreHotel+=getProprietes().get(i).getNbHotel();
            nombreMaison+=getProprietes().get(i).getNbMaisons();
        }
        if(i==nombreHotel){                                                                 // si tout est déjà construit sur la rue
            b=false;
        }else if(nombreMaison<4*i&&getProprietes().get(i).monopoly.getNbMaisons()!=0){      // si il y a plus assez de maison
            b=false;               
        }else if (nombreHotel<i&&getProprietes().get(i).monopoly.getNbHotels()!=0){         // si il y a plus assez d'hotel
            b=false;
        }else{b=true;}
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