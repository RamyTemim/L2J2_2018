package sudoku;

public class TestSudoku {

    public static void main (String[] argvs) {

        GrilleSudoku test2 = new GrilleSudoku();

        test2.generateNbRamdom();
        test2.afficherGrille();
        test2.generateNbRamdom();
        test2.afficherGrille();
        test2.generateNbRamdom();
        test2.afficherGrille();


    }
}
