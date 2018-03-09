package sudoku;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


//@RequiredArgsConstructor
@Data
public class GrilleSudoku {

    private ArrayList <Integer> grille;
    private ArrayList <Integer> listCompare;



    public GrilleSudoku () {
       grille = new ArrayList <> ();
       listCompare = new ArrayList <> ();
     }

    /****************** Vérifications ***********************/

    public void setGrille ( int [] e){
        for(int i=0 ; i < 81 ; i++) {

            if (grille.get(i) != 0) continue;
            grille.add(e[i]);
            System.out.println(grille.get(i));

        }

    }


    public void ini () {

        for(int i = 0 ; i < 81 ; i++) {
            grille.add( i , 0 );
        }

        /*for(int j = 0 ; j< 9 ; j++) {
            listCompare.add( j , 0);
        }*/
    }



    //Check if already on line
    public boolean isExistLine(int i, int num) {

        boolean res;
        int k = i - (i % 9);
        int z = 0;



    while( z < listCompare.size()) {
        int length = k + 9;
        for (int m = k; m < length; m++) {

            listCompare.add( z, (grille.get( m )) );
            z++;
        }

    }
        res = listCompare.contains( num );

        int s = 0;
        while (! (listCompare.isEmpty())) listCompare.remove( s );

        return res;
    }




    //Check if already on column

    public boolean isExistColumn(int i, int nb) {

        boolean res = true;

        int l = i % 27;
        int z = 0;

        while( z < listCompare.size()) {

            for (int k = i; k >= 27 && k % 27 != 0; k -= 9) {

                listCompare.add( z , ( grille.get(k) ) );
                z++;
            }
        }

        res = listCompare.contains( nb );

        int s = 0;
        while (! (listCompare.isEmpty())) listCompare.remove( s );


        return res;

    }




    //Check if already in bloc

    public boolean isExistBloc(int i, int nb) {

        boolean res = true;
        int k = i - i % 27;
        int l = i - i % 3;
        int z=0;

        while( z < listCompare.size()) {
            for (int m = k; m < (k + 27); m += 9) {

                for (int n = l; n < (l + 3); n++) {

                    listCompare.add( z , grille.get( n ) );
                    z++;

                }
            }
        }

        res = listCompare.contains( nb );

        int s = 0;
        while (! (listCompare.isEmpty())) listCompare.remove( s );

        return res;

    }




    //Check that number can be placed on line, column and bloc

    public boolean checkNumber(int i, int nb) {

        boolean res = (isExistLine( i, nb ) || isExistColumn( i, nb ) || isExistBloc( i, nb ));

        return res;

    }


    /****************** Creation & Affichage ***********************/


    //Generates a random number between 1 and 9

    public int generateNb() {

        return (int) (Math.random() * 9) + 1;

    }




    //Display Sudoku puzzle

    public void displayGrid() {

        int i = 0;

        while (i < 27) {

            System.out.println( grille.get( i ) + " " + grille.get( i + 1 ) + " " + grille.get( i + 2 ) + "   " + grille.get( i + 3 ) + " " + grille.get( i + 4 ) + " " + grille.get( i + 5 ) + "   " + grille.get( i + 6 ) + " " + grille.get( i + 7 ) + " " + grille.get( i + 8 ) );

            i += 9;
        }

        System.out.println( " " );

        while (i < 54) {

            System.out.println( grille.get( i ) + " " + grille.get( i + 1 ) + " " + grille.get( i + 2 ) + "   " + grille.get( i + 3 ) + " " + grille.get( i + 4 ) + " " + grille.get( i + 5 ) + "   " + grille.get( i + 6 ) + " " + grille.get( i + 7 ) + " " + grille.get( i + 8 ) );

            i += 9;

        }

        System.out.println( " " );

        while (i < 81) {

            System.out.println( grille.get( i ) + " " + grille.get( i + 1 ) + " " + grille.get( i + 2 ) + "   " + grille.get( i + 3 ) + " " + grille.get( i + 4 ) + " " + grille.get( i + 5 ) + "   " + grille.get( i + 6 ) + " " + grille.get( i + 7 ) + " " + grille.get( i + 8 ) );

            i += 9;

        }

    }




    //Generates a Sudoku puzzle

    public void generateGrid() {

        int nombre;
        int i = 0;

        while (i <= 80) {

            nombre = generateNb();

            if (!isPossible(i)) {

                removeGridFrom( i, nombre );

            }


            while (!(checkNumber( i, nombre ))) {

                nombre = generateNb();

            }

            grille.add( i , nombre );
            System.out.println(i+" "+grille.get( i ));
            i++;

        }

    }




    //Check if it possible to placed any number between 1 and 9

    public boolean isPossible(int i) {

        boolean res = false;

        for (int num = 1; num <= 9; num++) {

            if ((checkNumber( i, num ))) {

                res = true;

            }

        }

        return res;

    }




    //Remove element at index i, while nb cannot be placed

    public void removeGridFrom(int i, int nb) {

        while ((!(checkNumber( i, nb ))) && i>0) {

            grille.remove( i );

        }
            grille.add(i , nb);

    }




    //Generates an element which can be placed

    public int generateCase(int i) {

        int num = generateNb();

        while ((!(checkNumber( i, num )))) {

            num = generateNb();

        }

        return num;
    }




}
