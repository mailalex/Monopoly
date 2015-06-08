package Jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(0, prixAchat, numero, nomCarreau, monopoly);
    }

    @Override
    public void calculLoyer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void action(Joueur j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}