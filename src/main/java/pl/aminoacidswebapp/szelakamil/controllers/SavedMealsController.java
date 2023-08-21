package pl.aminoacidswebapp.szelakamil.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.aminoacidswebapp.szelakamil.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
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
    ResponseEntity<List<MealReadModel>> getMealsDetails(@RequestParam Map<String, String> allParams) {
        List<MealReadModel> meals = new ArrayList<>();
        allParams.forEach((name, param) -> {
            System.out.println(name);
            System.out.println(param);
        });
        try{
            Instant startDateInstant = Instant.parse(allParams.get("startDate"));
            Instant endDateInstant = Instant.parse(allParams.get("endDate"));
            Date startDate = Date.from(startDateInstant);
            Date endDate = Date.from(endDateInstant);
            Integer lowerCaloricity = Integer.valueOf(allParams.get("lowerCaloricityLimit"));
            Integer upperCaloricity = Integer.valueOf(allParams.get("upperCaloricityLimit"));
            System.out.println("From instant: " + startDate);
            System.out.println("lowerCaloricity: " + lowerCaloricity);
            //Date beginDate =  Date.parse(allParams.get("startDate"));
            //Date beginDate = new SimpleDateFormat("yyyy-MM-dd").parse("Thu Jan 01 01:00:00 CET 1970");
            List<Meal> mealsByCaloricity = mealRepository.findByCaloricityBetween(lowerCaloricity, upperCaloricity);
            List<MealReadModel> mealsReadModel = new ArrayList<>();


            mealsByCaloricity.forEach(meal -> {
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
//            mealsByCaloricity.forEach(meal -> {
//                meal.setUser(new User());
//                meal.getIngredients().forEach(ingredient -> {
//                    ingredient.setMeal(new Meal());
//                });
//            });
            meals.addAll(mealsReadModel);

            return ResponseEntity.status(200).body(meals);
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }

        return ResponseEntity.ok(meals);
        //List<Meal> mealsByCaloricity = mealRepository.findByCaloricityBetween(lowerCaloricityLimit, upperCaloricityLimit);

    }
}
