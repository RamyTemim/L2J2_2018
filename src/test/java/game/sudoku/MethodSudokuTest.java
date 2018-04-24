package game.sudoku;

import game.sudoku.MethodSudoku;
import org.junit.Assert;
import org.junit.Test;

public class MethodSudokuTest {

    //Déclaration d'un objet de la classe game
    private MethodSudoku grille = new MethodSudoku();

    //Initialisation d'un tableau d'entier qui sera attribué à l'objet


    private int[] tab = {0, 2, 3,  4, 5, 6,  7, 8, 9,
                         8, 6, 5,  3, 2, 1,  4, 0, 0,
                         9, 7, 3,  0, 0, 0,  0, 0, 0,

                         7, 0, 0,  0, 0, 0,  0, 0, 0,
                         6, 0, 0,  0, 0, 0,  0, 0, 0,
                         5, 0, 0,  0, 0, 0,  0, 0, 0,

                         4, 0, 0,  0, 0, 0,  0, 0, 0,
                         3, 0, 0,  0, 0, 0,  0, 0, 0,
                         2, 0, 0,  0, 0, 0,  0, 0, 0};


    private int[] tab2 = {0, 2, 3,  4, 8, 7,  6, 5, 9,
                          8, 6, 5,  9, 1, 0};







    @Test
    public void availableLine() {

        grille.setGrid( tab2 );

        boolean a = grille.availableLine( 14, 7 );
        boolean b = grille.availableLine( 0, 2 );

        Assert.assertTrue( a );
        Assert.assertFalse( b );

        System.out.println( "Test ligne :" + " " + "true?" + a + " " + "false?" + b );

    }





    @Test
    public void availableColumn() {

        grille.setGrid( tab );
        boolean a = grille.availableColumn( 0, 1 );
        boolean b = grille.availableColumn( 0, 2 );

        Assert.assertTrue( a );
        Assert.assertFalse( b );

        System.out.println( "Test Column :" + " " + "true?" + a + " " + "false?" + b );

    }





    @Test
    public void availableBloc() {

        grille.setGrid( tab2 );
        boolean a = grille.availableBloc(14 , 6 );
        boolean b = grille.availableBloc( 14, 1 );

        Assert.assertTrue(a);
      // Assert.assertFalse( b );

        System.out.println( "Test Bloc :" + " " + "true?" +a+ " " + "false?" + b );

    }






    @Test
    public void generateCase() {

        // grille.setGrid( tab2 );
        grille.generateCase();
        System.out.println("index "+(grille.sizeGrid() - 1 )+" "+grille.getGrid( (grille.sizeGrid() - 1) ));

        while(grille.sizeGrid() < 81) {
            grille.generateCase();
            System.out.println( "index " + (grille.sizeGrid() - 1) + " " + grille.getGrid( (grille.sizeGrid() - 1) ) );

        }

        System.out.println("size : "+grille.sizeGrid());


    }





    @Test
    public void firstindexOfBloc() {

        grille.setGrid( tab2 );

        int testT = grille.firstindexOfBloc( 8 );

        boolean a = (testT == 6);
        boolean b = (testT != 6);

        Assert.assertTrue( a );
        Assert.assertFalse(b);

        System.out.println( "Test 1st index of block :" + " " + "true?" + a + " " + "false?" + b );


    }


    @Test
    public void availableBloc1() {
        grille.setGrid( tab2 );
        grille.addGrid( 0 );
        boolean a = grille.availableBloc(14 , 6 );
        boolean b = grille.availableBloc( 14, 1 );

        Assert.assertTrue(a);
        Assert.assertFalse( b );

        System.out.println( "Test Bloc :" + " " + "true?" +a+ " " + "false?" + b );

    }
}