package Lesson3;

import Lesson3.ua.training.Controller;
import Lesson3.ua.training.Model;
import Lesson3.ua.training.View;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class ModelTest extends Assert{

    Model model = new Model();
    View view = new View();
    Controller test = new Controller(view, model);

    @Test
    public void testSetLanguageCorrectEn() {
        Locale expected = new Locale("en");
        model.setLanguage(expected);
        Locale actual = model.getModelLocale();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetGameModeCorrectInput1() {
        model.setNumberFromUser(1);
        try {
            model.setGameMode();
        }
        catch (NullPointerException e) {}
        int actual = model.getNumberToBeGuessed();
        assertTrue(actual != 0);
    }

    @Test
    public void testSetGameModeCorrectInput2() {
        model.setNumberFromUser(2);
        try {
            model.setGameMode();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {true, false, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSetGameModeIncorrect100500() {
        model.setNumberFromUser(100500);
        try {
            model.setGameMode();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {false, false, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSetLowBoundCorrect() {
        model.setNumberFromUser(100500);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {false, true, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSetHighBoundCorrect() {
        model.setNumberFromUser(0);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(100500);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {false, true, true};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSetHighBoundIncorrectLowerThanLowBound() {
        model.setNumberFromUser(0);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(-10);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {false, true, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSetHighBoundIncorrectNotEnoughElementsInBoundaries() {
        model.setNumberFromUser(0);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(1);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {false, true, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSetHighBoundIncorrectOutOfRangeForInteger() {
        model.setNumberFromUser(Integer.MIN_VALUE);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(Integer.MAX_VALUE);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        boolean[] actual = new boolean[] {model.isSetMode(), model.isSetLowBound(), model.isSetHighBound()};
        boolean[] expected = new boolean[] {false, true, false};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMatchNumberFromUserCorrectEquals() {
        model.setController(test);
        model.setNumberFromUser(model.getNumberToBeGuessed());
        try {
            model.matchNumberFromUser(model.getNumberFromUser());
        }
        catch (NullPointerException e) {}
        boolean actual = test.isStopReading();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchNumberFromUserCorrectLess() {
        model.setNumberFromUser(model.getNumberToBeGuessed() + 2);
        try {
            model.matchNumberFromUser(model.getNumberFromUser());
        }
        catch (NullPointerException e) {}
        boolean actual = (model.getHighBound() == model.getNumberFromUser());
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchNumberFromUserCorrectMore() {
        model.setNumberFromUser(model.getNumberToBeGuessed() - 2);
        try {
            model.matchNumberFromUser(model.getNumberFromUser());
        }
        catch (NullPointerException e) {}
        boolean actual = (model.getLowBound() == model.getNumberFromUser());
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckAndMatchNumberFromUserCorrect() {
        model.setNumberFromUser(-10);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}

        model.setNumberFromUser(15);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(12);
        try {
            model.checkAndMatchNumberFromUser();
        }
        catch (NullPointerException e) {}
        boolean actual = (model.getHighBound() == model.getNumberFromUser());
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckAndMatchNumberFromUserIncorrectLessThanLowBound() {
        boolean actual = false;
        model.setNumberFromUser(-10);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}

        model.setNumberFromUser(15);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(-11);
        try {
            model.checkAndMatchNumberFromUser();
        }
        catch (NullPointerException e) {
            actual = true;
        }
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckAndMatchNumberFromUserIncorrectBiggerThanHighBound() {
        boolean actual = false;
        model.setNumberFromUser(-10);
        try {
            model.setLowBound();
        }
        catch (NullPointerException e) {}

        model.setNumberFromUser(15);
        try {
            model.setHighBound();
        }
        catch (NullPointerException e) {}
        model.setNumberFromUser(20);
        try {
            model.checkAndMatchNumberFromUser();
        }
        catch (NullPointerException e) {
            actual = true;
        }
        boolean expected = true;
        assertEquals(expected, actual);
    }
}
