package pl.aminoacidswebapp.szelakamil.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.aminoacidswebapp.szelakamil.model.*;
import pl.aminoacidswebapp.szelakamil.service.SavedMealsService;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.*;

@Controller
public class SavedMealsController {

    @Autowired
    UserRepository repo;
    @Autowired
    MealRepository mealRepository;
    @Autowired
    SavedMealsService service;
    @RequestMapping("/savedMeals")
    String returnHomePage(Model model, Authentication auth) {
        List<Meal> meals = mealRepository.findByUserId(repo.findByEmail(auth.getName()).get(0).getId());
        model.addAttribute("Meals", meals);
        return "savedMeals";
    }
    @RequestMapping("/getMeals")
    ResponseEntity<List<MealReadModel>> getMealsDetails(@RequestParam Map<String, String> allParams, Authentication auth) {

        int userId = repo.findByEmail(auth.getName()).get(0).getId();
        try{
            Instant startDateInstant = Instant.parse(allParams.get("startDate"));
            Instant endDateInstant = Instant.parse(allParams.get("endDate"));
            Date startDate = Date.from(startDateInstant);
            Date endDate = Date.from(endDateInstant);
            if(startDate.equals(endDate)) {
                throw new IllegalArgumentException("Data początkowa i końcowa nie mogą być takie same.");
            }
            int lowerCaloricity = Integer.valueOf(allParams.get("lowerCaloricityLimit"));
            int upperCaloricity = Integer.parseInt(allParams.get("upperCaloricityLimit"));
            if(lowerCaloricity == upperCaloricity) {
                throw new IllegalArgumentException("Dolny i górny limit kaloryczności nie mogą być identyczne.");
            }

            List<Meal> mealsByAll = mealRepository.findByCaloricityBetweenAndUserIdAndDateSavedBetween(lowerCaloricity, upperCaloricity, userId, startDate, endDate);
            List<MealReadModel> mealsReadModel = service.convertMealsToMealReadModel(mealsByAll);

            return ResponseEntity.status(200).body(mealsReadModel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }

    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    public void setMealRepository(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public void setService(SavedMealsService service) {
        this.service = service;
    }
}
