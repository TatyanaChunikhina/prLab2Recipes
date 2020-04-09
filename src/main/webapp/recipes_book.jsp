<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin=“anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<center><h1>Книга Рецептов!</h1></center>
<div class="container">
    <h2>Поиск по названию</h2>
    <!--Search Form -->
    <form action="/recipes" method="get" role="form" >
        <div class="form-group col-xs-5">
            <input type="text" name="name" id="name" class="form-control" required="true"
                   placeholder="Введите название рецепта!"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span>Поиск
        </button>
    </form>
</div>
<div class="container">
    <form action="/recipes" method="get" role="form" >
        <button type="submit" class="btn btn-info">
         Показать все рецепты
        </button>
    </form>
</div>
<div class="container">
    <form action="/recipe" method="get" role="form" >
        <button type="submit" class="btn btn-info">
         Добавить рецепт
        </button>
    </form>
</div>
<div class="container">
    <form action="/recipes" method="post"  role="form" id="delForm">
        <c:choose>
        <c:when test="${not empty recipesBook}">

                <c:forEach var="recipe" items="${recipesBook}">
                    <input type="hidden" value="" name="id" id="id"/>
                    <h2>№${recipe.id} ${recipe.name}</h2>
                  <center> <h4>Список ингридиентов: </h4>
                    <div class="col-sm-4">
                    <table class="table table-hover"><!-- стили таблицы -->
                    <c:forEach var="ingredient" items="${recipe.ingredients}">
                            <tr>
                                <td>${ingredient.key.name}</td>
                                <td>${ingredient.value}
                                    ${ingredient.key.numeric}
                                </td>
                            </tr>
                    </c:forEach>
                    </table>
                    </div>
                    </center>
                    <div class="col-sm-4">
                    <div><b>Описание:</b> ${recipe.description}</div>
                    <div><b>Каллории:</b> ${recipe.kalories}</div>
                    </div>
                    <a href="/recipe?id=${recipe.id}">

                    Редактировать
                    </a>
                    <a href="#" onclick="document.getElementById('id').value='${recipe.id}';
                            document.getElementById('delForm').submit();">

                    Удалить
                    </a>
                    <hr>
                </c:forEach>
        </c:when><c:otherwise>
            <br>  </br>
            <div class="alert alert-info">
                Нет рецептов удовлетворяющих параметрам поиска!
            </div>
        </c:otherwise>
        </c:choose>
    </form>
</div>