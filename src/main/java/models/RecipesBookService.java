package models;

import java.util.ArrayList;
import java.util.List;

public class RecipesBookService {
    List<Recipe> recipesBook=RecipesBook.getInstance();

    public List <Recipe> getAllRecipes(){
        return recipesBook;
    }

    public List<Recipe> getRecipesByName(String name) {
        name=name.toLowerCase();
        List<Recipe> recipesByName=new ArrayList<>();
        for(Recipe r:recipesBook){
            String tmp=r.getName().toLowerCase();
            if(tmp.equals(name))
                recipesByName.add(r);
        }
        return recipesByName;
    }

    public Recipe getRecipeById(int id) {
        for(Recipe r:recipesBook){
            if(r.getId()==id)
                return r;
        } return null;
    }

    public int getFreeId() {
        int id = recipesBook.get(recipesBook.size()-1).getId();
        boolean flag=true;
        do{
            flag=true;
            id++;
        for(Recipe r:recipesBook){
            if(r.getId()== id) {
                flag = false;
                break;
            }
        }
        } while (flag == false);
        return id;
    }

    public void removeRecipe(int id) {
        for(int i=0;i<recipesBook.size();i++){
            if(recipesBook.get(i).getId()==id){recipesBook.remove(recipesBook.get(i));break;}
        }

    }

    public boolean addRecipe(Recipe recipe) {
        int id= recipe.getId();
        for(Recipe r:recipesBook){
            if(r.getId()==id) {
               return false;
            }
        }
        recipesBook.add(recipe);
        return true;
    }

    public boolean editRecipe(int idOld, Recipe recipe) {

        for(int i=0;i<recipesBook.size();i++){
            if(recipesBook.get(i).getId()==idOld){
                recipesBook.remove(recipesBook.get(i));
                recipesBook.add(i,recipe);
                return true;}
        }
        return false;
    }
}
