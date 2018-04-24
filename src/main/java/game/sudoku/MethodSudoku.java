package game.sudoku;

import lombok.Data;

import java.util.ArrayList;


@Data
public class MethodSudoku {


    private ArrayList <Integer> grille;



    //Constructor
    MethodSudoku() {
        this.grille = new ArrayList <> ();
    }


    MethodSudoku(ArrayList<Integer> e) {
        this.grille = e;
    }




    /****************** Getter and Setter ***********************/


    /**
     * Initialize an ArrayList with a tab for input
     *      (to make run test Junit)
     *
     * Setter
     * @param e tab of int
     */

    public void setGrid ( int [] e){
        for(int i=0 ; i < e.length - 1 ; i++) {
            this.grille.add(e[i]);
        }
    }


    /**
     * Get back value of element at index i
     *
     * Getter
     *
     * @param i index of case
     * @return value of i
     */
    public int getGrid(int i) {
        return this.grille.get(i);
    }







    /****************** Checking ***********************/


                //Line

    /**
     * Check if already on line
     *
     * @param i index of case
     * @param num number to test
     * @return boolean (true = not already on line)
     */
    public boolean availableLine(int i, int num) {

        int k = i - (i % 9);

        for (int m = k; (m < grille.size() - 1) ; m++) {
            if(this.grille.get(m) == num) return false;
        }

        return true;


    }




                //Column

    /**
     * Return the first index of a column
     *
     * @param cel index of case
     * @return index of 1st case on column
     */
    public int getFirstindexCol (int cel) {

        while(cel >= 9 ) cel -= 9;
        return cel;

    }


    /**
     * Check if already on column
     *
     * @param i index of case
     * @param nb number to test
     * @return boolean (true = not already on column)
     */
    public boolean availableColumn(int i, int nb) {
        int j = getFirstindexCol( i );

        while(  (j < grille.size() - 1)  ) {
            if(this.grille.get(j) == nb) {
                return false;
            }

            j += 9;
        }
        return true;
    }




                //Block

    /**
     * Return the first index of a block
     *
     * @param cel index of case
     * @return index of 1st case on block
     */
    public int firstindexOfBloc (int cel) {

        int firstColumn = cel - (cel%3);
        int k = firstColumn - (firstColumn%27);
        int l = firstColumn%9;

        int index = k + l ;

        return index;

    }


    /**
     * Check if already in bloc
     *
     * @param i index of case
     * @param nb number to test
     * @return boolean (true = not already on block)
     *
     */
    public boolean availableBloc(int i, int nb) {
        int k = firstindexOfBloc( i );
        int l = k + 27;


        while ( k < l   &&   k < grille.size() - 1 ) {
            int n = k;
            int h = k + 3;

            while( n < h && n < grille.size() - 1 ){
                if(this.grille.get(n) == nb) return false;
                n++;
            }
            k += 9;
        }

        return true;
    }





                // Block , column and line

    /**
     * Check that number can be placed on line, column and bloc
     *
     * @param i index of case
     * @param nb number to test
     * @return boolean (true = it's possible to place it)
     */
    public boolean checkNumber(int i, int nb) {
        return (availableLine( i, nb ) && availableColumn( i, nb ) && availableBloc( i, nb ));
    }




                // Idem + for all numbers



    /**
     * Check if it possible to placed any number between 1 and 9
     *
     * @param i index of case to check
     * @return boolean (true = it's possible)
     */
    public boolean isPossible(int i) {

        for (int num = 1; num < 10; num++) {

            if ((checkNumber( i, num ))) {
                return true;
            }
        }
        return false;
    }



    public boolean checkGrid () {
        return (!grille.contains( 0 ));
    }






    /****************** Creating & Displaying ***********************/


    /**
     * Generates a random number between 1 and 9
     *
     * @return number
     */
    public int generateNb() {
        return (int) (Math.random() * 9) + 1;
    }


    /**
     * Generates an element which can be placed
     */
    public void generateCase() {
        int i; int num;

        grille.add( 0 );
        i =  grille.size() - 1;

        if(isPossible(i)) {

            do {
                num = generateNb();
            } while (!checkNumber( i, num ));
            grille.set(i , num);

        }

    }


    /**
     * Display an ArrayList -> to run Junit test
     *
     * @return String contains elements of ArrayList
     */
    public String displayGrid( ) {

        String result =" ";
        for(int i=0 ; i <= grille.size() - 9  ; i +=9) {

            result += this.grille.get( i     ) + " "+this.grille.get( i + 1 ) + " "+this.grille.get( i + 2 ) + "   "+
                      this.grille.get( i + 3 ) + " "+this.grille.get( i + 4 ) + " "+this.grille.get( i + 5 ) + "   "+
                      this.grille.get( i + 6 ) + " "+this.grille.get( i + 7 ) + " "+this.grille.get( i + 8 ) + "   ";
            result += " \n ";


            if ((i + 9) % 27 == 0) {
                result += " \n ";
            }
        }
        return result;
    }





    /****************** Methods of Arraylist ***********************/


    /**
     * Getter for method ArrayList.size()
     *
     * @return size of ArrayList
     */
    public int sizeGrid (){
        return this.grille.size();
    }




    /**
     * Setter for method ArrayList.add( int number)
     *
     *   ADD
     */
    public void addGrid (int number) {
        this.grille.add( number );
    }




    /**
     * Setter for method ArrayList.remove( int index)
     *
     *  REMOVE
     */
    public void removeElement (int i) {
        this.grille.remove(i);
    }



    /**
     * Test if list contains element in param
     *
     * @param e index of case
     * @return
     */
    public boolean containsElement (int e) {
        return this.grille.contains(e);
    }




}