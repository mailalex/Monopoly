package Jeu;

public class Gare extends CarreauPropriete {

    public Gare(int prixLoyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixLoyer, prixAchat, numero, nomCarreau, monopoly);
    }
    
    public void achatGare(Joueur j){
        if(getPrixAchat()>j.getCash()){
            monopoly.interface_3.afficherln("Pas assez d'argent pour acheter "+getNomCarreau());
        }
    monopoly.interface_3.afficherln("Voulez-vous acheter la "+getNomCarreau()+ "au prix de "+getPrixAchat()+"?(oui/non)");
    if(monopoly.interface_3.lire()=="oui"){
        j.retirerCash(getPrixAchat());j.addGare(this);
         monopoly.interface_3.afficherln("Argent restant : "+j.getCash());
    }
            }

    @Override
    public void calculLoyer() {
        setLoyer(25*getProprietaire().getGares().size());
    }

    @Override
    public void action(Joueur j) {
         if(getProprietaire()==null){
       achatGare(j);
   } if(getProprietaire()==j){}
   else{j.payerLoyer(getProprietaire(), this);}
   }
    
}
    
