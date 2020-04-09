package models;

import java.util.HashMap;
import java.util.Map;

public class Recipe{
    int id;
    String name;//
    Map<Ingredient,Double> ingredients = new HashMap<>();
    double kalories;
    String description;

    public Recipe(int id, String name, Map<Ingredient, Double> ingredients, String description) {
        this.id = id;
        this.name = name;
        this.kalories = 0;
        this.ingredients = ingredients;
        this.description = description;
    }

    public Recipe(int id, String name, double kalories, String description) {
        this.id = id;
        this.name = name;
        this.kalories = kalories;
        this.description = description;
    }

    public Recipe(int id, String name, Map<Ingredient, Double> ingredients, double kalories, String description) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.kalories = kalories;
        this.description = description;
    }

    public Recipe(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.kalories = 0;
    }

    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Ingredient, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKalories() {
        return kalories;
    }

    public void setKalories(double kalories) {
        this.kalories = kalories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void addIngredient(Ingredient ingredient,double count){
        ingredients.put(ingredient,count);
    }
    public void deleteIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }
    public void changeIngredient(Ingredient ingredient,double count){
        ingredients.put(ingredient,count);
    }

}
