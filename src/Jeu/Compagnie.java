package Jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(0, prixAchat, numero, nomCarreau, monopoly);
    }

    public void achatCompagnie(Joueur j){
        if(prixAchat>j.getCash()){          
            monopoly.interface_3.afficherln("Pas assez d'argent pour acheter "+getNomCarreau());
        }
    monopoly.interface_3.afficherln("Voulez-vous acheter la "+getNomCarreau()+ "au prix de "+getPrixAchat()+"?(oui/non)");
    if(monopoly.interface_3.lire()=="oui"){
        j.retirerCash(getPrixAchat());j.addCompagnie(this);
         monopoly.interface_3.afficherln("Argent restant : "+j.getCash());
    }
            }
    
    @Override
    public void calculLoyer() {
        if(proprietaire.getCompagnies().size()==2){
            loyer=10*monopoly.getDeplacement();
        }else{
            loyer=4*monopoly.getDeplacement();
        }
    }

    @Override
    public void action(Joueur j) {
        if(proprietaire==null){
            achatCompagnie(j);
   } if(proprietaire==j){}
   else{j.payerLoyer(proprietaire, this);}
    }
    
}