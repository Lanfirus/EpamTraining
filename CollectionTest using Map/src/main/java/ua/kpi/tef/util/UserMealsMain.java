package ua.kpi.tef.util;

import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsMain {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> finalList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        System.out.println(finalList);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> totalCaloriesPerDay = getDailyCaloriesMap(mealList);
        List<UserMealWithExceed> mealListWithExceeded = getListWithExceeded(totalCaloriesPerDay, mealList, startTime, endTime, caloriesPerDay);
        return mealListWithExceeded;
    }

    protected static Map<LocalDate, Integer> getDailyCaloriesMap(List<UserMeal> mealList){
        Map<LocalDate, Integer> totalCaloriesPerDay = new HashMap<>();
        for (int i = 0; i < mealList.size(); i++) {
            LocalDate date = mealList.get(i).getDateTime().toLocalDate();
            if (totalCaloriesPerDay.containsKey(date)) {
                int caloriesPerMeal = totalCaloriesPerDay.get(date) + mealList.get(i).getCalories();
                totalCaloriesPerDay.put(date, caloriesPerMeal);
            }
            else {
                totalCaloriesPerDay.put(date, mealList.get(i).getCalories());
            }
        }
        return totalCaloriesPerDay;
    }

    protected static List<UserMealWithExceed> getListWithExceeded(Map<LocalDate, Integer> totalCaloriesPerDay, List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay){
        List<UserMealWithExceed> temporaryArrayWithExceeded = new ArrayList<>();
        for (int i = 0; i < mealList.size(); i++) {
            if (TimeUtil.isBetween(mealList.get(i).getDateTime().toLocalTime(), startTime, endTime)) {
                if (totalCaloriesPerDay.get(mealList.get(i).getDateTime().toLocalDate()) <= caloriesPerDay) {
                    UserMealWithExceed newMeal = new UserMealWithExceed(mealList.get(i), false);
                    temporaryArrayWithExceeded.add(newMeal);
                }
                else {
                    UserMealWithExceed newMeal = new UserMealWithExceed(mealList.get(i), true);
                    temporaryArrayWithExceeded.add(newMeal);

                }
            }
        }
        return temporaryArrayWithExceeded;
    }
}
