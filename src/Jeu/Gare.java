package Jeu;

public class Gare extends CarreauPropriete {

    public Gare(int prixLoyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixLoyer, prixAchat, numero, nomCarreau, monopoly);
    }
    
    public void achatGare(Joueur j){
        if(prixAchat>j.getCash()){
            monopoly.interface_3.afficherPasAssezCash();
        }
    monopoly.interface_3.afficherDemandeAchat(this);
    if(monopoly.interface_3.lireOui()){
        j.retirerCash(getPrixAchat());j.addGare(this);
         monopoly.interface_3.afficherCashRestant(j);
    }
            }

    @Override
    public void calculLoyer() {
        setLoyer(25*proprietaire.getGares().size());
    }

    @Override
    public void action(Joueur j) {
         if(proprietaire==null){
       achatGare(j);
   }else if(proprietaire==j){}
   else{j.payerLoyer(proprietaire, this);}
   }
    
}
    
