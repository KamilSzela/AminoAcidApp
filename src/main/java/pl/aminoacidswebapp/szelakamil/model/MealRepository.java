package pl.aminoacidswebapp.szelakamil.model;

import java.util.Date;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);
    List<Meal> findAll();
    List<Meal> findByUserId(int userId);

    List<Meal> findByDateSavedBetween(Date dateStart, Date dateEnd);

    List<Meal> findByCaloricityBetween(int lowerLimit, int upperLimit);
}
