package Jeu;

public abstract class CarreauPropriete extends Carreau {
    private final int prixAchat;
    private Joueur proprietaire;
    public CarreauPropriete( int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
        this.prixAchat = prixAchat;
    }
    
    public int getPrixAchat() {
        return prixAchat;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    protected void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    @Override
    public abstract void action(Joueur j);

    public abstract int calculLoyer();
}
