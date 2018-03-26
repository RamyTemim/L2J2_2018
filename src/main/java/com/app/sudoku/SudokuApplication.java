package com.app.sudoku;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuApplication.class, args);


		/*MethodSudoku test2 = new MethodSudoku();
		GenerateGrid test = new GenerateGrid();

		test.generateGrid(test2);

        System.out.println(test2.displayGrid());
*/
	}
}
