package Jeu;

import java.util.ArrayList;
import UI.Interface;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotel = 0;
    private int loyerMaison;
    private final Groupe groupePropriete;

    public ProprieteAConstruire(int loyerMaison, Groupe groupePropriete, int prixLoyer, int prixAchat, int numero, String nomCarreau, Monopoly monopoly) {
        super(prixLoyer, prixAchat, numero, nomCarreau, monopoly);
        this.loyerMaison = loyerMaison;
        this.groupePropriete = groupePropriete;
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getLoyerMaison() {
        return loyerMaison;
    }

    public Groupe getGroupe() {
        return groupePropriete;
    }

    public void setNbMaison(int nbMaison) {
        this.nbMaisons = nbMaison;
    }

    public int getNbHotel() {
        return nbHotel;
    }

    public void setNbHotel(int nbHotel) {
        this.nbHotel = nbHotel;
    }
<<<<<<< HEAD
    
    public void achatPropriété(Joueur j){
        if(getPrixAchat()>j.getCash()){
            monopoly.interface_3.afficherln("Pas assez d'argent pour acheter "+getNomCarreau());
=======

    public void achatPropriété(Joueur j) {
        if (prixAchat >= j.getCash()) {
            monopoly.interface_3.afficherPasAssezCash();
>>>>>>> origin/Tim
        }
<<<<<<< HEAD
        monopoly.interface_3.afficherln("Voulez-vous acheter le terrain "+getNomCarreau()+ " au prix de "+getPrixAchat()+"du groupe"+groupePropriete.getCouleur().toString()+"?(oui/non)");
        if(monopoly.interface_3.lire()=="oui"){
            j.retirerCash(getPrixAchat());j.addPropriete(this);
             monopoly.interface_3.afficherln("Argent restant : "+j.getCash());
=======
        monopoly.interface_3.demandeAchat(this);
        if (monopoly.interface_3.lireRéponse()) {
            j.retirerCash(getPrixAchat());
            j.addPropriete(this);
            monopoly.interface_3.afficherCashRestant(j);
>>>>>>> origin/Tim
        }
    }
    
<<<<<<< HEAD
    public void construire(){
        if(getProprietaire().possèdeRue(groupePropriete)){
            groupePropriete.getPrixAchatMaison();
            
=======
    public ArrayList<ProprieteAConstruire> terrainsConstructibles(){
        ArrayList<ProprieteAConstruire>  a = null;
        for(int i=0;i<=getGroupe().getProprietes().size();i++){                                                 // le cas ou this est pas un terrain constuctible
            if(getGroupe().getProprietes().get(i).getNbHotel()==0&&getGroupe().getProprietes().get(i).getNbMaisons()<this.getNbMaisons()){    
                a.add(getGroupe().getProprietes().get(i));
            }
>>>>>>> origin/Tim
        }
        if(a==null){
            for(int i=0;i<=getGroupe().getProprietes().size();i++){                                             // le cas ou this est un terrain constuctible
                if(getGroupe().getProprietes().get(i).getNbHotel()==0&&getGroupe().getProprietes().get(i).getNbMaisons()==this.getNbMaisons()){
                    a.add(getGroupe().getProprietes().get(i));
                }
            }
        }
        return a;
    }
  
    public void construire() {
        boolean veutConstruire = true;
        boolean peutConstruire = true;
        peutConstruire=(!getGroupe().rueConstructible()&&getGroupe().getPrixAchatMaison()<getProprio().getCash()&&getProprio().possèdeRue(groupePropriete));  
        if(peutConstruire){                                                                                               // si le joueur peut construire
            monopoly.interface_3.demandeConstruction(this);
            veutConstruire=monopoly.interface_3.lireRéponse();                                                          // si le joueur veut construire
            while(peutConstruire&&veutConstruire){
                monopoly.interface_3.demandeOuConstruire(this);
                int j=monopoly.interface_3.lireOuConstruire(this);                                                      // détermine où le joueur construit
                if(j!=0){                                                       //retire une maison ou un hotel de monopoly pour l'ajouter sur la propriété
                    if(this.terrainsConstructibles().get(j).getNbMaisons()!=4){
                        this.terrainsConstructibles().get(j).setNbMaison(getNbMaisons()+1);                             
                        monopoly.setNbMaisons(monopoly.getNbMaisons()-1);                                                   
                    }else{
                        this.terrainsConstructibles().get(j).setNbMaison(0);
                        this.terrainsConstructibles().get(j).setNbHotel(1);
                        monopoly.setNbMaisons(monopoly.getNbMaisons()+4);
                        monopoly.setNbHotels(monopoly.getNbHotels()-1);
                    }
                    getProprio().retirerCash(getGroupe().getPrixAchatMaison());                                          //retire l'argent de la construction
                }
                peutConstruire=(!getGroupe().rueConstructible()&&getGroupe().getPrixAchatMaison()<getProprio().getCash());
                monopoly.interface_3.demandeConstruction(this);
                veutConstruire=monopoly.interface_3.lireRéponse();                          // revérifie sir le joueur peut et veux construire dans le loop
            }
         }
    }

    @Override
    public void calculLoyer() {

    }

    @Override
    public void action(Joueur j) {
<<<<<<< HEAD
        if(getProprietaire()==null){
            achatPropriété(j);
        }else if(getProprietaire()==j){
            construire();
        }else{
            j.payerLoyer(getProprietaire(),this);
=======
        if (proprietaire == null) {
            achatPropriété(j);
        } else if (proprietaire == j) {
            construire();
        } else {
            j.payerLoyer(proprietaire, this);

>>>>>>> origin/Tim
        }
    }
<<<<<<< HEAD
    
    public void propriétaireAPerdu() {
        this.setProprietaire(null);
        this.setNbHotel(0);
        this.setNbMaison(0);
    }
=======
>>>>>>> origin/Tim
}
