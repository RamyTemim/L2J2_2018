package com.app.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run( GameApplication.class, args);


		/*MethodSudoku test2 = new MethodSudoku();
		GenerateGrid test = new GenerateGrid();

		test.generateGrid(test2);

        System.out.println(test2.displayGrid());
*/
	}
}
