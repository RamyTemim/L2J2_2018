
package com.jeu.demineur.Controller;

import Demineurclasses.Grille;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.lang.String;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemineurController {

    @RequestMapping("/demineur")

    public String home(){
        Grille test = new Grille();

        test.initialiser();
 
        return test.toString();
    }
}




