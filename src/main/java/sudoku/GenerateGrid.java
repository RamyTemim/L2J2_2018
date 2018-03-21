package sudoku;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class GenerateGrid {



    public void generateGrid(MethodSudoku e) {

        ArrayList <Integer> listForRemove = new ArrayList<>(  );
        MethodSudoku ms;
        ms = e;
        int nb;



        while ( ms.sizeGrid() < 81 ) {


            ms.generateCase( );

            System.out.println( "numI " + (ms.sizeGrid() -1) + " :" + ms.getGrid(ms.sizeGrid() -1) );


        }


        ms.displayGrid();

        System.out.println("checking result : ");
        ms.checkGrid();

        ms.displayGrid();

        while(ms.containsElement(0)) {

            ms.removeElement(ms.sizeGrid() -1);

        }



        listForRemove.add(ms.getGrid( (ms.sizeGrid() - 1 ) ));

        nb = ms.generateNb();

        if(ms.isPossible( ms.getGrid( (ms.sizeGrid() - 1 ) ) )) {
            while (listForRemove.contains( nb ) && !(ms.checkNumber( (ms.sizeGrid() - 1), nb ))) {
                nb = ms.generateNb();
            }
        }
        else
            do {

            ms.removeElement( (ms.sizeGrid() - 1 ) );

            } while (!(ms.checkNumber( (ms.sizeGrid() - 1), nb )));



        ms.addGrid( nb );
        listForRemove.clear();





        while ( ms.sizeGrid() < 81 ) {


            ms.generateCase( );

            System.out.println( "numI " + (ms.sizeGrid() -1) + " :" + ms.getGrid(ms.sizeGrid() -1) );


        }




        while (!(ms.checkGrid()) && (ms.sizeGrid() < 81)) {

            this.generateGrid( ms );

        }


        if(ms.checkGrid() && (ms.sizeGrid() == 81)) {
            System.out.println( "          Final version of Sudoku puzle :)))        " );
            ms.displayGrid();
        }

        else this.generateGrid( ms );

















    }


}











/*

    public void generateGrid(MethodSudoku e) {

        ArrayList <Integer> listForRemove = new ArrayList<>(  );
        MethodSudoku ms;
        ms = e;
        int nb;



        while ( ms.sizeGrid() < 81 ) {


            ms.generateCase( );

            if (ms.getGrid((ms.sizeGrid() - 1 )) == 0) {

                ms.removeElement( (ms.sizeGrid() - 1) );
                listForRemove.add(ms.getGrid( (ms.sizeGrid() - 1 ) ));

                nb = ms.generateNb();

                while(listForRemove.contains( nb ) && !(ms.checkNumber( (ms.sizeGrid() - 1 ) , nb ))){
                    nb = ms.generateNb();
                }

                ms.addGrid( nb );
                listForRemove.clear();

            }


            System.out.println( "numI " + (ms.sizeGrid() -1) + " :" + ms.getGrid(ms.sizeGrid() -1) );


        }

        ms.displayGrid();


        System.out.println("checking result : ");
        ms.checkGrid();

        ms.displayGrid();

        while(ms.containsElement(0)) {

            ms.removeElement(ms.sizeGrid() -1);

        }

        if (!(ms.checkGrid())) {

            this.generateGrid( ms );

        }

        System.out.println("          Final version of Sudoku puzle :)))        ");
        ms.displayGrid();



















    }

*/

























            /*ms.addGrid( 0 );
            if(ms.sizeGrid() > 0) index = ms.sizeGrid() - 1;

            while (ms.getGrid(index) == nb ) {
                 ms.generateCase();
                //i = index;

                if (ms.sizeGrid() - 1 == index) break;
            }


            while (ms.getGrid( index ) == 0 || !(ms.isPossible( index ))) {

                nb = ms.getGrid( index );
                ms.removeElement( index );
                index--;

            }
*/