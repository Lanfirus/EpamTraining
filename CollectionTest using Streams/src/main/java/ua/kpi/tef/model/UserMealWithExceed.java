package ua.kpi.tef.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public UserMealWithExceed(UserMeal userMeal, boolean exceeded) {
        this.dateTime = userMeal.getDateTime();
        this.description = userMeal.getDescription();
        this.calories = userMeal.getCalories();
        this.exceed = exceeded;
    }

    @Override
    public String toString() {
        return "Datetime = " + dateTime + " , description = " + description + ", calories = " + calories + ", exceeded = " + exceed;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null && !(obj instanceof UserMealWithExceed)) return false;
        UserMealWithExceed test = (UserMealWithExceed) obj;
        if(this.dateTime.equals(test.dateTime) && this.description.equals(test.description) &&
                this.calories == test.calories && this.exceed == test.exceed) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int prime = 31;
        int hash = prime*calories;
        hash *= description.equals(null) ? 1 : description.hashCode();
        hash *= exceed == true ? 1 : 2;
        return hash;
    }
}
