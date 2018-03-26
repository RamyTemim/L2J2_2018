package sudoku;

import java.util.ArrayList;


public class GameGrid {

    private MethodSudoku ms;
    private GenerateGrid e;

    private ArrayList <Integer> clone;

    // Constructor

    public GameGrid (MethodSudoku ms , GenerateGrid e) {
        this.ms = ms;
        this.e = e;
        this.clone = new ArrayList<>( );
    }



    /**
     *
     * Private method which create a clone of e.generateGrid() in an Arraylist
     *
     */
    private void getClone() {

        for(int i = 0 ; i < 81 ; i++){
            this.clone.add(e.getListForReturn( i ));
        }
        System.out.println(clone.toString());
    }




    /**
     *
     * Hide between 40 and 50 case of cloned grid
     *
     * void method which set the private Arraylist "clone"
     */

    public ArrayList<Integer> hideCase () {

        getClone();
        //int nb = (int) (Math.random() * 50) + 40;
        int nb=40;
        ArrayList <Integer> listOfIndex = new ArrayList<>( );

        for(int i = 0; i < nb + 1; i++) {

            int index = (int) (Math.random() * 80);

            while(listOfIndex.contains( index )) {

                index = (int) (Math.random() * 80);
            }

            listOfIndex.add(index);

            this.clone.set( index , 0);
        }

        System.out.println(clone.toString());

        return(clone);
    }





    public String displayGameGrid() {

        return e.displayGenerateGrid();

    }

    public String displaySoltion() {
        return displayClone();
    }




    public String displayClone( ) {

        String result =" ";
        for(int i=0 ; i <= clone.size() - 9  ; i +=9) {

            result += this.clone.get(   i   ) + " "+this.clone.get( i + 1 ) + " "+this.clone.get( i + 2 ) + "   "+
                      this.clone.get( i + 3 ) + " "+this.clone.get( i + 4 ) + " "+this.clone.get( i + 5 ) + "   "+
                      this.clone.get( i + 6 ) + " "+this.clone.get( i + 7 ) + " "+this.clone.get( i + 8 ) + "   ";
            result += " \n ";



            if ((i + 9) % 27 == 0) {
                result += " \n ";
            }

        }



        return result;
    }




    public void chooseDifficulty () {


    }


}
