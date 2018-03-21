package sudoku;

import lombok.Data;

import java.util.ArrayList;
import java.util.jar.Pack200;


@Data
public class MethodSudoku {


    private ArrayList <Integer> grille;




    //Constructor
    public MethodSudoku() {
       this.grille = new ArrayList <> ();
     }







    /****************** Getter and Setter ***********************/


        //Setter

    public void setGrid ( int [] e){

        for(int i=0 ; i < e.length - 1 ; i++) {

            this.grille.add(e[i]);

            System.out.println(this.grille.get(i));

        }

    }




        //Getter

    public int getGrid(int i) {

        return this.grille.get(i);

    }












    /****************** Checking ***********************/


        //Line

    //Check if already on line
    public boolean availableLine(int i, int num) {


        int k = i - (i % 9);


        for (int m = k; (m < grille.size() - 1) ; m++) {

            if(this.grille.get(m) == num) return false;
            
        }

        return true;


    }




        //Column

    //Return the first index of a column
    public int getFirstindexCol (int cel) {

        while(cel >= 9 ) cel -= 9;
        return cel;

    }



    //Check if already on column
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

    //Return the first index of a block
    public int firstindexOfBloc (int cel) {

        int firstColumn = cel - (cel%3);
        int k = firstColumn - (firstColumn%27);
        int l = firstColumn%9;

        int index = k + l ;

        return index;

    }



    //Check if already in bloc
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

    //Check that number can be placed on line, column and bloc
    public boolean checkNumber(int i, int nb) {

        boolean res = (availableLine( i, nb ) && availableColumn( i, nb ) && availableBloc( i, nb ));

        return res;

    }







        // Idem + for all numbers

    //Check if it possible to placed any number between 1 and 9
    public boolean isPossible(int i) {


        for (int num = 1; num < 9; num++) {

            if ((checkNumber( i, num ))) {
               return true;
            }

        }

        return false;

    }




    public boolean checkGrid () {

       /* for (int i = 0; i < grille.size() - 1; i++) {

            int value =  grille.get( i );
            grille.set( i , 0);

            boolean res = availableColumn( i , value ) && availableLine( i , value ) && availableBloc( i , value );

            if (res) {
                this.grille.set( i , value );
            }




        }*/

        if((grille.contains( 0 )) ) {
            System.out.println("NOT OK checking");
            return false;
        }
        else {
            System.out.println("OK checking");
            return true;
        }
    }








    /****************** Creating & Displaying ***********************/



    //Generates a random number between 1 and 9
    public int generateNb() {

        return (int) (Math.random() * 9) + 1;

    }






    //Generates an element which can be placed
    public void generateCase() {

        int i;
        int num;

        grille.add( 0 );
        i =  grille.size() - 1;


        if(isPossible(i) && grille.get( i ) == 0) {

            do {

                num = generateNb();

            } while (!checkNumber( i, num ));

            grille.set(i , num);
        }




    }






    //Display Sudoku puzzle
    public void displayGrid() {

        for(int i=0 ; i <= grille.size() - 9  ; i +=9) {

            System.out.println( this.grille.get( i ) + " " + this.grille.get( i + 1 ) + " " + this.grille.get( i + 2 ) + "   " + this.grille.get( i + 3 ) + " " + this.grille.get( i + 4 )
                                + " " + this.grille.get( i + 5 ) + "   " + this.grille.get( i + 6 ) + " "+ this.grille.get( i + 7 ) + " " + this.grille.get( i + 8 ) );


            if ( (i+9)%27 == 0) {
                System.out.println( " " );
            }


        }

    }












    /****************** Methods of Arraylist ***********************/



        // return Arraylist.size()

    public int sizeGrid (){

        return this.grille.size();

    }





        // Arraylist.add()

    public void addGrid (int number) {

        this.grille.add( number );

    }





        // Arraylist.add(Index , Element)

    public void addGrid (int index , int number) {

        this.grille.add( index , number );

    }




        // Arraylist.set(Index , Element)

    public void setGrid ( int index , int number) {

        this.grille.set(index , number);

    }






        // Arraylist.remove()

    public void removeElement (int i) {

        this.grille.remove(i);


    }



    public boolean containsElement (int e) {

        return this.grille.contains(e);

    }








































      /*  //Set element to 0
    public void setGridIni (int i){
        this.grille.set( i , 0 );
    }*/

   /* //Initialize an ArrayList
    public void ini () {

        for(int i = 0 ; i < 81 ; i++) {
            this.grille.add( i , 0 );
        }

    }*/


 //Replace an element by an other
    public void replaceElement (int i , int num) {

        this.grille.set(i , num);

    }


  /*  //Remove element at index i, while nb cannot be placed
    public void removeGridFrom(int i, int nb) {
        int number;
        this.index = i;


        if (!availableLine( this.index , nb)){

            while(!availableLine( this.index , nb )) {
                this.grille.remove( this.index );
                this.grille.add( this.index , 0 );
                this.index--;

            }

            number = generateCase( this.index );
            this.grille.add( this.index , number );
        }


        else if (!availableColumn( this.index , nb)){

            while(!availableColumn( this.index , nb )) {

                this.grille.remove( this.index );
                this.grille.add( this.index , 0 );
                this.index--;

            }

            number = generateCase( this.index );
            this.grille.add( this.index , number );
        }


        else if (!availableBloc( this.index , nb)){

            while(!availableBloc( this.index , nb )) {

                this.grille.remove( this.index );
                this.grille.add( this.index , 0 );
                this.index--;

            }

            number = generateCase( this.index );
            this.grille.add( this.index , number );

        }

    }

    //Generates a Sudoku puzzle
    public void generateGrid() {

        boolean test;
        int nombre;

        while (this.index < 81) {

            nombre = generateCase( this.index );
            System.out.println("numI "+this.index+" :"+nombre);
            test = isPossible( this.index );

            if (!test) {


                System.out.println("numA "+this.index+" :"+nombre);

                removeGridFrom( this.index, nombre );
                this.index--;
                this.grille.remove( this.index );
                this.grille.add( this.index , 0 );
                nombre = generateCase( this.index );

                System.out.println("numB "+this.index+" :"+nombre);

                this.grille.add( this.index , nombre );

            }

            else if( this.grille.get(this.index) == 0 || isPossible( this.index )) {
                nombre = generateCase( this.index );
                this.grille.add( this.index , nombre );
            }


            System.out.println("numF "+this.index+" :"+nombre);

            this.index++;

        }

    }

*/


}
