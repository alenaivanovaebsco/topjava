package ru.javawebinar.topjava.model;


import java.util.List;

public interface MealToDao extends CRUDDao<Meal, Long> {

    List<MealTo> getAllMealsTo();
}
