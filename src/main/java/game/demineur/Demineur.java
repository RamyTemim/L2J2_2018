package game.demineur;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;
import java.lang.String;

public class Demineur {

    private int mLargeur ; //Largeur de la grille
    private int mHauteur = 0; //Hauteur de la grille
    private int mMines = 0; //Nombre de mines de la grille
    @JsonProperty
    private String[][] grille; //Grille qui contient les différents éléments

    //Constructeur qui génére la grille selon le niveau de difficulté

    public Demineur( int niveau) {
        //Cas du niveau 1
        mLargeur = 0;
        if (niveau ==1) {
            mHauteur = 8;
            mLargeur = 8;
            mMines =10;
            //Cas du niveau 2
        }else if (niveau == 2){
            mHauteur =16;
            mLargeur=16;
            mMines =40;
            //Cas du niveau 3
        }else if(niveau ==3){
            mHauteur =30;
            mLargeur=16;
            mMines =99;
        }
    }
    @JsonProperty
    public String[][] affichage(){
        grille = new String[mHauteur][mLargeur];
        effacerGrille(); //Méthode qui réinitialise la grille
        placeMines(); //Méthode qui place les mines dans la grille
        genererGrille(); //Méthode qui génére la grille
        calculAstuce(); //Methode qui genere les chiffres selon le nombre de mines qu'il y a à proximité

        return grille;
    }

    public String placeMines() {
        int minesPlaced = 0; //Nombre de mines placées
        String mines = ""; //Initialisation du string
        Random random = new Random(); //generer nombre aléatoirement
        while (minesPlaced < mMines) {
            int x = random.nextInt(mLargeur); //genere et renvoie l'entier aléatoirement entre 0 et mLargeur
            int y = random.nextInt(mHauteur);////genere et renvoie l'entier aléatoirement entre 0 et mHauteur

            if (grille[y][x] != "*") {
                grille[y][x] = "*";
                minesPlaced++; //incrémentation du nombre de mines

                mines += grille[y][x]; //remplit la grille de mines
            }
        }
        return mines; // retourne les mines
    }
    public String effacerGrille() {

        String effacer ="";
        for (int y = 0; y < mHauteur; y++) {
            for (int x = 0; x < mLargeur; x++) {
                grille[y][x] = " "; //affecte un vide
                effacer += grille[y][x];
            }
        }
        return  effacer;
    }
    public String genererGrille() {
        String generer ="";
        for (int y = 0; y < mHauteur; y++) {
            for (int x = 0; x < mLargeur; x++) {
                System.out.print(grille[y][x]);
                generer += grille[y][x];
            }
            System.out.print("\n");
        }
        return  generer;
    }

    //Methode qui genere les chiffres selon le nombre de mines qu'il y a à proximité
    public String calculAstuce() {
        String astuce ="";
        for (int y = 0; y <mHauteur; y++)
            for (int x = 0; x < mLargeur; x++) {
                if (grille[y][x] != "*") {
                    grille[y][x] = minesProximite(y, x);
                    astuce += grille [y][x];
                }
            }
        return  astuce;
    }

    public String minesProximite(int y, int x) {
        int mines = 0;

        mines += mineAt(y - 1, x - 1);  //  En haut à gauche de la case concernée avec les indices x et y de la case
        mines += mineAt(y - 1, x);      // En haut de la case concernée avec les indices x et y de la case
        mines += mineAt(y - 1, x + 1);  // En haut à droite de la case concernée avec les indices x et y de la case
        mines += mineAt(y, x - 1);      //En bas de la case concernée avec les indices x et y de la case
        mines += mineAt(y, x + 1);      // A droite de la case concernée avec les indices x et y de la case
        mines += mineAt(y + 1, x - 1);  // En bas à gauche de la case concernée avec les indices x et y de la case
        mines += mineAt(y + 1, x);      // En bas de la case concernée avec les indices x et y de la case
        mines += mineAt(y + 1, x + 1);  // En bas à droite de la case concernée avec les indices x et y de la case
        if (mines > 0) {
            return  String.valueOf(mines); //Convertit l'entier mines en String
        } else {
            return " ";
        }
    }

    //donne l'élement correspondant à l'indice x (ligne) y(ligne) de la case
//retourne 1 si il trouve la mine , 0 sinon
    public int mineAt(int y, int x) {

        if (y >= 0 && y < mHauteur && x >= 0 && x < mLargeur && grille[y][x] == "*") {
            return 1;
        } else {
            return 0;
        }
    }
}