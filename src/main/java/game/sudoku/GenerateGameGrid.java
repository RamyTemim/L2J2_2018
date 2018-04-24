package game.sudoku;

import java.util.ArrayList;


public class GenerateGameGrid {

    private ArrayList <Integer> clone;
    private ArrayList<Integer> grid;
    private ArrayList<Integer> solution;


    // Constructor

    public GenerateGameGrid(GenerateGrid e ) {
        this.clone = e.generateGrid();
        this.grid = new ArrayList<>(  );
        this.solution = new ArrayList<>(  );
    }



    /**
     * Initialize game grid
     * If not empty -> remove grid and initialize
     */
    private void setGG() {

        while(!(this.grid.isEmpty())) {
            this.grid.remove( grid.size() - 1 );
        }

        for(int i = 0 ; i < 81 ; i++){
            this.grid.add(this.clone.get( i ));
        }
    }



    /**
     * Private method which create a clone of e.generateGrid() in an Arraylist
     */
    public ArrayList<Integer> getClone() {

        while(!(this.solution.isEmpty())) {
            this.solution.remove( this.solution.size() - 1 );
        }
        for(int i = 0 ; i < 81 ; i++){
            this.solution.add(this.clone.get( i ));
        }

        return solution;
    }



    /**
     * Hide case of cloned grid
     *
     * void method which set the private ArrayList "clone"
     */
    public ArrayList<Integer> hideCase(int level) {
        ArrayList <Integer> listOfIndex = new ArrayList<>( );
        int nb;

        switch (level) {
            case 1 : this.setGG(); nb = 30; break;
            case 2 : this.setGG(); nb = 40; break;
            case 3 : this.setGG(); nb = 50; break;
            default : this.setGG(); nb = 40; break;
        }

        for(int i = 0; i < nb ; i++) {

            int index = (int) (Math.random() * 80);

            while(listOfIndex.contains( index )) {
                index = (int) (Math.random() * 80);
            }

            listOfIndex.add(index);
            grid.set( index , 0);

        }

        return this.grid;
    }




    public int getE (int i) {
          return  this.grid.get( i );
    }


    /***************************** Only used in testSudoku *****************************/


    /**
     * Display game grid : contains hides case -> represent by 0
     *
     * @return String of game grid
     */
    public String displayGameGrid( ) {

        String result =" ";
        for(int i=0 ; i <= grid.size() - 9  ; i +=9) {

            result += this.grid.get(   i   ) + " "+this.grid.get( i + 1 ) + " "+this.grid.get( i + 2 ) + "   "+
                      this.grid.get( i + 3 ) + " "+this.grid.get( i + 4 ) + " "+this.grid.get( i + 5 ) + "   "+
                      this.grid.get( i + 6 ) + " "+this.grid.get( i + 7 ) + " "+this.grid.get( i + 8 ) + "   ";
            result += " \n ";


            if ((i + 9) % 27 == 0) {
                result += " \n ";
            }

        }

        return result;
    }



    /**
     * Display original grid : the solution
     *
     * @return String of solution grid
     */
    public String displaySolution( ) {

        ArrayList<Integer> displaySolution = this.getClone();

        String result =" ";
        for(int i=0 ; i <= displaySolution.size() - 9  ; i +=9) {

            result += displaySolution.get(   i   ) + " "+displaySolution.get( i + 1 ) + " "+displaySolution.get( i + 2 ) + "   "+
                    displaySolution.get( i + 3 ) + " "+displaySolution.get( i + 4 ) + " "+displaySolution.get( i + 5 ) + "   "+
                    displaySolution.get( i + 6 ) + " "+displaySolution.get( i + 7 ) + " "+displaySolution.get( i + 8 ) + "   ";
            result += " \n ";


            if ((i + 9) % 27 == 0) {
                result += " \n ";
            }

        }

        return result;
    }





    /***************************** Different levels for game grid *****************************/



    /**
     * Method to create a grid at level 1 : easy
     */
    public void easy () {
        grid = hideCase( 1 );
    }


    /**
     * Method to create a grid at level 2 : medium
     */
    public void medium () {
        grid = hideCase( 2 );
    }


    /**
     * Method to create a grid at level 3 : hard
     */
    public void hard () {
        grid = hideCase( 3 );
    }



}


