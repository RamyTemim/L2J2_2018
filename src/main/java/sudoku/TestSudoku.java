package sudoku;

public class TestSudoku {

    public static void main (String[] argvs) {

        MethodSudoku test2 = new MethodSudoku();
        GenerateGrid test = new GenerateGrid();

        test2.ini();
        test2.displayGrid();

        test.generateGrid( test2 );



    }
}
