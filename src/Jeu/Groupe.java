package Jeu;

import java.util.ArrayList;
import java.util.Iterator;

public class Groupe {
    private int prixAchatMaison;
    private CouleurPropriete couleur;
    private final ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();
    private final int nbPropriete;

    public Groupe(int prixAchatMaison, CouleurPropriete couleur) {
        this.prixAchatMaison = prixAchatMaison;
        this.couleur = couleur;
        if(couleur==CouleurPropriete.bleuFonce||couleur==CouleurPropriete.mauve){
            this.nbPropriete=2;
        }else{
            this.nbPropriete=3;
        }
    }
   
    public boolean rueConstructible(){
        int nombreMaison;
        int nombreHotel;
        boolean b;
        if(getNbPropriete()==2){
            nombreHotel=getProprietes().get(0).getNbHotel()+getProprietes().get(1).getNbHotel();
            nombreMaison=getProprietes().get(0).getNbMaisons()+getProprietes().get(1).getNbMaisons();
        }else{
            nombreHotel=getProprietes().get(0).getNbHotel()+getProprietes().get(1).getNbHotel()+getProprietes().get(2).getNbHotel();
            nombreMaison=getProprietes().get(0).getNbMaisons()+getProprietes().get(1).getNbMaisons()+getProprietes().get(2).getNbHotel();
        }
        if(getNbPropriete()==nombreHotel){                                                                 // si tout est déjà construit sur la rue
            b=false;
        }else if(nombreMaison<4*getNbPropriete() && getProprietes().get(1).monopoly.getNbMaisons()==0){      // si il y a plus assez de maison
            b=false;               
        }else if(nombreHotel<getNbPropriete() && getProprietes().get(1).monopoly.getNbHotels()==0){         // si il y a plus assez d'hotel
            b=false;
        }else{b=true;}
        return b;
    }
    
    public ArrayList<ProprieteAConstruire> getProprietes() {
        return proprietes;
    }
    
    public void addPropriete(ProprieteAConstruire p){
        proprietes.add(p);
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
    
    public int getNbPropriete() {
        return nbPropriete;
    }
}
