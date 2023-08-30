package pl.aminoacidswebapp.szelakamil.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pl.aminoacidswebapp.szelakamil.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ComposeMealControllerTest {
    @Test
    void passingMealWithNoIngredientsShouldCauseWithBadRequestResponseStatus() {
        //given
        Authentication auth = mock(Authentication.class);
        ComposeMealController controller = new ComposeMealController();
        //when
        ResponseEntity response = controller.saveNewMeal(createSampleEmptyMeal(), auth);
        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Posiłek powinien zawierać przynajmniej jeden składnik!",
                response.getBody().toString());
    }
    @Test
    void passingNonEmptyMealShouldCauseCreatedResponseStatus() {
        //given
        Authentication auth = mock(Authentication.class);
        given(auth.getName()).willReturn("admin@ad.pl");
        UserRepository userRepo = mock(UserRepository.class);
        given(userRepo.findByEmail("admin@ad.pl")).willReturn(createSampleUserList());
        MealRepository mealRepo = mock(MealRepository.class);
        given(mealRepo.save(createSampleMeal())).willReturn(createSampleMeal());
        MealIngredientRepository ingredientRepo = mock(MealIngredientRepository.class);
        given(ingredientRepo.save(createSampleIngredient())).willReturn(createSampleIngredient());
        ComposeMealController controller = new ComposeMealController();
        controller.setRepo(userRepo);
        controller.setMealRepository(mealRepo);
        controller.setIngredientRepository(ingredientRepo);
        //when
        ResponseEntity response = controller.saveNewMeal(createSampleMeal(), auth);
        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Data received",
                response.getBody().toString());
    }
    List<User> createSampleUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        users.add(user);
        return users;
    }

    Meal createSampleMeal() {
        Meal meal = new Meal();
        meal.setUser(createSampleUserList().get(0));
        meal.setId(1);
        meal.setCaloricity(100);
        MealIngredient ingredient = new MealIngredient();
        ingredient.setIngredientId(1);
        ingredient.setMass(100.0);
        ingredient.setId(1);
        ingredient.setName("Fonio");
        ingredient.setMeal(new Meal());
        List<MealIngredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        meal.setIngredients(ingredients);
        return meal;
    }

    Meal createSampleEmptyMeal() {
        Meal meal = new Meal();
        meal.setIngredients(Collections.emptyList());
        return meal;
    }

    MealIngredient createSampleIngredient() {
        MealIngredient ingredient = new MealIngredient();
        ingredient.setIngredientId(1);
        ingredient.setMass(100.0);
        ingredient.setId(1);
        ingredient.setName("Fonio");
        ingredient.setMeal(new Meal());
        return ingredient;
    }
}
