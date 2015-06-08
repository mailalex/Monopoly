package Jeu;

public abstract class Carreau {
    
    private final int numero;
    private String nomCarreau;
    private final Monopoly monopoly;

    public Carreau(int numero, String nomCarreau, Monopoly monopoly) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
        this.monopoly = monopoly;
    }
    
    public String getNomCarreau() {
        return nomCarreau;
    }

    public void setNomCarreau(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }
}