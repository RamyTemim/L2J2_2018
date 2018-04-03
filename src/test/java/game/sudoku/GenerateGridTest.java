package game.sudoku;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenerateGridTest {

    @Test
    public void generateGrid() {

        GenerateGrid e = new GenerateGrid();

        ArrayList<Integer> method = new ArrayList<>(  );

        method = e.generateGrid();

        for (int i = 0 ; i < 81 ; i++){
            method.add(e.getI( i ));
        }


        MethodSudoku ms = new MethodSudoku( method );
        boolean a = true;

        for (int i = 0 ; i < 81 ; i++) {

            if(!(ms.checkNumber( i , ms.getGrid( i ) ))) a = false;

        }

        assertTrue( a );
    }
}