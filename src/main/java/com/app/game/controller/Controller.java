package com.app.game.controller;



import com.app.game.model.Grid;
import game.demineur.Demineur;
import org.springframework.web.bind.annotation.*;
import game.sudoku.GenerateGameGrid;
import game.sudoku.GenerateGrid;


import java.util.ArrayList;

import static com.sun.activation.registries.LogSupport.log;


/************************  Sudoku  ************************/


@CrossOrigin(origins = "*")
@RequestMapping("/sudoku")
@RestController
class FirstController {


    private GenerateGrid grid;
    private GenerateGameGrid gameGrid;


    @PostMapping("/validateGrid")
    public boolean valideGrid(@RequestBody Grid grid) {
        ArrayList clone = gameGrid.getClone();
        boolean res = true;
        System.out.println( "------- test grid -------" );
        for (int i = 0; i < grid.getArrayList().size(); i++) {
            String crtClient = grid.getArrayList().get( i ).toString();
            String crtGrid = clone.get( i ).toString();
            System.out.println( crtClient + " " + crtGrid );
            if (crtClient.equals( "?" ) || !crtClient.equals( crtGrid )) res = false;
        }

        System.out.println( res );
        return res;
    }

    @GetMapping("/NewEasyGrid")
    public ArrayList<Integer> newEasyGrid() {

        grid = new GenerateGrid();
        gameGrid = new GenerateGameGrid( grid );
        grid.generateGrid();


        return this.gameGrid.hideCase( 1 );

    }


    @GetMapping("/NewMediumGrid")
    public ArrayList<Integer> newMediumGrid() {

        grid = new GenerateGrid();
        gameGrid = new GenerateGameGrid( grid );

        this.grid.generateGrid();


        return this.gameGrid.hideCase( 2 );

    }


    @GetMapping("/NewHardGrid")
    public ArrayList<Integer> newGrid() {

        grid = new GenerateGrid();
        gameGrid = new GenerateGameGrid( grid );

        this.grid.generateGrid();


        return this.gameGrid.hideCase( 3 );

    }


    @GetMapping("/GameGrid")
    public ArrayList<Integer> gameGrid() {

        grid = new GenerateGrid();
        gameGrid = new GenerateGameGrid( grid );

        this.grid.generateGrid();

        return gameGrid.hideCase( 0 );

    }


    @GetMapping("/SolutionGrid")
    public ArrayList<Integer> solutionGrid() {

        return gameGrid.getClone();

    }




    /************************  Démineur  ************************/



    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/demineur")
    public class DemineurController {

        private Demineur grilleD;
        /*Argument dans Demineur() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/


        @GetMapping("/niveauFacile")
        public String Facile() {

            this.grilleD = new Demineur( 1 );
            return  this.grilleD.genererGrilleArray();

        }

        @GetMapping("/niveauMoyen")
        public String Moyen() {

            this.grilleD = new Demineur( 2 );
            return  this.grilleD.genererGrilleArray();
        }

        @GetMapping("/niveauDifficile")
        public String Difficile() {

            this.grilleD = new Demineur( 3 );
            return  this.grilleD.genererGrilleArray();
        }



        @GetMapping("/devoileGrid")
        public String devoileCase(@RequestParam("id") int id) {

            return this.grilleD.devoile(id);
        }


    }
}


