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
    private int nbHotels = 12;

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
    private final Hashtable<Integer, Carreau> carreaux = new Hashtable<Integer, Carreau>();
    private final Hashtable<String, Joueur> joueurs = new Hashtable<String, Joueur>();
    private final Hashtable<Integer, String> ordreJoueur = new Hashtable<Integer, String>();
    public Interface interface_3;
    private int dé1;
    private int dé2;
    private int deplacement;

    public Monopoly(String dataFilename) {
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

    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            ProprieteAConstruire P = null;
            Groupe g = null;
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    if (P != null) {
                        if (P.getGroupe().getCouleur().equals(CouleurPropriete.valueOf(data.get(i)[3]))) {
                            g = P.getGroupe();
                        } else {
                            g = new Groupe(Integer.parseInt(data.get(i)[11]), CouleurPropriete.valueOf(data.get(i)[3]));
                        }
                    } else {
                        g = new Groupe(Integer.parseInt(data.get(i)[11]), CouleurPropriete.valueOf(data.get(i)[3]));
                    }
                    ArrayList<Integer> loyer = new ArrayList();
                    loyer.add(Integer.parseInt(data.get(i)[5]));
                    loyer.add(Integer.parseInt(data.get(i)[6]));
                    loyer.add(Integer.parseInt(data.get(i)[7]));
                    loyer.add(Integer.parseInt(data.get(i)[8]));
                    loyer.add(Integer.parseInt(data.get(i)[9]));
                    loyer.add(Integer.parseInt(data.get(i)[10]));
                    P = new ProprieteAConstruire(loyer, Integer.parseInt(data.get(i)[4]), Integer.parseInt(data.get(i)[1]), g, data.get(i)[2], this);
                    g.addPropriete(P);
                    this.carreaux.put(i + 1, P);
                } else if (caseType.compareTo("G") == 0) {
                    Gare G = new Gare(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i + 1, G);
                } else if (caseType.compareTo("C") == 0) {
                    Compagnie C = new Compagnie(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i + 1, C);
                } else if (caseType.compareTo("CT") == 0) {
                    CarreauTirage CT = new CarreauTirage(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i + 1, CT);
                } else if (caseType.compareTo("CA") == 0) {
                    CarreauArgent CA = new CarreauArgent(Integer.parseInt(data.get(i)[3]), Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i + 1, CA);
                } else if (caseType.compareTo("CM") == 0) {
                    CarreauMouvement CM = new CarreauMouvement(Integer.parseInt(data.get(i)[1]), data.get(i)[2], this);
                    this.carreaux.put(i + 1, CM);
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    private void menuUI() {
        interface_3.menu();
    }

    public void créerJoueur() {
        interface_3.afficherDemandeNom();
        String nom = interface_3.lire();
        Joueur J = new Joueur(nom, this);
        this.joueurs.put(J.getNomJoueur(), J);
    }

    public boolean supprimerJoueur() {
        interface_3.afficherDemandeNom();
        String nom = interface_3.lire();
        this.joueurs.remove(nom);
        return true;
    }

    public void débuterPartie() {

        Hashtable<String, Integer> jeuDé = new Hashtable<>();
        for (String nomJoueur : joueurs.keySet()) {
            jeuDé.put(nomJoueur, this.lancerDé());
        }

        for (int i = 0; i < joueurs.size(); i++) {

            Object[] max = new Object[2];
            max[0] = "";
            max[1] = (int) 0;

            for (String nomJoueur : jeuDé.keySet()) {
                if (jeuDé.get(nomJoueur) >= (int) max[1]) {
                    max[0] = nomJoueur;
                    max[1] = jeuDé.get(nomJoueur);
                }
            }
            this.ordreJoueur.put(i+1, (String) max[0]);
            jeuDé.remove((String) max[0]);
            this.interface_3.afficherOrdrePassageJoueur(max, i+1);
        }
        this.jouerPartie();
    }

    private void jouerPartie() {

        while (this.ordreJoueur.size() >= 2) {
            interface_3.afficherln("");
            for (int i = 1; i <= this.ordreJoueur.size(); i++) {
                
                int nbLancerDés = 1;
                String nomJoueur = this.ordreJoueur.get(i);
                Joueur j = this.joueurs.get(nomJoueur);
                this.interface_3.afficherNomJoueur(j);
                boolean aFaitUnDouble = this.jouerCoup(j, nbLancerDés);
                while (aFaitUnDouble && nbLancerDés < 3) {
                    aFaitUnDouble = this.jouerCoup(j, nbLancerDés);
                    nbLancerDés++;
                }
                interface_3.afficherln("");
                if (j.getCash() <= 0) {
                    j.joueurMeurt(i);
                }
            }
        }
    }

    public int getDeplacement() {
        return deplacement;
    }

    private boolean jouerCoup(Joueur j, int nbLancerDés) {                  //j s'échappe de prison grâce à la carte
        if (j.estEnPrison() && j.getNbCarteEchapper() > 0) {
            this.interface_3.afficherCarteSortiePrison();
            if (this.interface_3.lireRéponse()) {
                j.retirerCarteEchapper();
                j.sortirPrison();
            }
        }
        dé1 = this.lancerDé();
        dé2 = this.lancerDé();
        deplacement = dé1 + dé2;
        if (!j.estEnPrison()) {                                             // j n'est pas en prison
            if(j.getPositionJoueur()>(j.getPositionJoueur()+deplacement-1)%40 && j.getPositionJoueur()!=1){
                interface_3.afficerPassageCaseDépart();
                j.setCash(j.getCash()+200);
            }
            j.deplacement(j.getPositionJoueur() + deplacement);
            interface_3.afficherDéplacement(j,dé1,dé2);
            Carreau c = this.carreaux.get(j.getPositionJoueur());
            c.action(j);
            return dé1 == dé2;
        }else if(j.estEnPrison() && dé1==dé2){                              // j est en prison et a fait un double
            interface_3.afficherSortiePrison(j);
            j.deplacement(j.getPositionJoueur() + deplacement);
            interface_3.afficherDéplacement(j, dé1,dé2);
            Carreau c = this.carreaux.get(j.getPositionJoueur());
            c.action(j);
            return dé1 == dé2;
        } else if (j.getPrison() == 3) {                                     // j est en prison depuis 3 tours
            j.retirerCash(50);

            if (j.getCash() > 0) {
                j.sortirPrison();
                j.deplacement(j.getPositionJoueur()+deplacement);
            }
            return dé1==dé2;
        } else if (!j.estEnPrison() && dé1 == dé2 && nbLancerDés == 3) {    // j a fait 3 double

            j.metrreEnPrison();
            return false;
        } else {                                                            // j est en prison est ne  c'est pas échapper
            j.tourPrison();
            return false;
        }
    }

    private int lancerDé() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    void retirerJoueur(Joueur j, int i) {
        this.joueurs.remove(j.getNomJoueur());
        this.ordreJoueur.remove(i);
    }
}