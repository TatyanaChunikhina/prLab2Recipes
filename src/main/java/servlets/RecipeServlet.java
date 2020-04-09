package servlets;

import models.Ingredient;
import models.Recipe;
import models.RecipesBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        name = "RecipeServlet",
        urlPatterns = {"/recipe"}
)
public class RecipeServlet extends HttpServlet {
    RecipesBookService recipesBookService=new RecipesBookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("id");

        String nextJSP = "/recipe_edit.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        if (action != null) {//если редактировать (есть ид)
            Recipe recipe = recipesBookService.getRecipeById(Integer.parseInt(action));

            req.setAttribute("id", recipe.getId());
            req.setAttribute("recipe", recipe);


        } else {
            int id = recipesBookService.getFreeId();
            req.setAttribute("id", id);
        }
        dispatcher.forward(req, resp);


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //-------------------------получение параметров с формы
        String action = req.getParameter("action");
        System.out.println("POST  "+action);
        int id=Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String description=req.getParameter("description");
        String sKalories=req.getParameter("kalories");
        double kalories=0;
        if(sKalories!=null)kalories=Double.parseDouble(sKalories);
        //-----------------
        Map<Ingredient,Double> ing=new HashMap<>();
        for(int i=0;i<100;i++){//100 шт
            String ingredientName=req.getParameter("ingredient"+i);
            String ingredientCount=req.getParameter("ingredientCount"+i);
            String ingredientSelect=req.getParameter("ingredientSelect"+i);
            if(!ingredientName.equals("")&&!ingredientCount.equals("")){
               ing.put(new Ingredient(ingredientName,ingredientSelect),Double.parseDouble(ingredientCount));
            }

        }
        Recipe recipe=new Recipe(id,name,ing,kalories,description);
        switch (action) {
            case "add":
                recipesBookService.addRecipe(recipe);
                break;
            case "edit":
                recipesBookService.editRecipe(id,recipe);
                break;
        }
        resp.sendRedirect("/recipes");

    }

}
