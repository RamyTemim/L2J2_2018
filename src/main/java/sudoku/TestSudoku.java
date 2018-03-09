package sudoku;

public class TestSudoku {

    public static void main (String[] argvs) {

        GrilleSudoku test2 = new GrilleSudoku();

        test2.ini();
       /* for(int i=1 ; i<81 ; i++) {

        }*/
        test2.displayGrid();



       test2.generateGrid();

       test2.displayGrid();


    }
}
