package sudoku;

import lombok.Data;

import java.util.ArrayList;


@Data
public class MethodSudoku {


    //Set element to 0
    public void setGridIni (int i){
        this.grille.set( i , 0 );
    }



    private ArrayList <Integer> grille;

    private int index = 0;


    //Constructor
    public MethodSudoku() {
       this.grille = new ArrayList <> ();
     }






    /****************** Vérifications ***********************/


    //Setter
    public void setGrid ( int [] e){
        for(int i=0 ; i < 81 ; i++) {

            this.grille.add(e[i]);

            System.out.println(this.grille.get(i));

        }

    }

    //Getter
    public int getGrid(int i) {
        return this.grille.get(i);
    }



    //Initialize an ArrayList
    public void ini () {

        for(int i = 0 ; i < 81 ; i++) {
            this.grille.add( i , 0 );
        }

    }






    //Check if already on line
    public boolean availableLine(int i, int num) {

        boolean res = true;
        int k = i - (i % 9);


        int length = k + 7;

        for (int m = k; m < length - 1; m++) {
            
            if(this.grille.get(m) == num) res = false;
            
        }

        return res;
    }





    //Return the first index of a column
    public int getFirstindexCol (int cel) {

        while(cel >= 9 ) cel -= 9;
        return cel;

    }


    //Check if already on column
    public boolean availableColumn(int i, int nb) {
            boolean res = true;
            int j = getFirstindexCol( i );

        while(j < grille.size()-1 ) {

            if(this.grille.get(j) == nb) {
                res=false;
                break;
            }

            j += 9;
        }


        return res;

    }






    //Return the first index of a block
    public int firstindexOfBloc (int cel) {
        int firstColumn = cel - (cel%3);
        int k = firstColumn - (firstColumn%27) +9 ;

        int index = k + firstColumn ;
        //while (firstColumn > k  ) firstColumn -= 9;

        return index;

    }


    //Check if already in bloc
    public boolean availableBloc(int i, int nb) {

        boolean res = true;

        int m = firstindexOfBloc( i );
        int length = m + 18;

            for (int k = m; k < length - 1 ; k += 9) {

                for (int n = k ; n < (k + 2); n++) {

                    if(this.grille.get(n) == nb) res = false;

                }

            }

        return res;

    }






    //Check that number can be placed on line, column and bloc
    public boolean checkNumber(int i, int nb) {

        boolean res = (availableLine( i, nb ) && availableColumn( i, nb ) && availableBloc( i, nb ));

        return res;

    }






    //Check if it possible to placed any number between 1 and 9
    public boolean isPossible(int i) {

        boolean res = false;

        for (int num = 1; num < 9; num++) {

            if ((checkNumber( i, num ))) {
                res = true;
                break;
            }

        }

        return res;

    }




    /****************** Creation & Affichage ***********************/


    //Generates a random number between 1 and 9
    public int generateNb() {

        return (int) (Math.random() * 9) + 1;

    }




    //Generates an element which can be placed
    public int generateCase(int i) {

        int num;

        do {

            num = generateNb();

        }  while (!checkNumber( i, num ));

        this.grille.remove( i );

        return num;

    }




    //Remove an element of ArrayList
    public void removeElement (int i) {

        i--;
        this.grille.set( i , 0 );


    }



    //Replace an element by an other
    public void replaceElement (int i , int num) {

        this.grille.set(i , num);

    }




    //Display Sudoku puzzle
    public void displayGrid() {

       for(int i=0 ; i <= 72 ; i +=9) {

            System.out.println( this.grille.get( i ) + " " + this.grille.get( i + 1 ) + " " + this.grille.get( i + 2 ) + "   " + this.grille.get( i + 3 ) + " " + this.grille.get( i + 4 ) + " " + this.grille.get( i + 5 ) + "   " + this.grille.get( i + 6 ) + " " + this.grille.get( i + 7 ) + " " + this.grille.get( i + 8 ) );

            if ( (i+9)%27 == 0) {
                System.out.println( " " );
            }


        }

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
