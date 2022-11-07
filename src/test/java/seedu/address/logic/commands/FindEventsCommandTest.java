package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.BOTTLE_SALE;
import static seedu.address.testutil.TypicalEvents.SHOE_SALE;
import static seedu.address.testutil.TypicalEvents.SLIPPER_SALE;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventTitleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventsCommand}.
 */
public class FindEventsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        EventTitleContainsKeywordsPredicate firstPredicate =
                new EventTitleContainsKeywordsPredicate(Collections.singletonList("first"));
        EventTitleContainsKeywordsPredicate secondPredicate =
                new EventTitleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindEventsCommand findEventsFirstCommand = new FindEventsCommand(firstPredicate);
        FindEventsCommand findEventsSecondCommand = new FindEventsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findEventsFirstCommand.equals(findEventsFirstCommand));

        // same values -> returns true
        FindEventsCommand findFirstCommandCopy = new FindEventsCommand(firstPredicate);
        assertTrue(findEventsFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findEventsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findEventsFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(findEventsFirstCommand.equals(findEventsSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventTitleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindEventsCommand command = new FindEventsCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        EventTitleContainsKeywordsPredicate predicate = preparePredicate("Shoe Slipper Bottle");
        FindEventsCommand command = new FindEventsCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SHOE_SALE, SLIPPER_SALE, BOTTLE_SALE), model.getFilteredEventList());
    }

    /**
     * Parses {@code userInput} into an {@code EventTitleContainsKeywordsPredicate}.
     */
    private EventTitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EventTitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

