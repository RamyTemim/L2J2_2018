package game.sudoku;

import game.sudoku.GameGrid;
import game.sudoku.GenerateGrid;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GameGridTest {

    @Test
    public void getClone() {
    }


    @Test
    public void hideCase() {
        GenerateGrid e = new GenerateGrid();
        GameGrid test = new GameGrid( e );
        test.hideCase( 0 );
        int count=0;

        ArrayList<Integer> list = new ArrayList<>(  );


        for(int i = 0 ; i < 81 ; i++) {
            list.add( test.getE( i ) );
        }


        for(int i = 0 ; i < 81 ; i++) {
            if (list.get( i ) == 0) count += 1;
        }

        boolean a = (count == 40);
        boolean b = (count != 40);

        Assert.assertTrue( a );
        Assert.assertFalse( b );

    }


    @Test
    public void restartGameGrid() {
        GenerateGrid e = new GenerateGrid();
        GameGrid test = new GameGrid( e );


        ArrayList<Integer> list = test.hideCase( 0 );
        ArrayList<Integer> list2 = test.restartGameGrid();



        boolean a = (list.equals( list2));
        boolean b = (!(list.equals( list2)));


        Assert.assertTrue( a );
        Assert.assertFalse( b );

    }




    @Test
    public void easy() {
        GenerateGrid e = new GenerateGrid();
        GameGrid test = new GameGrid( e );
        test.easy();
        int count=0;

        ArrayList<Integer> list = new ArrayList<>(  );


        for(int i = 0 ; i < 81 ; i++) {
            list.add( test.getE( i ) );
        }


        for(int i = 0 ; i < 81 ; i++) {
            if (list.get( i ) == 0) count += 1;
        }


        boolean a = (count == 30);
        boolean b = (count != 30);


        Assert.assertTrue( a );
        Assert.assertFalse( b );

    }




    @Test
    public void medium() {

        GenerateGrid e = new GenerateGrid();
        GameGrid test = new GameGrid( e );
        test.medium(  );
        int count=0;

        ArrayList<Integer> list = new ArrayList<>(  );



        for(int i = 0 ; i < 81 ; i++) {
            list.add( test.getE( i ) );
        }


        for(int i = 0 ; i < 81 ; i++) {
            if (list.get( i ) == 0) count += 1;
        }



        boolean a = (count == 40);
        boolean b = (count != 40);


        Assert.assertTrue( a );
        Assert.assertFalse( b );

    }




    @Test
    public void hard() {
        GenerateGrid e = new GenerateGrid();
        GameGrid test = new GameGrid( e );
        test.hard(  );
        int count=0;


        ArrayList<Integer> list = new ArrayList<>(  );



        for(int i = 0 ; i < 81 ; i++) {
            list.add( test.getE( i ) );
        }


        for(int i = 0 ; i < 81 ; i++) {
            if (list.get( i ) == 0) count += 1;
        }



        boolean a = (count == 50);
        boolean b = (count != 50);


        Assert.assertTrue( a );
        Assert.assertFalse( b );

    }
}