package seedu.guest.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.commons.core.Messages.MESSAGE_GUESTS_LISTED_OVERVIEW;
import static seedu.guest.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.guest.testutil.TypicalGuests.CARL;
import static seedu.guest.testutil.TypicalGuests.ELLE;
import static seedu.guest.testutil.TypicalGuests.FIONA;
import static seedu.guest.testutil.TypicalGuests.getTypicalGuestBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.GuestContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalGuestBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalGuestBook(), new UserPrefs());

    @Test
    public void equals() {
        GuestContainsKeywordsPredicate firstPredicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("first"));
        GuestContainsKeywordsPredicate secondPredicate =
                new GuestContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different guest -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noGuestFound() {
        String expectedMessage = String.format(MESSAGE_GUESTS_LISTED_OVERVIEW, 0);
        GuestContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGuestList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGuestList());
    }

    @Test
    public void execute_multipleKeywords_multipleGuestsFound() {
        String expectedMessage = String.format(MESSAGE_GUESTS_LISTED_OVERVIEW, 3);
        GuestContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredGuestList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredGuestList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private GuestContainsKeywordsPredicate preparePredicate(String userInput) {
        return new GuestContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
