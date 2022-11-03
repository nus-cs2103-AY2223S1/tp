package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.watson.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.watson.testutil.TypicalStudents.ALICE;
import static seedu.watson.testutil.TypicalStudents.BENSON;
import static seedu.watson.testutil.TypicalStudents.CARL;
import static seedu.watson.testutil.TypicalStudents.DANIEL;
import static seedu.watson.testutil.TypicalStudents.ELLE;
import static seedu.watson.testutil.TypicalStudents.FIONA;
import static seedu.watson.testutil.TypicalStudents.GEORGE;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.UserPrefs;
import seedu.watson.model.student.FindCommandPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        FindCommandPredicate firstPredicate =
            new FindCommandPredicate(Collections.singletonList("first"));
        FindCommandPredicate secondPredicate =
            new FindCommandPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /*@Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommandPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommandPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }*/

    @Test
    public void execute_nameKeywordNotInList_noPersonFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String name = "Hoon";
        keywords.add(0, name);
        keywords.add(1, "");
        keywords.add(2, "");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleNameKeyword_onePersonFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String name = "Fiona";
        keywords.add(0, name);
        keywords.add(1, "");
        keywords.add(2, "");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleNameKeyword_multiplePersonsWithSameNameFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String name = "Meier";
        keywords.add(0, name);
        keywords.add(1, "");
        keywords.add(2, "");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String name = "Fiona Daniel Kurz";
        keywords.add(0, name);
        keywords.add(1, "");
        keywords.add(2, "");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, FIONA), model.getFilteredPersonList());
    }

    // Show that arguments are case-insensitive
    @Test
    public void execute_multipleNameKeywordsDifferentCase_multiplePersonsFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String name = "FiOna daniel kurZ";
        keywords.add(0, name);
        keywords.add(1, "");
        keywords.add(2, "");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleStudentClassKeyword_personsFromSameClassFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String studentClass = "1A";
        keywords.add(0, "");
        keywords.add(1, studentClass);
        keywords.add(2, "");

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleSubjectKeyword_personsWithSameSubjectFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String subjects = "MATH";
        keywords.add(0, "");
        keywords.add(1, "");
        keywords.add(2, subjects);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleSubjectKeywords_personsWithSameSubjectsFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String subjects = "MATH ENGLISH";
        keywords.add(0, "");
        keywords.add(1, "");
        keywords.add(2, subjects);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    // Test to confirm that subject keywords are case-insensitive
    @Test
    public void execute_multipleSubjectKeywordsDifferentCase_personsWithSameSubjectsFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String subjects = "mAtH eNgliSH";
        keywords.add(0, "");
        keywords.add(1, "");
        keywords.add(2, subjects);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleDifferentKeywords_personsWithMatchingFieldsFound() {
        ArrayList<String> keywords = new ArrayList<>();
        String names = "Meier Pauline";
        String studentClass = "1A 1B";
        String subjects = "mAtH eNgliSH";
        keywords.add(0, "");
        keywords.add(1, "");
        keywords.add(2, subjects);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCommandPredicate predicate = preparePredicateUsingList(keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code FindCommandPredicate}.
     */
    private FindCommandPredicate preparePredicate(String userInput) {
        return new FindCommandPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Creates a new {@code FindCommandPredicate} using an ArrayList containing user input.
     * ArrayList contains 3 categories obtained from user input: names,  student classes and subjects.
     *
     * @param userInputs ArrayList containing user input.
     * @return A new FindCommandPredicate.
     */
    private FindCommandPredicate preparePredicateUsingList(ArrayList<String> userInputs) {
        return new FindCommandPredicate(userInputs);
    }
}
