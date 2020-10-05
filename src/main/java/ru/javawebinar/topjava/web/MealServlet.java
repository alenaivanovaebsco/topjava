package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.InMemoryMealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealToDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealToDao mealDao;

    public MealServlet() {
        super();
        mealDao = new InMemoryMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("meal Get request is to be processed");
        String commandName = request.getParameter("commandName");

        if (commandName == null) {
            request.setAttribute("meals", mealDao.getAllMealsTo());
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if (commandName.equalsIgnoreCase("addMealView")) {
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        } else if (commandName.equalsIgnoreCase("delete")) {
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            mealDao.delete(mealId);
            response.sendRedirect("meals");
        } else if (commandName.equalsIgnoreCase("edit")) {
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            Meal meal = mealDao.getById(mealId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("meal.jsp").forward(request, response);
        } else {
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("meal Post request is to be processed");
        request.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter("commandName");
        if (commandName.equalsIgnoreCase("addMeal")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
            String description = request.getParameter("description");
            int calories = Integer.parseInt(request.getParameter("calories"));

            Meal meal = new Meal();
            meal.setCalories(calories);
            meal.setDescription(description);
            meal.setDateTime(dateTime);
            String mealId = request.getParameter("id");

            if (mealId == null || mealId.isEmpty()) {
                mealDao.save(meal);
            } else {
                meal.setId(Long.parseLong(request.getParameter("id")));
                mealDao.update(meal);
            }
        }
        response.sendRedirect("meals");
    }
}
