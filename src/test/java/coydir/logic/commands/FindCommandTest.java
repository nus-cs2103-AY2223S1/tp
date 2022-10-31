package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertViewingCommandSuccess;
import static coydir.testutil.TypicalPersons.CARL;
import static coydir.testutil.TypicalPersons.DANIEL;
import static coydir.testutil.TypicalPersons.ELLE;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;
import coydir.model.person.PersonMatchesKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void equals() {
        PersonMatchesKeywordsPredicate firstPredicate =
                new PersonMatchesKeywordsPredicate("one", "two", "three");
        PersonMatchesKeywordsPredicate secondPredicate =
                new PersonMatchesKeywordsPredicate("four", "five", "six");

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(FindCommand.MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("el");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertFindCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_onePersonFound() {
        String expectedMessage = String.format(FindCommand.MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("Kurz Fire Operations");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertFindCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_mismatchedKeywords_noPersonFound() {
        String expectedMessage = String.format(FindCommand.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonMatchesKeywordsPredicate predicate = preparePredicate("Alice Fire");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertFindCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code PersonMatchesKeywordsPredicate}.
     */
    private PersonMatchesKeywordsPredicate preparePredicate(String userInput) {
        List<String> keywords = new ArrayList<>(Arrays.asList(userInput.split("\\s+")));
        while (keywords.size() < 3) {
            keywords.add("");
        }

        return new PersonMatchesKeywordsPredicate(keywords.get(0), keywords.get(1), keywords.get(2));
    }

    private void assertFindCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        assertViewingCommandSuccess(command, actualModel, expectedMessage, expectedModel, 0);
    }
}
