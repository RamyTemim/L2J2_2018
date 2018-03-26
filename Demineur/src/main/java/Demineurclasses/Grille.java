package Demineurclasses;
import Demineurclasses.Case;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;


public class Grille {
public static int NbLigneFacile = 8 ; //Nombre de ligne pour le niveau Facile
public static int NbColonneFacile = 8 ; //Nombre de colonne pour le niveau Facile
public static int NbMineFacile = 10; //Nombre de Mine pour le niveau Facile
public static int NbLigneMoyen = 16; //Nombre de ligne pour le niveau Moyen
public static int NbColonneMoyen = 16;//Nombre de colonne pour le niveau Moyen
public static int NbMineMoyen = 40; //Nombre de Mine pour le niveau Moyen
public static int NbLigneDifficile = 16; //Nombre de ligne pour le niveau Difficile
public static int NbColonneDifficile = 30;//Nombre de colonne pour le niveau Difficile
public static int NbMineDifficile = 99 ; //Nombre de Mine pour le niveau Difficile
public static int nbMaxLigne = 16;
public static int nbMaxColonne = 30;
public static int nbMaxMine = 99;

public Case[][] grille ;
private int NbLigne;
private int NbColonne;
private int NbMine;
private int nbDrapeau=0;


public Grille(){

    this.NbLigne = NbLigneFacile;
    this.NbColonne = NbColonneFacile;
    this.NbMine = NbMineFacile;
    this.grille = new Case[NbLigne][NbColonne];
}
//constructeur avec paramètres
    public Grille(int nbLigne, int nbColonne, int nbMine) {

            this.NbLigne = nbLigne;
            this.NbColonne = nbColonne;
            this.NbMine = nbMine;
        }


    //méthode qui permet de remplir la grille de case en plaçant aléatoirement les mines
    public void initialiser() {
Random r = new Random();
Vector<Point> indexMines = new Vector<Point>();
int x,y;
//tirage aléatoire sans remine de Nbmine qui seront les index des cases minées
for (int i=0 ; i<NbMine; i++){
Point pt;
do {
    pt = new Point(r.nextInt(NbLigne),r.nextInt(NbColonne));
   }while (indexMines.indexOf(pt)!=-1);
indexMines.addElement(pt);
}

    //initialisation des cases
    for(int i =0; i< NbLigne;i++){
    for(int j =0 ; j<NbColonne ; j++){
        if(indexMines.indexOf(new Point(i,j))!=-1)//si indice est dans indiceMine
        {
            grille[i][j] = new Case(true); //ajout d'une case minée
        }else {
            grille[i][j]= new Case(false);//ajout d'une case autre
        }
    }
    }this.set_nbMinesAProximite();
}
public void set_nbMinesAProximite () {
            for (int i = 0; i < NbLigne; i++) {
                for (int j = 0; j < NbColonne; j++) {
                    if (!grille[i][j].get_mine())
                        grille[i][j].set_NbMineProximite(NbMine);
                }
            }
        }
/*méthode retournant le nombre de mines à proximité de la case dont les coordonnées sont passées en paramètre
x indice de la ligne de la case
y indice de la colonne de la case
retourne le nombre de mine à proximité */

        private int nbMinesProxCase ( int x, int y){
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i >= 0 && i < NbLigne && j >= 0 && j < NbColonne) {
                        if (grille[i][j].get_mine()) NbMine++;
                    }
                }
            }return NbMine;
        }
        public void decouvrirCase ( int x, int y){

            grille[x][y].decouvrir();
            }
            public Case get_Case(int x,int y){
            return grille[x][y];
            }

public void decouvrirAutour(int x , int y){
            if(grille[x][y].get_NbMineProximite()==0){
                for(int i=-1;i<=1;i++){ for (int j=-1;j<=1;j++){
                    if ((x + i) >= 0 && (x + i) < NbLigne &&(y + j) >= 0 && (y + j) < NbColonne ){
                        if (grille[x + i][y + i].get_NbMineProximite() == 0 &&
                                !grille[x + i][y + j].get_decouvert() &&
                                !grille[x + i][y + j].get_mine()
                                ) {
                            decouvrirCase(x + i, y + j);
                            decouvrirAutour(x + i, y + j);
                        } else if (!grille[x + i][y + j].get_mine() && !grille[x + i][y + j].get_decouvert()) {
                            decouvrirCase(x + i, y + j);
                        }
                    }

                    }
                }}}
//méthode affichage grille
                public String toString(){
                    String grille = "";
                    for (int i=0 ; i<NbLigne ; i++){
                        for (int j=0; j<NbColonne; j++){
                            grille += "" + this.grille[i][j]+ "\t";

                        }
                    }
                    return grille;
                }}





