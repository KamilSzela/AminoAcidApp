package pl.aminoacidswebapp.szelakamil.model;

import java.util.List;

public interface MealIngredientRepository {
    MealIngredient save(MealIngredient ingredient);
    List<MealIngredient> findByMealId(int mealId);
}
