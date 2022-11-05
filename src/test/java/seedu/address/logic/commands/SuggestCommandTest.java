package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.JUSTIN;
import static seedu.address.testutil.TypicalPersons.MONDAY_NOON_DAY_TIME_IN_WEEK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DayTimeInWeek;
import seedu.address.model.person.Keyword;
import seedu.address.model.person.PersonSuggestionPredicate;

public class SuggestCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        keywordSet.add(new Keyword("victoria"));
        keywordSet.add(new Keyword("12345"));
        dayTimeInWeekSet.add(new DayTimeInWeek("mon@2359"));
        dayTimeInWeekSet.add(new DayTimeInWeek("fri@0000"));

        Set<Keyword> keywordSet1 = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet1 = new HashSet<>();
        keywordSet1.add(new Keyword("victoria"));
        keywordSet1.add(new Keyword("123456"));
        dayTimeInWeekSet1.add(new DayTimeInWeek("mon@2359"));
        dayTimeInWeekSet1.add(new DayTimeInWeek("fri@0001"));

        PersonSuggestionPredicate firstPredicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        PersonSuggestionPredicate secondPredicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet1, keywordSet1);

        SuggestCommand c1 = new SuggestCommand(firstPredicate);
        SuggestCommand c2 = new SuggestCommand(secondPredicate);

        // same object -> returns true
        assertTrue(c1.equals(c1));

        // same values -> returns true
        SuggestCommand c1Copy = new SuggestCommand(firstPredicate);
        assertTrue(c1.equals(c1Copy));

        // different types -> returns false
        assertFalse(c1.equals(1));

        // null -> returns false
        assertFalse(c1.equals(null));

        // different person -> returns false
        assertFalse(c1.equals(c2));
    }

    @Test
    public void execute_zeroKeywords_allPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 10);
        PersonSuggestionPredicate predicate = new PersonSuggestionPredicate(new HashSet<>(), new HashSet<>());
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON, IDA, JUSTIN),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {

        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        keywordSet.add(new Keyword("1"));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 8);
        PersonSuggestionPredicate predicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, IDA, JUSTIN),
                model.getFilteredPersonList());

    }

    @Test
    public void execute_keywordForCountry_multiplePersonsFound() {

        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        keywordSet.add(new Keyword("Singapore"));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 6);
        PersonSuggestionPredicate predicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        System.out.println(expectedModel.getFilteredPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, HOON, IDA, JUSTIN),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_keywordForMinecraftServerAndGameType_multiplePersonsFound() {
        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        keywordSet.add(new Keyword("MinePlex"));
        keywordSet.add(new Keyword("server@111.222.333"));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonSuggestionPredicate predicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_dayTimeInWeek_multiplePersonsFound() {

        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        dayTimeInWeekSet.add(new DayTimeInWeek(MONDAY_NOON_DAY_TIME_IN_WEEK));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonSuggestionPredicate predicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_dayTimeInWeekAndKeyword_onePersonFound() {

        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        keywordSet.add(new Keyword("Singapore"));
        dayTimeInWeekSet.add(new DayTimeInWeek("mon@2330"));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonSuggestionPredicate predicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_dayTimeInWeekForEarlyMondayMorning_onePersonFound() {

        Set<Keyword> keywordSet = new HashSet<>();
        Set<DayTimeInWeek> dayTimeInWeekSet = new HashSet<>();
        dayTimeInWeekSet.add(new DayTimeInWeek("mon@0030"));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonSuggestionPredicate predicate =
                new PersonSuggestionPredicate(dayTimeInWeekSet, keywordSet);
        SuggestCommand command = new SuggestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage,expectedModel);
        assertEquals(Arrays.asList(JUSTIN),
                model.getFilteredPersonList());
    }

}
