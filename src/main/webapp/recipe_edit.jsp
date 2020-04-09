<%@ page import="models.Recipe" %>
<%@ page import="models.Ingredient" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.io.IOException" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    function unShowIngredient(i) {
        document.getElementById('divIngredient'+i).hidden = true;
        document.getElementById('ingredient' + i).value = '';
        document.getElementById('ingredientCount'+ i).value = '';

    }
    function showIngredient() {
        i=document.getElementById('count').value;
        if(i>=100){alert('В рецепте не может быть больше 100 ингредиентов!'); return;}
        document.getElementById('divIngredient'+i).hidden = false;
        document.getElementById('ingredient' + i).value = '';
        document.getElementById('ingredientCount'+ i).value = '';
        document.getElementById('addIngredient').onclick = showIngredient;
        i++;
        document.getElementById('count').value=''+i;

    }
</script>

<div class="container">
    <form action="/recipe" method="post" role="form" >
        <input type="hidden" id="action" name="action" value="add"/>
        <%int ingredientCount=0; %>
        <%int MAX_INGREDIENT_COUNT=100;%>
        <input type="hidden" id="count" value="0"/>
        <%!
            void showIngredient(int i, String name, Double count, String numeric, JspWriter out) throws IOException {
                out.println("<script>\n" +
                        " document.getElementById('divIngredient" + i + "').hidden=false;"+
                        " document.getElementById('ingredient" + i + "').value='"+name+"';" +
                        " document.getElementById('ingredientCount" + i + "').value='"+count+"';" +
                        " document.getElementById('ingredientSelect" + i + "').value='"+numeric+"';" +//!
                        " </script>");
            }

        %>

        <%!
            void createIngredient(int i, JspWriter out) throws IOException {
                out.println(
                        "<div class=\"form-row\"  name=\"divIngredient" + i + "\" id=\"divIngredient" + i + "\" hidden=\"true\">\n" +
                                "<h6>Ингридиент " + (i + 1) + ":</h6>" +
                                "    <div class=\"form-group col-md-2\" >" +
                                "            <input type=\"text\" name=\"ingredient" + i + "\" id=\"ingredient" + i + "\" class=\"form-control\" \n" +
                                "                  placeholder=\"Название\" />" +
                                "</div>\n" +
                                "    <div class=\"form-group col-md-2\">" +
                                "            <input type=\"number\" name=\"ingredientCount" + i + "\" id=\"ingredientCount" + i + "\" class=\"form-control\"\n" +
                                "                   placeholder=\"Колличество\"/>" +
                                "</div>\n" +
                                "    <div class=\"form-group col-md-2\">");

                out.println("<select name=\"ingredientSelect" + i + "\" class=\"form-control\">\n");
                Ingredient.UNITS[] units = Ingredient.UNITS.values();
                for (int j = 0; j < units.length; j++)
                    out.println("        <option value='" + units[j].toString() + "'>" + units[j] + "</option>\n");
                out.println("</select>");
                out.println("</div><div class=\"form-group col-md-2\"><button type=\"button\" id=\"remove\" class=\"btn btn-danger\"\n" +
                        "             onclick=\"unShowIngredient("+i+")\">\n" +
                        "         -\n" +
                        "     </button></div>");
                out.println("</div>");
            }
        %>
        <label for="id" class="control-label col-xs-4">Id:</label>
        <input type="number" name="id" id="id" class="form-control" value="${id}" readonly />

        <label for="name" class="control-label col-xs-4">Название:</label>
        <input type="text" name="name" id="name" class="form-control" value="${recipe.name}"
               required="true"/>

        <label for="description" class="control-label col-xs-4">Описание:</label>
        <textarea name="description" id="description" class="form-control"
                  required="true">${recipe.description}</textarea>

        <label for="kalories" class="control-label col-xs-4">Каллории:</label>
        <input type="number" name="kalories" id="kalories" class="form-control" value="${recipe.kalories}"
               min="1" max="100000"/>
        <h4>Ингридиенты:</h4>
        <%
            for(int i=0;i<MAX_INGREDIENT_COUNT;i++)
                createIngredient(i,out);//вызов функции
        %>
        <%
            Recipe recipe= (Recipe) request.getAttribute("recipe");
            if(recipe!=null) {
                out.println("<script> document.getElementById('action').value=\"edit\"; </script>");
                int i = 0;
                Set<Map.Entry<Ingredient, Double>> ing = recipe.getIngredients().entrySet();

                for (Map.Entry<Ingredient, Double> ingredient : ing) {
                    showIngredient(i,ingredient.getKey().getName(),ingredient.getValue(),ingredient.getKey().getNumeric(),out);
                    i++;
                    ingredientCount++;//считаем колличество
                }
                out.println("<script>document.getElementById('count').value='"+ingredientCount+"';</script>");
            }
        %>

        <button type="button" id="addIngredient" class="btn btn-primary"
                onclick="showIngredient(<%=ingredientCount%>)">
            +</button>


        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">Готово!</button>



    </form>
</div>