package com.app.sudoku;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sudoku.*;

@SpringBootApplication
public class SudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuApplication.class, args);

		GrilleSudoku test2 = new GrilleSudoku();

		int i = 1;
		int j = 1;
		test2.generateBloc(i, j);
		test2.afficherGrille();

	}
}
