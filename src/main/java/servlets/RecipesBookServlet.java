package servlets;

import models.Recipe;
import models.RecipesBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "RecipesBookServlet",
        urlPatterns = {"/recipes"}
)

public class RecipesBookServlet extends HttpServlet {
    RecipesBookService recipesBookService=new RecipesBookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("name");
        //
        // System.out.println("Get"+action);
        List<Recipe> result;
        if (action != null) {
            action = new String(action.getBytes("ISO-8859-1"),"UTF-8");
            result = recipesBookService.getRecipesByName(action);

        } else {
           result = recipesBookService.getAllRecipes();
        }
        String nextJSP = "/recipes_book.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("recipesBook", result);
        dispatcher.forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id=req.getParameter("id");
        System.out.println("POST "+ id);
        if (id!=null){
            recipesBookService.removeRecipe(Integer.parseInt(id));
            req.removeAttribute("id");
        }
        resp.sendRedirect("/recipes");
    }
}
