package controller;

import lombok.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sudoku")
@Controller
class FirstController {

    private String appName;

    public FirstController(String appName) {
        this.appName = appName;
    }

    @GetMapping("/generateGrid")
    public String homePage(Model model) {
        model.addAttribute( "appName", appName );
        return "home";

    }

   /* @GetMapping("/solveGrille")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";

    }*/

}
