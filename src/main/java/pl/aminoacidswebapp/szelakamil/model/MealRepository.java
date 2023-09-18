package pl.aminoacidswebapp.szelakamil.model;

import java.util.Date;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);
    List<Meal> findAll();
    List<Meal> findByUserId(int userId);

    List<Meal> findByDateSavedBetween(Date dateStart, Date dateEnd);

    List<Meal> findByCaloricityBetweenAndUserId(int lowerLimit, int upperLimit, int userId);
    List<Meal> findByCaloricityBetweenAndUserIdAndDateSavedBetween(int lowerLimit, int upperLimit, int userId, Date dateStart, Date dateEnd);
}
