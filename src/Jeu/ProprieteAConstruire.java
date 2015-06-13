package Jeu;

import java.util.ArrayList;
import UI.Interface;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotel = 0;
    private final Groupe groupePropriete;
    private ArrayList<Integer> loyer;

    public ProprieteAConstruire(ArrayList<Integer> loyer, int prixAchat, int numero, Groupe groupePropriete, String nomCarreau, Monopoly monopoly) {
        super(prixAchat, numero, nomCarreau, monopoly);
        this.loyer=loyer;
        this.groupePropriete=groupePropriete;
    }
    public int getNbMaisons() {
        return nbMaisons;
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

    public void achatPropriété(Joueur j) {
        if (getPrixAchat() >= j.getCash()) {
            monopoly.interface_3.afficherPasAssezCash();
        } else {
            monopoly.interface_3.demandeAchat(this);
            if (monopoly.interface_3.lireRéponse()) {
                j.retirerCash(getPrixAchat());
                j.addPropriete(this);
                monopoly.interface_3.afficherCashRestant(j);
                setProprietaire(j);
            }
        }
    }
            
    public ArrayList<ProprieteAConstruire> terrainsConstructibles(){
        ArrayList<ProprieteAConstruire>  a = new ArrayList<>();
        for(int i=0;i<=getGroupe().getProprietes().size()-1;i++){                                                 // le cas ou this est pas un terrain constuctible
            if(getGroupe().getProprietes().get(i).getNbHotel()==0 && getGroupe().getProprietes().get(i).getNbMaisons()<this.getNbMaisons()){    
                a.add(getGroupe().getProprietes().get(i));
            }
        }
        if(a.isEmpty()){
            for(int i=0;i<=getGroupe().getProprietes().size()-1;i++){                                             // le cas ou this est un terrain constuctible
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
        peutConstruire=(getGroupe().rueConstructible() && getGroupe().getPrixAchatMaison() < getProprietaire().getCash() && getProprietaire().possèdeRue(getGroupe()));  
        if(peutConstruire){                                                                                               // si le joueur peut construire
            monopoly.interface_3.demandeConstruction(this);
            veutConstruire=monopoly.interface_3.lireRéponse();                                                          // si le joueur veut construire
            while(peutConstruire&&veutConstruire){
                monopoly.interface_3.demandeOuConstruire(this);
                int i=monopoly.interface_3.lireOuConstruire(this);                                                      // détermine où le joueur construit
                if(i!=0){                                                               //retire une maison ou un hotel de monopoly pour l'ajouter sur la propriété
                    if(this.terrainsConstructibles().get(i-1).getNbMaisons()!=4){
                        this.terrainsConstructibles().get(i-1).setNbMaison(getNbMaisons()+1);                             
                        monopoly.setNbMaisons(monopoly.getNbMaisons()-1);                                                   
                    }else{
                        this.terrainsConstructibles().get(i-1).setNbMaison(0);                        
                        this.terrainsConstructibles().get(i-1).setNbHotel(1);
                        monopoly.setNbMaisons(monopoly.getNbMaisons()+4);
                        monopoly.setNbHotels(monopoly.getNbHotels()-1);
                    }
                    getProprietaire().retirerCash(getGroupe().getPrixAchatMaison());                                          //retire l'argent de la construction
                    peutConstruire=(getGroupe().rueConstructible()&&getGroupe().getPrixAchatMaison()<getProprietaire().getCash());   
                }else{
                    veutConstruire=false;
                }                         
            }
         }else{
            monopoly.interface_3.afficherImpossibleConstruire();
        }
    }

    @Override
    public int calculLoyer() {
        if(nbHotel==1){
            return loyer.get(5);
        }else if(nbMaisons==4){
            return loyer.get(4);
        }else if(nbMaisons==3){
            return loyer.get(3);
        }else if(nbMaisons==2){
            return loyer.get(2);
        }else if(nbMaisons==1){
            return loyer.get(1);
        }else if(nbMaisons==0&& getProprietaire().possèdeRue(getGroupe())){
            return loyer.get(0)*2;
        }else{
            return loyer.get(0);
        }
    }

    @Override
    public void action(Joueur j) {
        if (getProprietaire()== null) {
            achatPropriété(j);
        } else if (getProprietaire()== j) {
            construire();
        } else {
            j.payerLoyer(this);

        }
    }
    
    public void propriétaireAPerdu() {
        this.setProprietaire(null);
        this.monopoly.setNbHotels(this.getNbHotel());
        this.setNbHotel(0);
        this.monopoly.setNbMaisons(this.getNbMaisons());
        this.setNbMaison(0);
    }
}
