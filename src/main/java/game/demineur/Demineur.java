package game.demineur;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Random;
import java.lang.String;

public class Demineur {

    private int mLargeur ; //Largeur de la grille
    private int mHauteur = 0; //Hauteur de la grille
    private int mMines = 0; //Nombre de mines de la grille
    private ArrayList <String> generer;
    private ArrayList <Integer> list;
    private ArrayList<Integer> checkIndex;
    private ArrayList<Integer> indexChecked;
    private boolean addMore;




    @JsonProperty
    private String[][] grille; //Grille qui contient les différents éléments

    //Constructeur qui génére la grille selon le niveau de difficulté

    public Demineur( int niveau) {
        //Cas du niveau 1
        mLargeur = 0;
        if (niveau ==1) {
            this.mHauteur = 8;
            this.mLargeur = 8;
            this.mMines =10;
            //Cas du niveau 2
        }else if (niveau == 2){
            this.mHauteur =16;
            this.mLargeur=16;
            this.mMines =40;
            //Cas du niveau 3
        }else if(niveau ==3){
            this.mHauteur =30;
            this.mLargeur=16;
            this.mMines =99;
        }

        this.generer = new ArrayList <> ();
        this.grille = new String[mHauteur][mLargeur];

        this.checkIndex = new ArrayList <> ();
        this.indexChecked = new ArrayList <> ();
        this.list = new ArrayList <> ();
        this.addMore = true;


    }








    private void placeMines() {
        int minesPlaced = 0; //Nombre de mines placées
        Random random = new Random(); //generer nombre aléatoirement
        while (minesPlaced < this.mMines) {
            int x = random.nextInt(this.mLargeur); //genere et renvoie l'entier aléatoirement entre 0 et mLargeur
            int y = random.nextInt(this.mHauteur);////genere et renvoie l'entier aléatoirement entre 0 et mHauteur

            if (!this.grille[y][x].equals( "*" )) {
                this.grille[y][x] = "*";
                minesPlaced++; //incrémentation du nombre de mines

            }
        }
    }



    //Methode qui genere les chiffres selon le nombre de mines qu'il y a à proximité
    private void calculAstuce() {
        for (int y = 0; y <mHauteur; y++)
            for (int x = 0; x < mLargeur; x++) {
                if (!grille[y][x].equals( "*" )) {
                    grille[y][x] = minesProximite(y, x);
                }
            }
    }

