package game.demineur;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

public class LaClasseTest {


    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class DemineurApplicationTests {


        //Déclaration d'un objet de la classe Grille
        int [][] tab= {{0,0,0,0,2,0,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,0,1,1,0,0},
                {0,0,0,0,0,1,0,0},
                {0,1,1,1,0,1,0,0},
                {0,1,2,1,0,1,1,1},
                {0,1,2,1,0,0,0,0}};

        @Test
        public void initialiser() {


        }

        @Test
        public void set_nbMinesAProximite() {
        }

        @Test
        public void decouvrirCase() {
        }

        @Test
        public void decouvrirAutour() {
        }


    }


}