package Jeu;

public abstract class CarreauPropriete extends Carreau {
    protected final int prixAchat;
    protected Joueur proprietaire;
    protected int loyer;
    
    public CarreauPropriete(int prixLoyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
        this.prixAchat = prixAchat;
    }
    
    public int getPrixAchat() {
        return prixAchat;
    }
    
     public Joueur getProprio() {
        return proprietaire;
        }
    
     public abstract void action(Joueur j);

    public abstract void calculLoyer();

}