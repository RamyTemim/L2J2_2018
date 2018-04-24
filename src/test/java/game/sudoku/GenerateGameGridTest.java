package game.sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GenerateGameGridTest {

    @Test
    public void getClone() {
    }


    @Test
    public void hideCase() {
        GenerateGrid e = new GenerateGrid();
        GenerateGameGrid test = new GenerateGameGrid( e );
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
    public void easy() {
        GenerateGrid e = new GenerateGrid();
        GenerateGameGrid test = new GenerateGameGrid( e );
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
        GenerateGameGrid test = new GenerateGameGrid( e );
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
        GenerateGameGrid test = new GenerateGameGrid( e );
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