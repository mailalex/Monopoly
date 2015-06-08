package Jeu;

public abstract class CarreauPropriete extends Carreau {
    private int prixLoyer;
    private final int prixAchat;
    private Joueur proprietaire;
    
    public CarreauPropriete(int prixLoyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
        this.prixLoyer = prixLoyer;
        this.prixAchat = prixAchat;
    }
    
    public int getPrixVente() {
        return prixAchat;
    }
    
    public int getPrixLoyer() {
        return prixLoyer;
    }
    
    protected void setPrixLoyer(int prixLoyer) {
        this.prixLoyer = prixLoyer;
    }
    
    public abstract void action(Joueur j);
    
    public abstract void calculLoyer();
}