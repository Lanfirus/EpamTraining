package Lesson3;

import Lesson3.ua.training.Controller;
import Lesson3.ua.training.Model;
import Lesson3.ua.training.View;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;


public class ControllerTest extends Assert {

    Model model = new Model();
    View view = new View();
    Controller test = new Controller(view, model);

    @Test
    public void testInputCheckCorrect() {
        test.inputCheck("10");
        int actual = model.getNumberFromUser();
        int expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    public void testInputCheckIncorrectEmpty() {
        test.inputCheck("");
        int actual = model.getNumberFromUser();
        String actualStr = test.getBundle().getString("input.incorrect");
        int expected = 0;
        String expectedStr = "You have used incorrect input: %s. Correct input is a number from -2147483648 to 2147483647.";
        assertEquals(expectedStr + expected, actualStr + actual);
    }

    @Test
    public void testInputCheckIncorrectNull() {
        test.inputCheck(null);
        int actual = model.getNumberFromUser();
        String actualStr = test.getBundle().getString("input.incorrect");
        int expected = 0;
        String expectedStr = "You have used incorrect input: %s. Correct input is a number from -2147483648 to 2147483647.";
        assertEquals(expectedStr + expected, actualStr + actual);
    }

    @Test
    public void testInputCheckTooLongNumber() {
        test.inputCheck("100500100500");
        int actual = model.getNumberFromUser();
        String actualStr = test.getBundle().getString("input.incorrect");
        int expected = 0;
        String expectedStr = "You have used incorrect input: %s. Correct input is a number from -2147483648 to 2147483647.";
        assertEquals(expectedStr + expected, actualStr + actual);
    }

    @Test
    public void testInputCheckIncorrectString() {
        test.inputCheck("onehundredthousands");
        int actual = model.getNumberFromUser();
        String actualStr = test.getBundle().getString("input.incorrect");
        int expected = 0;
        String expectedStr = "You have used incorrect input: %s. Correct input is a number from -2147483648 to 2147483647.";
        assertEquals(expectedStr + expected, actualStr + actual);
    }

    @Test
    public void testInputCheckIncorrectSeveralNumbers() {
        test.inputCheck("100500 100500");
        int actual = model.getNumberFromUser();
        String actualStr = test.getBundle().getString("input.incorrect");
        int expected = 0;
        String expectedStr = "You have used incorrect input: %s. Correct input is a number from -2147483648 to 2147483647.";
        assertEquals(expectedStr + expected, actualStr + actual);
    }

    @Test
    public void testSetLanguageEn() {
        test.setLanguage("english");
        Locale actual = new Locale("en");
        Locale expected = test.getLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLanguageRu() {
        test.setLanguage("Russian");
        Locale actual = new Locale("ru");
        Locale expected = test.getLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLanguageIncorrectString() {
        test.setLanguage("Russia");
        Locale actual = null;
        Locale expected = test.getLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLanguageIncorrectEmpty() {
        test.setLanguage("");
        Locale actual = null;
        Locale expected = test.getLocale();
        assertEquals(expected, actual);
    }

}
