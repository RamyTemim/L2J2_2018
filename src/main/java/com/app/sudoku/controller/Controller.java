package com.app.sudoku.controller;


import org.springframework.web.bind.annotation.*;
import sudoku.GameGrid;
import sudoku.GenerateGrid;


import java.util.ArrayList;


@CrossOrigin(origins = "*")
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


        return this.gameGrid.hideCase(1);

    }





    @GetMapping("/NewMediumGrid")
    public ArrayList<Integer> newMediumGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );

        this.grid.generateGrid();


        return this.gameGrid.hideCase(2);

    }







    @GetMapping("/NewHardGrid")
    public ArrayList<Integer> newGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );

        this.grid.generateGrid();


        return this.gameGrid.hideCase(3);

    }








    @GetMapping("/GameGrid")
    public ArrayList<Integer> gameGrid() {

        grid = new GenerateGrid();
        gameGrid = new GameGrid( grid );

        this.grid.generateGrid(  );

        return gameGrid.hideCase(0);

    }






    @GetMapping("/SolutionGrid")
    public ArrayList <Integer> solutionGrid() {

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
}

