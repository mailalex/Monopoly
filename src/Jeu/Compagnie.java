package Jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixAchat, numero, nomCarreau, monopoly);
    }

    public void achatCompagnie(Joueur j){
        if(getPrixAchat()>j.getCash()){          
            monopoly.interface_3.afficherPasAssezCash();
        }
    monopoly.interface_3.demandeAchat(this);
    if(monopoly.interface_3.lireRéponse()){
        j.retirerCash(getPrixAchat());j.addCompagnie(this);
         monopoly.interface_3.afficherCashRestant(j);
    }
            }
    
    @Override
    public int calculLoyer() {
        if(getProprio().getCompagnies().size()==2){
            return(10*monopoly.getDeplacement());
        }else{
            return(4*monopoly.getDeplacement());
        }
    }

    @Override
    public void action(Joueur j) {
        if(getProprio()==null){
            achatCompagnie(j);
   }else if(getProprio()==j){}
   else{j.payerLoyer(getProprio(), this);}
    }
    
}