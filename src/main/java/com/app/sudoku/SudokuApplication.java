package com.app.sudoku;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sudoku.*;

@SpringBootApplication
public class SudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuApplication.class, args);

		MethodSudoku test2 = new MethodSudoku();

		int i = 0;

		//test2.generateGrid();
		test2.displayGrid();

	}
}
