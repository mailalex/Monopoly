package Jeu;

public abstract class Carreau {
    
    private final int numero;
    private final String nomCarreau;
    protected final Monopoly monopoly;

    public Carreau(int numero, String nomCarreau, Monopoly monopoly) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
        this.monopoly = monopoly;
    }
    
    public String getNomCarreau() {
        return nomCarreau;
    }
    
    public abstract void action(Joueur j);
     
}