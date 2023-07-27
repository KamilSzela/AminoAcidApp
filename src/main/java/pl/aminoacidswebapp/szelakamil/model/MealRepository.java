package pl.aminoacidswebapp.szelakamil.model;

import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);
    List<Meal> findAll();
    List<Meal> findByUserId(int userId);
}
