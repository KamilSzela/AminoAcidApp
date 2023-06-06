package pl.aminoacidswebapp.szelakamil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SavedMealsController {

    @RequestMapping("/savedMeals")
    String returnHomePage() {
        return "savedMeals";
    }
}
