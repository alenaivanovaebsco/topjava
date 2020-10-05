package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMealDao implements MealToDao {
    private static final Logger log = getLogger(InMemoryMealDao.class);
    private static List<Meal> meals;

    public InMemoryMealDao() {
        meals = MealsUtil.getInstance();
    }

    @Override
    public Long save(Meal meal) {
        log.info("save Meal in meals");

        Meal lastMeal = Collections.max(meals, Comparator.comparing(Meal::getId));
        Long newId = lastMeal.getId() + 1;
        meal.setId(newId);
        meals.add(meal);
        return newId;
    }

    @Override
    public boolean update(Meal meal) {
        log.info("update Meal in meals");

        Long id = meal.getId();
        if (meals.removeIf(m -> m.getId().equals(id))) {
            meals.add(meal);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long mealId) {
        log.info("delete Meal in meals");

        return meals.removeIf(m -> m.getId().equals(mealId));
    }

    @Override
    public Meal getById(Long id) {
        log.info("get Meal in meals");

        return meals.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<MealTo> getAllMealsTo() {
        log.info("get meals with excess");

        return MealsUtil.addedExcess(meals, 2000);
    }
}
