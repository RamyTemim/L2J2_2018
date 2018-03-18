
package com.jeu.demineur.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.String;


@org.springframework.stereotype.Controller

public class Controller {
private String Nom;
public Controller() {
}
    public Controller(String Nom){
    this.Nom=Nom;
}
@GetMapping("/Demineur")
    public String App(Model model){
    model.addAttribute("Nom",Nom);
    return "App";
}
}
