package pl.aminoacidswebapp.szelakamil.model;

public class MealIngredientReadModel {
    Integer id;
    Integer ingredientId;
    String name;
    Double mass;
    Integer mealId;

    public MealIngredientReadModel() {

    }
    public MealIngredientReadModel addIdToModel(Integer id) {
        this.setId(id);
        return this;
    }
    public MealIngredientReadModel addIngredientIdToModel(Integer ingredientId) {
        this.setIngredientId(ingredientId);
        return this;
    }
    public MealIngredientReadModel addNameToModel(String name) {
        this.setName(name);
        return this;
    }
    public MealIngredientReadModel addMassToModel(Double mass) {
        this.setMass(mass);
        return this;
    }
    public MealIngredientReadModel addMealIdToModel(Integer mealId) {
        this.setMealId(mealId);
        return this;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }
}
