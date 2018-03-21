
package com.jeu.demineur.Controller;

import com.jeu.demineur.Grille;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.String;


@Controller
public class DemineurController {

    @GetMapping("/demineur")
    public String getGrilleDemineur() {
        Grille test = new Grille();
        test.initialiser();

        return test.toString();
    }
}
