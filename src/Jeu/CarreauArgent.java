package Jeu;

public class CarreauArgent extends CarreauAction {
	private int montant;

    public CarreauArgent(int montant, int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
        this.montant = montant;
    }
        @Override
    public void action(Joueur j) {}
}