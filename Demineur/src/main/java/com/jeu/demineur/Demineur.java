package com.jeu.demineur;

import com.fasterxml.jackson.databind.Module;



public class Demineur {
    private int NiveauFacile = 1;
    private int NiveauMoyen = 2;
    private int NiveauDifficile = 3;
    private int NombreCaseFacile;
    private int NombreCaseMoyen;
    private int NombreCaseDifficile;

    public Grille grille;
    public Partie partie;
public Demineur(int nbLigne, int nbColonne, int nbMine) {
    grille = new Grille(nbLigne, nbColonne,nbMine);
    grille.initialiser();
   partie = new Partie(this);
}
public Demineur(int niveau){
    this.grille = new Grille();
    this.grille.initialiser();
    this.partie = new Partie(this);
    this.partie.setNiveau(NiveauFacile);
}
public Demineur(){
    this.grille = new Grille();
    this.grille.initialiser();
    this.partie = new Partie(this);
}


    protected Grille setupGrille(int niveau){
        int nbLignes,nbColonnes,nbMines;
        switch(niveau){
            case 2:
                partie.setNiveau(NiveauMoyen);
                nbLignes = Grille.NbLigneMoyen;
                nbColonnes = Grille.NbColonneMoyen;
                nbMines = Grille.NbMineMoyen;
                break;
            case 3:
                partie.setNiveau(NiveauDifficile);
                nbLignes = Grille.NbLigneDifficile;
                nbColonnes = Grille.NbColonneDifficile;
                nbMines = Grille.NbMineDifficile;
                break;
            default:
                partie.setNiveau(NiveauFacile);
                nbLignes = Grille.NbLigneFacile;
                nbColonnes = Grille.NbColonneFacile;
                nbMines = Grille.NbMineFacile;
                break;
        }
        return new Grille(nbLignes,nbColonnes,nbMines);
    }




public Grille get_Grille(){

    return grille;
}
public Partie get_Partie(){
    return partie;
}
public void initialiser(){
    grille.initialiser();
    partie= new Partie(this);
}
}

