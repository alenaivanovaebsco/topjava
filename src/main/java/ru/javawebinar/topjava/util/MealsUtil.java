package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class MealsUtil {

    private static final AtomicBoolean DO_INSTANCE_EXIST = new AtomicBoolean(false);
    private static final Lock INSTANCE_LOCK = new ReentrantLock();
    private static List<Meal> meals;


    public static List<Meal> getInstance() {
        if (!DO_INSTANCE_EXIST.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (meals == null) {
                    meals = new ArrayList<>();
                    meals.add(new Meal((long) 1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
                    meals.add(new Meal((long) 2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
                    meals.add(new Meal((long) 3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
                    meals.add(new Meal((long) 4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
                    meals.add(new Meal((long) 5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
                    meals.add(new Meal((long) 6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
                    meals.add(new Meal((long) 7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));

                    DO_INSTANCE_EXIST.set(true);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return meals;
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<MealTo> addedExcess(List<Meal> meals, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );

        return meals.stream()
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
