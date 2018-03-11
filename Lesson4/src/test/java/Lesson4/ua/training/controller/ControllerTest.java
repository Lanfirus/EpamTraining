package Lesson4.ua.training.controller;

import Lesson4.ua.training.model.Notebook;
import Lesson4.ua.training.view.ViewConsole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ControllerTest extends Assert{
    ViewConsole view = new ViewConsole();
    Notebook model = new Notebook();
    Controller controller = new Controller(view, model);

    @Before


    @Test
    public void inputParsedToIntegerCorrect() {
        String test = "1";
        int actual = controller.inputParsedToInteger(test);
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inputParsedToIntegerIncorrectDouble() {
        String test = "100500.4";
        controller.inputParsedToInteger(test);
        boolean actual = controller.isInputCorrect();
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inputParsedToIntegerIncorrectCharacters() {
        String test = "Onehundred";
        controller.inputParsedToInteger(test);
        boolean actual = controller.isInputCorrect();
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void inputParsedToIntegerIncorrectNull() {
        String test = "";
        controller.inputParsedToInteger(test);
        boolean actual = controller.isInputCorrect();
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameCorrectSimple() {
        String test = "Петренко";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = true;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameCorrectHyphenSpaceAndApostrophe() {
        String test = "Петренко-Діденко Ул'янова";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = true;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameInorrectDigitsInSurname() {
        String test = "Петр5енко";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = false;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameInorrectEnglishInSurname() {
        String test = "Петрenko";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = false;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameInorrectNotAllowedLettersInSurname() {
        String test = "Петрынко";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = false;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameIcnorrectStartsWithLetterЬ() {
        String test = "Ьетренко";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = false;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameIcnorrectStartsWithSpecialCharacter() {
        String test = "'етренко";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = false;
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void matchInputWithRegexpUkrSurnameIcnorrectEndsWithSpecialCharacter() {
        String test = "Петренко'";
        controller.bundleInitialization(new Locale("ua"));
        List<String> regexpArray = Regexp.returnRegexpStream().collect(Collectors.toList());
        String regexp = controller.getRegexpBundle().getString(regexpArray.get(0));

        boolean actual = controller.matchInputWithRegexp(test, regexp);
        boolean expected = false;
        Assert.assertTrue(expected == actual);
    }

/*    @Test
    public void bundleInitialization() {
    }

    @Test
    public void returnGroupsNames() {
    }

    @Test
    public void matchGroupNames() {
    }

    @Test
    public void createSurnameAndNameRecord() {
    }

    @Test
    public void createFullAddressString() {
    }

    @Test
    public void createAddressMap() {
    }*/
}