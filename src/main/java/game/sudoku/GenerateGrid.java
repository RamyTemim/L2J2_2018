package game.sudoku;

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
     * Method which generates a game puzzle conforms
     *
     * @return listForReturn an Arraylist which contains all element of the generate game puzzle
     *
     */
    public ArrayList <Integer> generateGrid() {

        ArrayList <Integer> al = new ArrayList<>(  );
        int nb;


        //Generate a grid which contains 0 in case of impossible situation
        while ( this.ms.sizeGrid() < 81 ) {
            this.ms.generateCase( );

            al.add(this.ms.getGrid( this.ms.sizeGrid() - 1 ));

            if(al.size() == 9) {
                al.clear();
            }
        }


        //Check if grid contains 0
        ms.checkGrid();


        //Remove grid while contains 0
        while(this.ms.containsElement(0)) {
            this.ms.removeElement(this.ms.sizeGrid() -1);
        }


        // Generate element -> remove while it's not possible to add him
        nb = this.ms.generateNb();
            do {
                this.ms.removeElement( (this.ms.sizeGrid() - 1) );
            } while (!(this.ms.checkNumber( (this.ms.sizeGrid() - 1), nb )));



        //Generate case of grid while it's not complete, and add 0 when it's impossible
        while ( this.ms.sizeGrid() < 81 ) {
            this.ms.generateCase( );
        }



        // If grid is correct and complete : display it ; else recursive call of method generateGrid();
        if(ms.checkGrid() && (ms.sizeGrid() == 81)) {
            for(int i=0 ; i < this.ms.sizeGrid() ; i++) {
                this.listForReturn.add( i , this.ms.getGrid( i ));
            }
        } else this.generateGrid( );


        return this.listForReturn;
    }





    public int getI(int i ){
        return this.listForReturn.get( i );
    }




    /**
     * Method which return generate grid as a String
     *      -> to display the game grid
     *
     * @return String
     */
    public String displayGenerateGrid () {
        return ms.displayGrid();
    }


}
