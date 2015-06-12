package Jeu;

public class Gare extends CarreauPropriete {

    public Gare(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixAchat, numero, nomCarreau, monopoly);
    }

    public void achatGare(Joueur j) {
        if (getPrixAchat() >= j.getCash()) {
            monopoly.interface_3.afficherPasAssezCash();
        } else {
            monopoly.interface_3.demandeAchat(this);
            if (monopoly.interface_3.lireRéponse()) {
                j.retirerCash(getPrixAchat());
                j.addGare(this);
                monopoly.interface_3.afficherCashRestant(j);
                setProprietaire(j);
            }
        }
    }

    @Override
    public int calculLoyer() {
        return (25 * getProprietaire().getGares().size());
    }

    @Override
    public void action(Joueur j) {
        if (getProprietaire() == null) {
            achatGare(j);
        } else if (getProprietaire() != j) {
            j.payerLoyer(this);
        }
    }
}
