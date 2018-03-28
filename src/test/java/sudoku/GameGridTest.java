package sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameGridTest {

    @Test
    public void getClone() {
    }

    @Test
    public void hideCase() {
        GenerateGrid e = new GenerateGrid();
        GameGrid test = new GameGrid( e );
        test.hideCase( 0 );
        int count;

       /* for(int i = 0 ; i < 81 ; i++){
            if(test.get(i) ==)
        }
        boolean a = test.contains(0)
*/
    }

    @Test
    public void restartGameGrid() {
    }

    @Test
    public void easy() {
    }

    @Test
    public void medium() {
    }

    @Test
    public void hard() {
    }
}