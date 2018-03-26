package Demineurclasses;

import Demineurclasses.Case;
import Demineurclasses.Grille;

public class TestGrille {
    public static void main (String[] args){

        Grille test = new Grille();

        test.initialiser();
        System.out.println(test.toString());

    }
}
