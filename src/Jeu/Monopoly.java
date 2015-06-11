package Jeu;

import UI.Interface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Monopoly {
    private int nbMaisons = 32;

    public int getNbMaisons() {
        return nbMaisons;
    }

    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }
    private int nbHotels = 12;
    private final Hashtable<Integer,Carreau> carreaux = new Hashtable<Integer,Carreau>();
    private final Hashtable<String,Joueur> joueurs = new Hashtable<String,Joueur>();
    private final Hashtable<Integer,String> ordreJoueur = new Hashtable<Integer, String>();
    public Interface interface_3;
    private int dé1;
    private int dé2;
    private int deplacement;
    
    public Monopoly(String dataFilename){
        buildGamePlateau(dataFilename);
        interface_3 = new Interface(this);
        menuUI();
    }

    public Hashtable<String, Joueur> getJoueurs() {
        return joueurs;
    }

    public Hashtable<Integer, Carreau> getCarreaux() {
        return carreaux;
    }

    private void buildGamePlateau(String dataFilename)
    {        
        try{
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            System.out.println(""+data.size());
            //TODO: create cases instead of displaying
            ProprieteAConstruire P = null;
            Groupe g = null;
            for(int i=0; i<data.size(); ++i){
                String caseType = data.get(i)[0];
                if(caseType.compareTo("P") == 0){
                    if (P != null) {
                        if (P.getGroupe().equals(CouleurPropriete.valueOf(data.get(i)[3]))) {
                            g = P.getGroupe();
                        } else {
                            g = new Groupe(Integer.parseInt(data.get(i)[11]), Integer.parseInt(data.get(i)[12]), CouleurPropriete.valueOf(data.get(i)[3]));
                        }
                    } else {
                        g = new Groupe(Integer.parseInt(data.get(i)[11]), Integer.parseInt(data.get(i)[12]), CouleurPropriete.valueOf(data.get(i)[3]));
                    }
                    P = new ProprieteAConstruire(Integer.parseInt(data.get(i)[6]), g, Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[4]), Integer.parseInt(data.get(i)[1]), data.get(i)[2],this);
                    this.carreaux.put(i+1, P);
                }
                else if(caseType.compareTo("G") == 0){
                    Gare G = new Gare(50, Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i+1, G);
                }
                else if(caseType.compareTo("C") == 0){
                    Compagnie C = new Compagnie(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i+1, C);
                }
                else if(caseType.compareTo("CT") == 0){
                    CarreauTirage CT = new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i+1, CT);
                }
                else if(caseType.compareTo("CA") == 0){
                    CarreauArgent CA = new CarreauArgent(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i+1, CA);
                }
                else if(caseType.compareTo("CM") == 0){
                    CarreauMouvement CM = new CarreauMouvement(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i+1, CM);
                }
            }

        } 
        catch(FileNotFoundException e){
            System.err.println("[buildGamePlateau()] : File is not found!");
        }
        catch(IOException e){
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
    {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader  = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line = reader.readLine()) != null){
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    private void menuUI() {
        interface_3.menu();
    }
    
    public boolean créerJoueur() {
        interface_3.afficherDemandeNom();
        String nom = interface_3.lire();
        Joueur J = new Joueur(nom, this);
        this.joueurs.put(J.getNomJoueur(),J);
        return true;
    }
    
    public boolean supprimerJoueur() {
        interface_3.afficherDemandeNom();
        String nom = interface_3.lire();
        this.joueurs.remove(nom);
        return true;
    }
    
    public void débuterPartie() {
        
        Hashtable<String,Integer> jeuDé = new Hashtable<>();
        for (String nomJoueur : joueurs.keySet()) {
            jeuDé.put(nomJoueur, this.lancerDé());
        }
        
        for (int i = 1; i < joueurs.size()+1; i++) {
            
            Object[] max = new Object[2];
            max[0] = "plop";
            max[1] = (int) 0;

            for (String nomJoueur : jeuDé.keySet()) {
                if (jeuDé.get(nomJoueur) >= (int) max[1]) {
                   max[0] = nomJoueur;
                   max[1] = jeuDé.get(nomJoueur); 
                }
            }
            this.ordreJoueur.put(i, (String) max[0]);
            jeuDé.remove((String) max[0]);
            this.interface_3.afficherOrdrePassageJoueur(max, i);
        }
        this.jouerPartie();
    }
    
    private void jouerPartie() {
<<<<<<< HEAD
        while (this.ordreJoueur.size() >= 2) {
            this.interface_3.afficherln("");
            this.interface_3.afficherln("");
=======
        while (this.ordreJoueur.size() > 2) {
>>>>>>> origin/Tim
            for (int i = 1; i <= this.ordreJoueur.size(); i++) {
                int y = 1;
                String nomJoueur = this.ordreJoueur.get(i);
                Joueur j = this.joueurs.get(nomJoueur);
<<<<<<< HEAD
                this.interface_3.afficherln(nomJoueur);
=======
                this.interface_3.afficherNomJoueur(j);
>>>>>>> origin/Tim
                boolean b = this.jouerCoup(j,y);
<<<<<<< HEAD
                this.interface_3.afficherln("\t"+j.getPositionJoueur());
                this.interface_3.afficherln("\t" + this.carreaux.get(j.getPositionJoueur()).getNomCarreau());
=======
                this.interface_3.afficherPositionJoueur(j);
>>>>>>> origin/Tim
                while (b && y<3) {                
                    b = this.jouerCoup(j,y);
                    y++;
<<<<<<< HEAD
                    this.interface_3.afficherln("\t"+j.getPositionJoueur());
                    Carreau c = this.carreaux.get(j.getPositionJoueur());
                    this.interface_3.afficherln("\t" + c.getNomCarreau());
                    if (j.getPositionJoueur() == 31) {
                        j.metrreEnPrison();
                        this.interface_3.afficherln("\t"+j.getPositionJoueur());
                        c = this.carreaux.get(j.getPositionJoueur());
                        this.interface_3.afficherln("\t" + c.getNomCarreau());
                    }
=======
                    this.interface_3.afficherPositionJoueur(j);
>>>>>>> origin/Tim
                }
                //faire jouer coup au joueur sélectionner
                if (j.getCash() <= 0) {
                    j.joueurMeurt(i);
                }
            }
        }
    }

    public int getDeplacement() {
        return deplacement;
    }
    
    private boolean jouerCoup(Joueur j, int y){
        if (j.estEnPrison() && j.getNbCarteEchapper()>0){
            this.interface_3.afficherCarteSortiePrison();
            if(this.interface_3.lireRéponse()){
                j.retirerCarteEchapper();
                j.sortirPrison();
            }
        }
        dé1 = this.lancerDé();
        dé2 = this.lancerDé();
        deplacement = dé1 + dé2;
        if ((j.estEnPrison() && dé1==dé2) || !j.estEnPrison()) {
            j.sortirPrison();
            j.deplacement(j.getPositionJoueur()+deplacement);
            int p = j.getPositionJoueur();
            Carreau c = this.carreaux.get(p);
            return dé1==dé2;
        } else if (j.getPrison() == 3) {
            j.retirerCash(50);
            if (j.getCash() > 0) {
                j.sortirPrison();
                j.deplacement(j.getPositionJoueur()+deplacement);
            }
            return dé1==dé2;
        } else if (!j.estEnPrison() && dé1==dé2 && y == 3) {
            j.metrreEnPrison();
            return false;
        } else {
            j.tourPrison();
            return false;
        }
    }
    
    private int lancerDé() {
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }

    void retirerJoueur(Joueur j, int i) {
        this.joueurs.remove(j.getNomJoueur());
        this.ordreJoueur.remove(i);
    }
} 
