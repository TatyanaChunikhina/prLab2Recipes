package models;

import java.util.ArrayList;
import java.util.List;

public class RecipesBook {
    private static List<Recipe> recipes=new ArrayList<>();

    static{
        Recipe r=new Recipe(1,"Банановый торт",1200,"Description1");
        r.addIngredient(new Ingredient("Крем",Ingredient.UNITS.gram.toString()),200);
        r.addIngredient(new Ingredient("Масло",Ingredient.UNITS.gram.toString()),100);
        r.addIngredient(new Ingredient("Печенье",Ingredient.UNITS.gram.toString()),150);
        r.addIngredient(new Ingredient("Бананы",Ingredient.UNITS.pc.toString()),2);
        recipes.add(r);
         r=new Recipe(2,"Плов",1000,"Description2");
        r.addIngredient(new Ingredient("Рис",Ingredient.UNITS.pc.toString()),3);
        r.addIngredient(new Ingredient("Морковь",Ingredient.UNITS.pc.toString()),2);
        r.addIngredient(new Ingredient("Подсолнечное масло",Ingredient.UNITS.ml.toString()),10);
        recipes.add(r);
        r=new Recipe(3,"Суп",3000,"Description3");
        r.addIngredient(new Ingredient("Картошка",Ingredient.UNITS.pc.toString()),3);
        r.addIngredient(new Ingredient("Морковь",Ingredient.UNITS.pc.toString()),2);
        r.addIngredient(new Ingredient("Бульон",Ingredient.UNITS.ml.toString()),10);
        r.addIngredient(new Ingredient("Заправка",Ingredient.UNITS.ml.toString()),30);
        recipes.add(r);
        r=new Recipe(4,"Чай с лимоном",300,"Description4");
        r.addIngredient(new Ingredient("Лимон",Ingredient.UNITS.pc.toString()),1);
        r.addIngredient(new Ingredient("чай",Ingredient.UNITS.ml.toString()),15);
        recipes.add(r);
    }
    public static List<Recipe> getInstance(){
        return recipes;
    }
}
