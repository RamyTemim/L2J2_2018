package sudoku;



import java.util.ArrayList;


public class GenerateGrid {

    private MethodSudoku ms;

    private ArrayList <Integer> listForReturn;





    // Constructor
    public GenerateGrid (){

        this.ms = new MethodSudoku();
        this.listForReturn = new ArrayList<>(  );

    }



    /**
     *
     * Method which generates a sudoku puzzle conforms
     *
     *
     *
     *
     * @param e an object of MethodSudoku
     *          to create and check element of grid
     *
     *
     * @return listForReturn an Arraylist which contains all element of the generate sudoku puzzle
     *
     */
    public ArrayList <Integer> generateGrid( MethodSudoku e ) {

        this.ms = e;

        ArrayList <Integer> al = new ArrayList<>(  );
        ArrayList <Integer> lr = new ArrayList<>(  );

        int nb;


        //Generate a grid which contains 0 in case of impossible situation
        while ( ms.sizeGrid() < 81 ) {

            ms.generateCase( );

            al.add(ms.getGrid( ms.sizeGrid() - 1 ));

            if(al.size() == 9) {
                al.clear();
            }

        }



        //Check if grid contains 0
        ms.checkGrid();



        //Remove grid while contains 0
        while(ms.containsElement(0)) {

            ms.removeElement(ms.sizeGrid() -1);

        }


        ms.removeElement(ms.sizeGrid() -1);



        // Add element if it's possible, else remove while it's not possible to add him
        lr.add(ms.getGrid( (ms.sizeGrid() - 1 ) ));

        nb = ms.generateNb();

        if(ms.isPossible( ms.getGrid( (ms.sizeGrid() - 1 ) ) )) {

            while (lr.contains( nb ) && !(ms.checkNumber( (ms.sizeGrid() - 1), nb ))) {
                nb = ms.generateNb();
            }

            ms.addGrid( nb );

        } else

            do {

            ms.removeElement( (ms.sizeGrid() - 1 ) );

            } while (!(ms.checkNumber( (ms.sizeGrid() - 1), nb )));


        lr.clear();



        //Genrerate case of grid while it's not complete, and add 0 when it's impossible
        while ( ms.sizeGrid() < 81 ) {

            ms.generateCase( );

        }



        // If grid is correct and complete : display it ; else recursive call of method generateGrid();
        if(ms.checkGrid() && (ms.sizeGrid() == 81)) {

            System.out.println( "          Final version of Sudoku puzle :)))        " );
            ms.displayGrid();


        } else

            this.generateGrid( ms );




        for(int i=0 ; i < ms.sizeGrid() ; i++) {

            listForReturn.add( i , ms.getGrid( i ));

        }


        return listForReturn;

    }







    public Integer getListForReturn(int i) {

        return listForReturn.get(i);
    }




    public String displayGenerateGrid () {

        return ms.displayGrid();

    }




}


























