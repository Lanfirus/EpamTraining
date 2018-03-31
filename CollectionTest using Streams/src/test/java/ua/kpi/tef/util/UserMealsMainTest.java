package ua.kpi.tef.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsMainTest extends Assert{

    private List<UserMeal> mealList;
    private Map<LocalDate, Integer> expectedTotalCaloriesMap;

    @Before
    public void initialize(){
        mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        expectedTotalCaloriesMap = new HashMap<>();
        expectedTotalCaloriesMap.put(LocalDate.of(2015, Month.MAY, 30), 2000);
        expectedTotalCaloriesMap.put(LocalDate.of(2015, Month.MAY, 31), 2010);
    }

    @Test
    public void testGetFilteredWithExceededCorrect(){
        List<UserMealWithExceed> actual = UserMealsMain.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        List<UserMealWithExceed> expected = new ArrayList<>();
        UserMealWithExceed newMeal = new UserMealWithExceed(mealList.get(0), false);
        expected.add(newMeal);
        newMeal = new UserMealWithExceed(mealList.get(3), true);
        expected.add(newMeal);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFilteredWithExceededInorrectDifferentLists(){
        List<UserMealWithExceed> actual = UserMealsMain.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        List<UserMealWithExceed> expected = new ArrayList<>();
        UserMealWithExceed newMeal = new UserMealWithExceed(mealList.get(0), false);
        expected.add(newMeal);
        newMeal = new UserMealWithExceed(mealList.get(3), false);
        expected.add(newMeal);
        assertNotEquals(expected, actual);
    }
}
