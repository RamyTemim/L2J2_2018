package com.app.sudoku.controller;


import org.springframework.web.bind.annotation.*;
import sudoku.GameGrid;
import sudoku.GenerateGrid;
import sudoku.MethodSudoku;

import java.util.ArrayList;


@CrossOrigin(origins = "*")
@RequestMapping("/sudoku")
@RestController
class FirstController {


    private String sudoku;
    private MethodSudoku ms;
    private GenerateGrid grid;
    private GameGrid gameGrid = new GameGrid( ms, grid );

    @GetMapping("/test")
    public String test () {
        return "it's working";
    }

    @GetMapping("/displayGrid")
    public String display(GameGrid e) {
    this.ms = new MethodSudoku();
        this.ms.ini();
        this.sudoku = ms.displayGrid();
        return this.sudoku;

    }

    @GetMapping("/generateGrid")
    public String generate() {
        this.ms = new MethodSudoku();
        grid = new GenerateGrid();

        grid.generateGrid(this.ms);

        return this.ms.displayGrid();

    }

    @GetMapping("/GameGrid")
    public String gameGrid () {
        grid = new GenerateGrid();
        ms = new MethodSudoku();

        this.gameGrid.hideCase();

       return gameGrid.displayGameGrid();

    }

    @GetMapping("/SolutionGrid")
    public String solutionGrid () {
        grid = new GenerateGrid();
        ms = new MethodSudoku();


        return this.gameGrid.displaySoltion();

    }
}

