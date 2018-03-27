
package com.jeu.demineur.Controller;

import Demineurclasses.LaClasse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demineur")
public class DemineurController {
    /*Argument dans LaClasse() :
     * 1 : niveau facile 8 x 8 10 mines
     * 2 : nvieau moyen 16 x 16 40 mines
     * 3 : niveau difficile 30 x 16 99 mines*/

    @GetMapping("/niveauFacile")

    public String Facile() {


        LaClasse facile = new LaClasse(1);

        return "NIVEAU FACILE :"+facile.genererGrille();
    }
    @GetMapping("/niveauMoyen")

    public String Moyen() {

        /*Argument dans LaClasse() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/

        LaClasse moyen = new LaClasse(2);

        return "NIVEAU MOYEN:"+moyen.genererGrille();
    }
    @GetMapping("/niveauDifficile")

    public String Difficile() {

        /*Argument dans LaClasse() :
         * 1 : niveau facile 8 x 8 10 mines
         * 2 : nvieau moyen 16 x 16 40 mines
         * 3 : niveau difficile 30 x 16 99 mines*/

        LaClasse difficile = new LaClasse(3);

        return "NIVEAU DIFFICILE :"+difficile.genererGrille();
    }



}




