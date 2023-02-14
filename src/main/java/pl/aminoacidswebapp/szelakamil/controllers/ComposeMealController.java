package pl.aminoacidswebapp.szelakamil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComposeMealController {

    public ComposeMealController() {
    }

    @GetMapping(path = "/composeMeal")
    String getPage() {
        return "composeMeal";
    }
}
