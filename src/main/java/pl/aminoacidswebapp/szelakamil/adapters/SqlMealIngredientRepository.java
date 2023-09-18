package pl.aminoacidswebapp.szelakamil.adapters;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.aminoacidswebapp.szelakamil.model.MealIngredient;
import pl.aminoacidswebapp.szelakamil.model.MealIngredientRepository;

@Repository
public interface SqlMealIngredientRepository extends MealIngredientRepository, CrudRepository<MealIngredient, Integer> {
}
