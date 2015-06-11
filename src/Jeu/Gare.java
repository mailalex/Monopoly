package Jeu;

public class Gare extends CarreauPropriete {

    public Gare(int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixAchat, numero, nomCarreau, monopoly);
    }
    
    public void achatGare(Joueur j){
        if(getPrixAchat()>j.getCash()){
            monopoly.interface_3.afficherln("Pas assez d'argent pour acheter "+getNomCarreau());
            if(getPrixAchat()>j.getCash()){
                monopoly.interface_3.afficherPasAssezCash();
            }
            monopoly.interface_3.afficherln("Voulez-vous acheter la "+getNomCarreau()+ " au prix de "+getPrixAchat()+"?(oui/non)");
            if(monopoly.interface_3.lire()=="oui"){
                j.retirerCash(getPrixAchat());j.addGare(this);
                 monopoly.interface_3.afficherln("Argent restant : "+j.getCash());
            }
            monopoly.interface_3.demandeAchat(this);
            if(monopoly.interface_3.lireRéponse()){
                j.retirerCash(getPrixAchat());j.addGare(this);
                 monopoly.interface_3.afficherCashRestant(j);
            }
        }
    }

    @Override
    public int calculLoyer() {
        return(25*getProprietaire().getGares().size());

    }

    @Override
    public void action(Joueur j) {
        if(getProprietaire()==null){
            achatGare(j);
        } else if(getProprietaire()!=j){
            j.payerLoyer(getProprietaire(), this);
        }
   }
}
    
