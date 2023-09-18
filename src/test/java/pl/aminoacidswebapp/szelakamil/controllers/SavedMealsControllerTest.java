package pl.aminoacidswebapp.szelakamil.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pl.aminoacidswebapp.szelakamil.model.*;
import pl.aminoacidswebapp.szelakamil.service.SavedMealsService;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SavedMealsControllerTest {
    @Test
    void passingTheSameStartANdEndDateShouldResultInBadRequestResponseEntityStatus() {
        //given
        UserRepository userRepo = mock(UserRepository.class);
        given(userRepo.findByEmail("sample@sa.pl")).willReturn(prepareSampleUserList());
        Authentication auth = mock(Authentication.class);
        given(auth.getName()).willReturn("sample@sa.pl");
        SavedMealsController controller = new SavedMealsController();
        controller.setRepo(userRepo);
        //when
        ResponseEntity response = controller.getMealsDetails(prepareParamsListWithTheSameDates(), auth);
        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void passingTheSameLowerAndUpperCaloricityLimitsShouldResultInBadRequestResponseEntityStatus() {
        //given
        UserRepository userRepo = mock(UserRepository.class);
        given(userRepo.findByEmail("sample@sa.pl")).willReturn(prepareSampleUserList());
        Authentication auth = mock(Authentication.class);
        given(auth.getName()).willReturn("sample@sa.pl");
        SavedMealsController controller = new SavedMealsController();
        controller.setRepo(userRepo);
        //when
        ResponseEntity response = controller.getMealsDetails(prepareParamsListWithTheSameCaloricityLimits(), auth);
        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void passingCorrectParametersShouldResultInOkResponseEntityStatus() {
        //given
        UserRepository userRepo = mock(UserRepository.class);
        given(userRepo.findByEmail("sample@sa.pl")).willReturn(prepareSampleUserList());
        Authentication auth = mock(Authentication.class);
        given(auth.getName()).willReturn("sample@sa.pl");
        MealRepository mealRepo = mock(MealRepository.class);
        Date sampleStartDate = Date.from(Instant.parse("1970-01-01T00:00:00.000Z"));
        Date sampleEndDate = Date.from(Instant.parse("2050-12-30T00:00:00.000Z"));
        given(mealRepo.findByCaloricityBetweenAndUserIdAndDateSavedBetween(0,10000,1, sampleStartDate, sampleEndDate))
                .willReturn(prepareSampleMealList());
        SavedMealsService service = new SavedMealsService();

        SavedMealsController controller = new SavedMealsController();
        controller.setRepo(userRepo);
        controller.setMealRepository(mealRepo);
        controller.setService(service);
        //when
        ResponseEntity response = controller.getMealsDetails(prepareCorrectParamsList(), auth);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
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

    private Map<String, String> prepareParamsListWithTheSameDates() {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", "2023-08-30T00:00:00.000Z");
        map.put("endDate", "2023-08-30T00:00:00.000Z");
        return map;
    }
    private Map<String, String> prepareParamsListWithTheSameCaloricityLimits() {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", "2021-08-30T00:00:00.000Z");
        map.put("endDate", "2023-08-30T00:00:00.000Z");
        map.put("lowerCaloricityLimit", "0");
        map.put("upperCaloricityLimit", "0");
        return map;
    }
    private Map<String, String> prepareCorrectParamsList() {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", "1970-01-01T00:00:00.000Z");
        map.put("endDate", "2050-12-30T00:00:00.000Z");
        map.put("lowerCaloricityLimit", "0");
        map.put("upperCaloricityLimit", "10000");
        return map;
    }

    private List<User> prepareSampleUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("sample@sa.pl");
        user.setPassword("");
        users.add(user);
        return users;
    }

}