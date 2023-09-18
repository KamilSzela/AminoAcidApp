package pl.aminoacidswebapp.szelakamil.model;

import java.util.Date;
import java.util.List;

public class MealReadModel {
    Integer id;
    Integer userId;
    Date dateSaved;
    List<MealIngredientReadModel> ingredients;
    Integer caloricity;

    public Integer getCaloricity() {
        return caloricity;
    }

    public void setCaloricity(Integer caloricity) {
        this.caloricity = caloricity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(Date dateSaved) {
        this.dateSaved = dateSaved;
    }

    public List<MealIngredientReadModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<MealIngredientReadModel> ingredients) {
        this.ingredients = ingredients;
    }
}
