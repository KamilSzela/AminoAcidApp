package pl.aminoacidswebapp.szelakamil.service;

import org.springframework.stereotype.Service;
import pl.aminoacidswebapp.szelakamil.model.Meal;
import pl.aminoacidswebapp.szelakamil.model.MealIngredientReadModel;
import pl.aminoacidswebapp.szelakamil.model.MealReadModel;

import java.util.ArrayList;
import java.util.List;
@Service
public class SavedMealsService {

    public List<MealReadModel> convertMealsToMealReadModel(List<Meal> meals) throws IllegalArgumentException {

        if(meals.isEmpty()) throw new IllegalArgumentException();
        else {
            List<MealReadModel> mealsReadModel = new ArrayList<>();

            meals.forEach(meal -> {
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
            return mealsReadModel;
        }
    }
}
