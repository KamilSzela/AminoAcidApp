package pl.aminoacidswebapp.szelakamil.service;

import org.junit.jupiter.api.Test;
import pl.aminoacidswebapp.szelakamil.model.Meal;
import pl.aminoacidswebapp.szelakamil.model.MealIngredient;
import pl.aminoacidswebapp.szelakamil.model.MealReadModel;
import pl.aminoacidswebapp.szelakamil.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SavedMealsServiceTest {
    @Test
    void passingEmptyMealListShouldThrowIllegalArgumentException() {
        //given
        SavedMealsService service = new SavedMealsService();
        List<Meal> meals = Collections.emptyList();
        //when + then
        assertThrows(IllegalArgumentException.class, () -> service.convertMealsToMealReadModel(meals));
    }

    @Test
    void passingMealListShouldConvertToMealReadModelListWithEqualSize() {
        //given
        SavedMealsService service = new SavedMealsService();
        List<Meal> meals = prepareSampleMealList();
        //when
        List<MealReadModel> mealsConverted = service.convertMealsToMealReadModel(meals);
        //then
        assertEquals(meals.size(), mealsConverted.size());
        assertEquals(meals.get(0).getId(), mealsConverted.get(0).getId());
        assertEquals(meals.get(0).getCaloricity(), mealsConverted.get(0).getCaloricity());
        assertEquals(meals.get(0).getIngredients().size(), mealsConverted.get(0).getIngredients().size());
    }

    private List<Meal> prepareSampleMealList() {
        List<Meal> meals = new ArrayList<>();
        Meal meal = new Meal();
        Meal meal2 = new Meal();
        meals.add(meal);
        meals.add(meal2);
        meals.forEach(sampleMeal -> {
            sampleMeal.setId((int)Math.floor(Math.random() * 2));
            sampleMeal.setUser(new User());
            sampleMeal.setCaloricity((int)Math.floor(Math.random() * 1001));
            sampleMeal.setDateSaved(new Date());
            sampleMeal.setIngredients(prepareSampleIngredients());
        });
        return meals;
    }

    private List<MealIngredient> prepareSampleIngredients() {
        List<MealIngredient> ingredients = new ArrayList<>();
        Meal meal = new Meal();
        meal.setId(1);
        MealIngredient ingredient = new MealIngredient();
        ingredient.setIngredientId(1);
        ingredient.setName("Fonio");
        ingredient.setMass(100.0);
        ingredient.setId(1);
        ingredient.setMeal(meal);
        ingredients.add(ingredient);
        return ingredients;
    }
}