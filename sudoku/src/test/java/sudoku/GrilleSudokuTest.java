package sudoku;

import org.junit.Test;

public class GrilleSudokuTest {

    //Déclaration d'un objet de la classe sudoku
    GrilleSudoku grilleSudoku = new GrilleSudoku();

    //Initialisation d'un tableau d'entier qui sera attribué à l'objet
    int [][] tab = {{0,2,3,4,5,6,7,8,9},
                    {8,6,5,0,0,0,0,0,0},
                    {9,7,3,0,0,0,0,0,0},
                    {7,0,0,0,0,0,0,0,0},
                    {6,0,0,0,0,0,0,0,0},
                    {5,0,0,0,0,0,0,0,0},
                    {4,0,0,0,0,0,0,0,0},
                    {3,0,0,0,0,0,0,0,0},
                    {2,0,0,0,0,0,0,0,0}};


    /*************  Fait  *************/

    @Test
    public void existLigne() {
        grilleSudoku.setTab(tab);
        boolean a = grilleSudoku.existLigne(0,1);
        boolean b = grilleSudoku.existBloc(0,0,2);
        System.out.println("Test ligne :"+" "+"faux?"+a+" "+"vrai?"+b);
    }

    @Test
    public void existColonne() {
        grilleSudoku.setTab(tab);
        boolean a = grilleSudoku.existLigne(0,1);
        boolean b = grilleSudoku.existBloc(0,0,2);
        System.out.println("Test colonne :"+" "+"faux?"+a+" "+"vrai?"+b);
    }

    @Test
    public void existBloc() {
        grilleSudoku.setTab(tab);
        boolean a = grilleSudoku.existBloc(0,0,1);
        boolean b = grilleSudoku.existBloc(0,0,2);
        System.out.println("Test bloc :"+" "+"faux?"+a+" "+"vrai?"+b);
    }

    @Test
    public void checkNumber() {
        grilleSudoku.setTab(tab);
        boolean a = grilleSudoku.checkNumber(0,0,1);
        boolean b = grilleSudoku.checkNumber(0,0,2);
        System.out.println("Test number :"+" "+"faux?"+a+" "+"vrai?"+b);
    }

    @Test
    public void generateNb() {
        boolean res;
        int num = grilleSudoku.generateNb();
        if(num<1 || num>9) res =false;
        else res=true;

        System.out.println("correct ? "+res);
        System.out.println("number is : "+num);
    }

    @Test
    public void afficherGrille() {
        grilleSudoku.setTab(tab);
        grilleSudoku.afficherGrille();
    }

    @Test
    public void generateLigne() {
        grilleSudoku.setTab(tab);
        grilleSudoku.generateLigne(2);
        for(int i=2,j=0;j<9;j++) System.out.println(grilleSudoku.getTab(i, j));
    }

    /************  A faire  **************/



    @Test
    public void generateGrille() {
        grilleSudoku.setTab(tab);
        int i=0;
        while(i<9) {
            grilleSudoku.generateLigne(i);
            System.out.println("Ligne :"+i);
            for (int m = i, j = 0; j < 9; j++) System.out.println(grilleSudoku.getTab(i, j));
            i++;
        }
        //grilleSudoku.generateGrille();
        //grilleSudoku.afficherGrille();
    }
}