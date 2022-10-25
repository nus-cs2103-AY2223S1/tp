package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_ACCORDING_TO_DAY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DayIsKeywordPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code ShowCommand}.
 */
public class ShowCommandTest {
    private static final String DAY_STUB = "Sat";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_inFullView_failure() {
        model.setFullView();
        ShowCommand showCommand = new ShowCommand(DAY_STUB);
        assertCommandFailure(showCommand, model, ShowCommand.MESSAGE_NOT_LIST_MODE);
    }

    @Test
    public void equals() {
        String firstKeyword = "first";
        String secondKeyword = "second";

        ShowCommand showFirstCommand = new ShowCommand(firstKeyword);
        ShowCommand showSecondCommand = new ShowCommand(secondKeyword);

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        ShowCommand showFirstCommandCopy = new ShowCommand(firstKeyword);
        assertTrue(showFirstCommand.equals(showFirstCommandCopy));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTimeSlotsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_ACCORDING_TO_DAY, 0, DAY_STUB);
        ShowCommand command = new ShowCommand(DAY_STUB);
        expectedModel.updateFilteredPersonList(new DayIsKeywordPredicate(DAY_STUB));
        expectedModel.updateTimeSlots(DAY_STUB);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getTimeSlotList());
    }
}
