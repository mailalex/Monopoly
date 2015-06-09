package Jeu;

import java.util.ArrayList;

public class Joueur {
    private final String nomJoueur;
    private int positionJoueur = 1;
    private int cash = 1500;
    private final Monopoly monopoly;
    private final ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
    private final ArrayList<Gare> gares = new ArrayList<Gare>();
    private final ArrayList<ProprieteAConstruire> proprietesAConstruire = new ArrayList<ProprieteAConstruire>();
    private int prison = 0;
    private int nbCarteEchapper =0;

    public Joueur(String nomJoueur, Monopoly monopoly) {
        this.nomJoueur = nomJoueur;
        this.monopoly = monopoly;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public int getCash() {
        return cash;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public ArrayList<ProprieteAConstruire> getProprietesAConstruire() {
        return proprietesAConstruire;
    }

    public int getPositionJoueur() {
        return positionJoueur;
    }
    
    public int getPrison() {
        return prison;
    }

    private void setCash(int cash) {
        this.cash = cash;
    }

    private void setPositionJoueur(int positionJoueur) {
        this.positionJoueur = positionJoueur;
    }

    private void setPrison(int prison) {
        this.prison = prison;
    }
    
    public String nomJoueur() {
        return this.getNomJoueur();
    }
    
    public boolean deplacement(int nouvellePosition) {
        this.setPositionJoueur(nouvellePosition%40);
        return true;
    }
    
    public void retirerCash(int montant) {
        this.setCash(this.getCash()-montant);
    }
    
    public void metrreEnPrison() {
        this.setPrison(1);
        this.setPositionJoueur(11);
    }
    
    public void tourPrison() {
        this.setPrison(this.getPrison()+1);
    }
    
    public void sortirPrison() {
        this.setPrison(0);
    }
    
    public boolean estEnPrison() {
        return this.getPrison()!=0;
    }
    
    public void addGare(Gare propriete){
        gares.add(propriete);
    }
    public void addCompagnie(Compagnie propriete){
        compagnies.add(propriete);
    }
    public void addPropriete(ProprieteAConstruire propriete){
        proprietesAConstruire.add(propriete);
    }
    
    public boolean possèdeRue(Groupe groupe){
       return(getProprietesAConstruire().containsAll(groupe.getProprietes()));
    }
    
     public int getNbCarteEchapper() {
        return nbCarteEchapper;
    }
    
    public void addCarteEchapper(){
        nbCarteEchapper++;
    }
    
    public void retirerCarteEchapper(){
        nbCarteEchapper--;
    }
    
    public void payerLoyer(Joueur j, CarreauPropriete c){
        c.calculLoyer();
        c.proprietaire.setCash(cash+c.getLoyer());
        j.setCash(cash-c.getLoyer());
    }
}
