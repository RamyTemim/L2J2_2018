package com.app.game.controller;



import com.app.game.model.Grid;
import game.demineur.ClasseDemineur;
import org.springframework.web.bind.annotation.*;
import game.sudoku.GameGrid;
import game.sudoku.GenerateGrid;


import java.util.ArrayList;


@CrossOrigin(origins = "*")


/************************  Sudoku  ************************/



@RequestMapping("/sudoku")
@RestController
class FirstController {


    private GenerateGrid grid;
    private GameGrid gameGrid;



    @PostMapping("/validateGrid")
    public boolean valideGrid(@RequestBody Grid grid) {
        ArrayList clone = gameGrid.getClone();
        boolean res = true;
        System.out.println("------- test grid -------");
        for (int i = 0; i < grid.getArrayList().size(); i++) {
            String crtClient = grid.getArrayList().get( i ).toString();
            String crtGrid = clone.get( i ).toString();
            System.out.println(crtClient + " " + crtGrid);
            if (crtClient.equals( "?" ) || !crtClient.equals( crtGrid )) res = false;
        }

        System.out.println(res);
        return res;
    }

    @GetMapping("/NewEasyGrid")
    public ArrayList<Integer> newEasyGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );
        grid.generateGrid();


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

        private ClasseDemineur grille;
        /*Argument dans ClasseDemineur() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/


        @GetMapping("/niveauFacile")
        public String Facile() {


           this.grille = new ClasseDemineur( 1 );

            return  this.grille.genererGrille();
        }


        @GetMapping("/niveauMoyen")
        public String Moyen() {

            /*Argument dans ClasseDemineur() :
             * 1 : niveau facile 8 x 8 10 mines
             * 2 : nvieau moyen 16 x 16 40 mines
             * 3 : niveau difficile 30 x 16 99 mines*/

            this.grille = new ClasseDemineur( 2 );

            return  this.grille.genererGrille();
        }


        @GetMapping("/niveauDifficile")
        public String Difficile() {

            /*Argument dans ClasseDemineur() :
             * 1 : niveau facile 8 x 8 10 mines
             * 2 : nvieau moyen 16 x 16 40 mines
             * 3 : niveau difficile 30 x 16 99 mines*/

            this.grille = new ClasseDemineur( 3 );

            return  this.grille.genererGrille();
        }
    }
}