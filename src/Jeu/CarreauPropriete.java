package Jeu;

public abstract class CarreauPropriete extends Carreau {
    private int loyer;
    private final int prixAchat;
    private Joueur proprietaire;
    
    public CarreauPropriete(int loyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
        this.loyer = loyer;
        this.prixAchat = prixAchat;
    }
    
    public int getPrixAchat() {
        return prixAchat;
    }
    
    public int getLoyer() {
        return loyer;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    protected void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    protected void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    public abstract void action(Joueur j);

    public abstract void calculLoyer();
}
