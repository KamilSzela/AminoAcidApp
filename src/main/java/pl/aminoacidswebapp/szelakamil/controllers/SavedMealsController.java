package pl.aminoacidswebapp.szelakamil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.aminoacidswebapp.szelakamil.model.Meal;
import pl.aminoacidswebapp.szelakamil.model.MealRepository;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

import java.util.List;

@Controller
public class SavedMealsController {

    @Autowired
    UserRepository repo;
    @Autowired
    MealRepository mealRepository;
    @RequestMapping("/savedMeals")
    String returnHomePage(Model model, Authentication auth) {
        List<Meal> meals = mealRepository.findByUserId(repo.findByEmail(auth.getName()).get(0).getId());
        model.addAttribute("Meals", meals);
        return "savedMeals";
    }
}