    private String minesProximite(int y, int x) {
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
    private int mineAt(int y, int x) {

        if (y >= 0 && y < mHauteur && x >= 0 && x < mLargeur && grille[y][x].equals( "*" )) {
            return 1;
        } else {
            return 0;
        }
    }








    private void initGrid() {

        for(int i = 0; i < this.mHauteur; i++){
            for(int j = 0; j < this.mLargeur; j++){
                this.grille[i][j] = " ";
            }
        }

    }



    public String genererGrilleArray() {
        initGrid();
        placeMines(); //Méthode qui place les mines dans la grille
        calculAstuce(); //Methode qui genere les chiffres selon le nombre de mines qu'il y a à proximité


        for (int y = 0; y < this.mHauteur; y++) {
            for (int x = 0; x < this.mLargeur; x++) {
                this.generer.add(this.grille[y][x]);
            }
        }

        return this.generer.toString();
    }


    public void setAddMore(boolean addMore) {
        this.addMore = addMore;
    }




    private void addIndex(int i) {


        int index = i;
        int index1 = -1, index2 = -1, index3 = -1, index4 = -1, index5 = -1, index6 = -1, index7 = -1, index8 = -1;
        int size = (this.mLargeur) * (this.mHauteur);

        if (!(this.list.contains( i )) ) {
            this.list.add( i );
            this.addMore = true;
        }

        while (this.addMore)  {

            this.addMore = false;

            if (i - (this.mLargeur + 1) >= 0 && (i%this.mLargeur) != 0) {
                index1 = i - (this.mLargeur + 1);

                String element = this.generer.get( index1 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index1 ))) {
                            this.list.add( index1 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index1 ))) {
                            this.checkIndex.add( index1 );
                        }

                        index = index1;
                        break;
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index1 ))) {
                            this.list.add( index1 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }

            }


            if (i - this.mLargeur >= 0 ) {
                index2 = i - this.mLargeur;

                String element = this.generer.get( index2 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index2 ))) {
                            this.list.add( index2 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index2 ))) {
                            this.checkIndex.add( index2 );
                        }

                        if(index != i ) index = index2; break;

                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index2 ))) {
                            this.list.add( index2 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }


            }


            if (i - this.mLargeur + 1 >= 0 && ((i + 1) % this.mLargeur) != 0) {
                index3 = i - this.mLargeur + 1;

                String element = this.generer.get( index3 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index3 ))) {
                            this.list.add( index3 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index3 ))) {
                            this.checkIndex.add( index3 );
                        }

                        if(index != i ) index = index3; break;

                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index3 ))) {
                            this.list.add( index3 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }

            }


            if (i + 1 < size && ((i + 1)%this.mLargeur) != 0) {
                index4 = i + 1;

                String element = this.generer.get( index4 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index4 ))) {
                            this.list.add( index4 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index4 ))) {
                            this.checkIndex.add( index4 );
                        }

                        if(index != i ) index = index4; break;


                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index4 ))) {
                            this.list.add( index4 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }

            }


            if (i + (this.mLargeur + 1) < size && ((i + 1)%this.mLargeur) != 0) {
                index7 = i + (this.mLargeur + 1);

                String element = this.generer.get( index7 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index7 ))) {
                            this.list.add( index7 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index7 ))) {
                            this.checkIndex.add( index7 );
                        }

                        if(index != i ) index = index7; break;

                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":

                        if (!(this.list.contains( index7 ))) {
                            this.list.add( index7 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }

            }

            if (i + this.mLargeur < size) {
                index6 = i + this.mLargeur;

                String element = this.generer.get( index6 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index6 ))) {
                            this.list.add( index6 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index6 ))) {
                            this.checkIndex.add( index6 );
                        }

                        if(index != i ) index = index6; break;

                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index6 ))) {
                            this.list.add( index6 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }

            }

            if (i + (this.mLargeur - 1) < size && (i%this.mLargeur) != 0) {
                index5 = i + (this.mLargeur - 1);

                String element = this.generer.get( index5 );
                switch (element) {
                    case " ":
                        if (!(this.list.contains( index5 ))) {
                            this.list.add( index5 );
                            this.addMore = true;
                        }

                        if( !(this.indexChecked.contains( index5 ))) {
                            this.checkIndex.add( index5 );
                        }

                        if(index != i ) index = index5; break;

                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index5 ))) {
                            this.list.add( index5 );
                            this.addMore = true;
                        }
                        break;
                    default:
                        break;
                }

            }


            if (i - 1 >= 0 && (i%this.mLargeur) != 0) {
                index8 = i - 1;

                String element = generer.get( index8 );
                switch (element) {
                    case " ":
                        if (!(list.contains( index8 ))) {
                            list.add( index8 );
                            addMore = true;
                        }

                        if( !(this.indexChecked.contains( index8 ))) {
                            this.checkIndex.add( index8 );
                        }

                        if(index != i ) index = index8; break;

                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                        if (!(this.list.contains( index8 ))) {
                            this.list.add( index8 );
                            this.addMore = true;
                        } break;

                    default: break;

                }
            }


            if (index == index1 || index == index2 || index == index3 || index == index4 || index == index5 || index == index6 || index == index7 || index == index8) {
                this.addIndex( index );
            }

        }

        while(!(this.checkIndex.isEmpty())) {
            for (int j = 0; j < this.checkIndex.size(); j++) {
                int cell = this.checkIndex.get( j );
                this.checkIndex.remove( j );
                this.indexChecked.add( cell );
                this.addIndex( cell );
            }
        }
    }




    private String returnList(){
        return this.list.toString();
    }


    public void removeList(){
        while(!(this.list.isEmpty())){
            this.list.remove( this.list.size() - 1 );
        }
    }


    private void removeCheckIndex(){
        while(!(this.checkIndex.isEmpty())){
            this.checkIndex.remove( this.checkIndex.size() - 1 );
        }
    }

    private void removeIndexChecked(){
        while(!(this.indexChecked.isEmpty())){
            this.indexChecked.remove( this.indexChecked.size() - 1 );
        }
    }



    public String devoile(int i){

        this.addMore = true;
        this.removeCheckIndex();
        this.removeList();
        String result = "";
        String element = this.generer.get( i );

    switch (element){
        case "*" : result += "[" + i + "]"; break;
        case "1": case "2": case "3": case "4": case "5": result += "[" + i + "]" ; break;
        default : this.removeIndexChecked(); this.removeList(); this.addIndex( i ); result += this.returnList(); break;
    }

        return result;
    }





















}