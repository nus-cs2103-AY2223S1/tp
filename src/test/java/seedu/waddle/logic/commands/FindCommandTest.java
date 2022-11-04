package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.commons.core.Messages.MESSAGE_ITINERARIES_LISTED_OVERVIEW;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.TypicalItineraries.AUTUMN;
import static seedu.waddle.testutil.TypicalItineraries.SPRING;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWaddle(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different itinerary -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noItineraryFound() {
        String expectedMessage = String.format(MESSAGE_ITINERARIES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItineraryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredItineraryList());
    }

    @Test
    public void execute_multipleKeywords_multipleItinerariesFound() {
        String expectedMessage = String.format(MESSAGE_ITINERARIES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Spring Autumn");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredItineraryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SPRING, AUTUMN), model.getFilteredItineraryList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
