package Jeu;

public class Gare extends CarreauPropriete {

    public Gare(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixAchat, numero, nomCarreau, monopoly);
    }
    
    public void achatGare(Joueur j){
        if(prixAchat>j.getCash()){
            monopoly.interface_3.afficherPasAssezCash();
        }
    monopoly.interface_3.demandeAchat(this);
    if(monopoly.interface_3.lireRéponse()){
        j.retirerCash(getPrixAchat());j.addGare(this);
         monopoly.interface_3.afficherCashRestant(j);
    }
            }

    @Override
    public int calculLoyer() {
        return(25*proprietaire.getGares().size());
    }

    @Override
    public void action(Joueur j) {
         if(proprietaire==null){
       achatGare(j);
   }else if(proprietaire==j){}
   else{j.payerLoyer(proprietaire, this);}
   }
    
}
    
