package pl.aminoacidswebapp.szelakamil.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.aminoacidswebapp.szelakamil.model.*;

import java.time.Instant;
import java.util.*;

@Controller
public class SavedMealsController {

    @Autowired
    UserRepository repo;
    @Autowired
    MealRepository mealRepository;
    Gson gson = new Gson();
    @RequestMapping("/savedMeals")
    String returnHomePage(Model model, Authentication auth) {
        List<Meal> meals = mealRepository.findByUserId(repo.findByEmail(auth.getName()).get(0).getId());
        model.addAttribute("Meals", meals);
        return "savedMeals";
    }
    @RequestMapping("/getMeals")
    ResponseEntity<List<MealReadModel>> getMealsDetails(@RequestParam Map<String, String> allParams, Authentication auth) {
        List<MealReadModel> meals = new ArrayList<>();
        int userId = repo.findByEmail(auth.getName()).get(0).getId();
        try{
            Instant startDateInstant = Instant.parse(allParams.get("startDate"));
            Instant endDateInstant = Instant.parse(allParams.get("endDate"));
            Date startDate = Date.from(startDateInstant);
            Date endDate = Date.from(endDateInstant);
            Integer lowerCaloricity = Integer.valueOf(allParams.get("lowerCaloricityLimit"));
            Integer upperCaloricity = Integer.valueOf(allParams.get("upperCaloricityLimit"));

            List<Meal> mealsByAll = mealRepository.findByCaloricityBetweenAndUserIdAndDateSavedBetween(lowerCaloricity, upperCaloricity, userId, startDate, endDate);

            List<MealReadModel> mealsReadModel = new ArrayList<>();

            mealsByAll.forEach(meal -> {
                List<MealIngredientReadModel> ingredientsReadModelList = new ArrayList<>();
                MealReadModel mealModel = new MealReadModel();
                mealModel.setId(meal.getId());
                mealModel.setCaloricity(meal.getCaloricity());
                mealModel.setDateSaved(meal.getDateSaved());

                meal.getIngredients().forEach(ingredient -> {
                    MealIngredientReadModel modelIngredient = new MealIngredientReadModel();
                    modelIngredient
                            .addIdToModel(ingredient.getId())
                            .addMealIdToModel(ingredient.getMeal().getId())
                            .addIngredientIdToModel(ingredient.getIngredientId())
                            .addMassToModel(ingredient.getMass())
                            .addNameToModel(ingredient.getName());
                    ingredientsReadModelList.add(modelIngredient);
                });
                mealModel.setIngredients(ingredientsReadModelList);
                mealsReadModel.add(mealModel);
            });

            meals.addAll(mealsReadModel);

            return ResponseEntity.status(200).body(meals);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }
}
