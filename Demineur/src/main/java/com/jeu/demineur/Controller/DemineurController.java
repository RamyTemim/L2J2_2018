
package com.jeu.demineur.Controller;

import Demineurclasses.Demineur;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demineur")
public class DemineurController {
    /*Argument dans Demineur() :
     * 1 : niveau facile 8 x 8 10 mines
     * 2 : nvieau moyen 16 x 16 40 mines
     * 3 : niveau difficile 30 x 16 99 mines*/

    @GetMapping("/niveauFacile")

    public String Facile() {


        Demineur facile = new Demineur(1);

        return facile.genererGrille();
    }
    @GetMapping("/niveauMoyen")

    public String Moyen() {

        /*Argument dans Demineur() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/

        Demineur moyen = new Demineur(2);

        return moyen.genererGrille();
    }
    @GetMapping("/niveauDifficile")

    public String Difficile() {

        /*Argument dans Demineur() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/

        Demineur difficile = new Demineur(3);

        return difficile.genererGrille();
    }



}




