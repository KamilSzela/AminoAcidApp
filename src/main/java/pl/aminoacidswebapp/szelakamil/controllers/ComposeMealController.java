package pl.aminoacidswebapp.szelakamil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.aminoacidswebapp.szelakamil.model.*;

import java.util.List;

@Controller
public class ComposeMealController {
    @Autowired
    UserRepository repo;
    @Autowired
    MealRepository mealRepository;
    @Autowired
    MealIngredientRepository ingredientRepository;

    public ComposeMealController() {
    }

    @GetMapping(path = "/composeMeal")
    String getPage() {
        return "composeMeal";
    }

    @PostMapping("/newMeal")
    ResponseEntity<String> saveNewMeal(@RequestBody Meal newMeal, Authentication auth) {

        User user = repo.findByEmail(auth.getName()).get(0);
        newMeal.setUser(user);

        Meal savedMeal = mealRepository.save(newMeal);
        System.out.println("Saved Meal date format: " + savedMeal.getDateSaved());
        List<MealIngredient> ingredients = newMeal.getIngredients();
        ingredients.forEach(ingredient -> {
            ingredient.setMeal(savedMeal);
            ingredientRepository.save(ingredient);
        });

        return ResponseEntity.ok("Data received");
    }
}
