package sudoku;

public class TestSudoku {

    public static void main (String[] argvs) {


        MethodSudoku test2 = new MethodSudoku();
        GenerateGrid test = new GenerateGrid();
        GameGrid gg = new GameGrid(test2 , test );



        test.generateGrid( test2 );

        System.out.println(test2.displayGrid());

        gg.hideCase();
        System.out.println(gg.displayGameGrid());
        System.out.println(gg.displaySoltion());






    }
}





