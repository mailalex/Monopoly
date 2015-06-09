package Jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(0, prixAchat, numero, nomCarreau, monopoly);
    }

    public void achatCompagnie(Joueur j){
        if(prixAchat>j.getCash()){          
            monopoly.interface_3.afficherPasAssezCash();
        }
    monopoly.interface_3.afficherDemandeAchat(this);
    if(monopoly.interface_3.lireOui()){
        j.retirerCash(getPrixAchat());j.addCompagnie(this);
         monopoly.interface_3.afficherCashRestant(j);
    }
            }
    
    @Override
    public void calculLoyer() {
        if(proprietaire.getCompagnies().size()==2){
            setLoyer(10*monopoly.getDeplacement());
        }else{
            setLoyer(4*monopoly.getDeplacement());
        }
    }

    @Override
    public void action(Joueur j) {
        if(proprietaire==null){
            achatCompagnie(j);
   }else if(proprietaire==j){}
   else{j.payerLoyer(proprietaire, this);}
    }
    
}