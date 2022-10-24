package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.event.FindEventCommand.MESSAGE_EVENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.event.FindEventCommand.MESSAGE_NO_MATCH;
import static seedu.address.testutil.TypicalEvents.DINNER;
import static seedu.address.testutil.TypicalEvents.PRACTICE;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.TitleContainsKeywordsPredicate;
import seedu.address.testutil.TypicalAddressBook;

public class FindEventCommandTest {
    private Model model = new ModelManager(TypicalAddressBook.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalAddressBook.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("first"));
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindEventCommand findEventFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findEventSecondCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findEventFirstCommand.equals(findEventFirstCommand));

        // same values -> returns true
        FindEventCommand findEventFirstCommandCopy = new FindEventCommand(firstPredicate);
        assertTrue(findEventFirstCommand.equals(findEventFirstCommandCopy));

        // different types -> returns false
        assertFalse(findEventFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findEventFirstCommand.equals(null));

        // different event -> returns false
        assertFalse(findEventFirstCommand.equals(findEventSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        TitleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, MESSAGE_NO_MATCH, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_singleKeyword_singleEventFound() {
        TitleContainsKeywordsPredicate predicate = preparePredicate("Presentation");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, MESSAGE_EVENT_LISTED_OVERVIEW, expectedModel);
        assertEquals(Arrays.asList(PRESENTATION), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        TitleContainsKeywordsPredicate predicate = preparePredicate("Presentation Practice Dinner");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PRESENTATION, PRACTICE, DINNER), model.getFilteredEventList());
    }

    @Test
    public void execute_partialKeywords_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        TitleContainsKeywordsPredicate predicate = preparePredicate("Pre PraC Din");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PRESENTATION, PRACTICE, DINNER), model.getFilteredEventList());
    }

    /**
     * Parses {@code userInput} into a {@code TitleContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
