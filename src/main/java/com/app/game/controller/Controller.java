package com.app.game.controller;



import org.springframework.web.bind.annotation.*;
import game.sudoku.GameGrid;
import game.sudoku.GenerateGrid;
import game.demineur.LaClasse;


import java.util.ArrayList;


@CrossOrigin(origins = "*")


/************************  Sudoku  ************************/



@RequestMapping("/sudoku")
@RestController
class FirstController {


    private GenerateGrid grid;
    private GameGrid gameGrid;


    @GetMapping("/test")
    public String test() {
        return "it's working";
    }


    @GetMapping("/NewEasyGrid")
    public ArrayList<Integer> newEasyGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );
        this.grid.generateGrid();


        return this.gameGrid.hideCase( 1 );

    }


    @GetMapping("/NewMediumGrid")
    public ArrayList<Integer> newMediumGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );

        this.grid.generateGrid();


        return this.gameGrid.hideCase( 2 );

    }


    @GetMapping("/NewHardGrid")
    public ArrayList<Integer> newGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );

        this.grid.generateGrid();


        return this.gameGrid.hideCase( 3 );

    }


    @GetMapping("/GameGrid")
    public ArrayList<Integer> gameGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );

        this.grid.generateGrid();

        return gameGrid.hideCase( 0 );

    }


    @GetMapping("/SolutionGrid")
    public ArrayList<Integer> solutionGrid() {

        return gameGrid.getClone();

    }


    @GetMapping("/RestartGrid")
    public ArrayList<Integer> restartGrid() {

        return this.gameGrid.restartGameGrid();

    }


    @GetMapping("/TestArrayList")
    public ArrayList<Integer> testArrayList() {

        ArrayList<Integer> ar = new ArrayList<>();
        ar.add( 5 );
        ar.add( 2 );
        ar.add( 4 );
        ar.add( 1 );
        ar.add( 8 );

        return ar;

    }





    /************************  Démineur  ************************/





    @RestController
    @RequestMapping("/demineur")
    public class DemineurController {
        /*Argument dans LaClasse() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/

        @GetMapping("/niveauFacile")

        public String Facile() {


            LaClasse facile = new LaClasse( 1 );

            return "NIVEAU FACILE :" + facile.genererGrille();
        }

        @GetMapping("/niveauMoyen")

        public String Moyen() {

            /*Argument dans LaClasse() :
             * 1 : niveau facile 8 x 8 10 mines
             * 2 : nvieau moyen 16 x 16 40 mines
             * 3 : niveau difficile 30 x 16 99 mines*/

            LaClasse moyen = new LaClasse( 2 );

            return "NIVEAU MOYEN:" + moyen.genererGrille();
        }

        @GetMapping("/niveauDifficile")

        public String Difficile() {

            /*Argument dans LaClasse() :
             * 1 : niveau facile 8 x 8 10 mines
             * 2 : nvieau moyen 16 x 16 40 mines
             * 3 : niveau difficile 30 x 16 99 mines*/

            LaClasse difficile = new LaClasse( 3 );

            return "NIVEAU DIFFICILE :" + difficile.genererGrille();
        }
    }
}