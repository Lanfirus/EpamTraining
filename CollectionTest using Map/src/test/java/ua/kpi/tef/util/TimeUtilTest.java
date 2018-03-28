package ua.kpi.tef.util;

import org.junit.Test;

import java.time.LocalTime;

public class TimeUtilTest extends org.junit.Assert{

    @Test
   public void testIsBetweenCorrect(){
        LocalTime startTime = LocalTime.of(12,0);
        LocalTime endTime = LocalTime.of(22,0);
        LocalTime timeToCheck = LocalTime.of(15,0);
        boolean actuals = TimeUtil.isBetween(timeToCheck, startTime, endTime);
        boolean expected = true;
        assertEquals(expected, actuals);
    }

    @Test
    public void testIsBetweenIncorrectTooEarly(){
        LocalTime startTime = LocalTime.of(12,0);
        LocalTime endTime = LocalTime.of(22,0);
        LocalTime timeToCheck = LocalTime.of(10,0);
        boolean actuals = TimeUtil.isBetween(timeToCheck, startTime, endTime);
        boolean expected = false;
        assertEquals(expected, actuals);
    }

    @Test
    public void testIsBetweenIncorrectTooLate(){
        LocalTime startTime = LocalTime.of(12,0);
        LocalTime endTime = LocalTime.of(22,0);
        LocalTime timeToCheck = LocalTime.of(23,0);
        boolean actuals = TimeUtil.isBetween(timeToCheck, startTime, endTime);
        boolean expected = false;
        assertEquals(expected, actuals);
    }

    @Test
    public void testIsBetweenIncorrectNullToCheck(){
        LocalTime startTime = LocalTime.of(12,0);
        LocalTime endTime = LocalTime.of(22,0);
        LocalTime timeToCheck = null;
        boolean actuals;
        try {
            actuals = TimeUtil.isBetween(timeToCheck, startTime, endTime);
        }
        catch(NullPointerException e){
            actuals = false;
        }
        boolean expected = false;
        assertEquals(expected, actuals);
    }
}
