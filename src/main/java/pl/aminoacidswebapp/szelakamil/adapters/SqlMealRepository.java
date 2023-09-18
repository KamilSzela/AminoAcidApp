package pl.aminoacidswebapp.szelakamil.adapters;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.aminoacidswebapp.szelakamil.model.Meal;
import pl.aminoacidswebapp.szelakamil.model.MealRepository;

@Repository
public interface SqlMealRepository extends MealRepository, CrudRepository<Meal, Integer> {
}
