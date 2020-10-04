package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InMemoryMealDao implements MealToDao {

    private static List<Meal> meals;

    public InMemoryMealDao() {
        meals = MealsUtil.getInstance();
    }

    @Override
    public Long save(Meal meal) {
        Meal lastMeal = Collections.max(meals, Comparator.comparing(Meal::getId));
        Long newId = lastMeal.getId()+1;
        meal.setId(newId);
        meals.add(meal);
        return newId;
    }

    @Override
    public boolean update(Meal mealTo) {
        return false;
    }

    @Override
    public boolean delete(Long mealId) {
        System.out.println(mealId);
        return meals.removeIf(m -> m.getId().equals(mealId));
    }

    @Override
    public Meal getById(Long id) {
        return null;
    }

    @Override
    public List<MealTo> getAllMealsTo() {
        return MealsUtil.addedExcess(meals, 2000);
    }
}
