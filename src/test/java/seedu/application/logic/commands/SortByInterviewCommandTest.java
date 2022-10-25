package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortByInterviewCommand}.
 */
class SortByInterviewCommandTest {

    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void execute_sort_success() {
        SortCommand sortCommand = new SortByInterviewCommand(false);

        String expectedMessage = String.format(SortByInterviewCommand.MESSAGE_SUCCESS, "");

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.sortApplicationListByInterview(false);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortReverse_success() {
        SortCommand sortCommand = new SortByInterviewCommand(true);

        String expectedMessage = String.format(SortByInterviewCommand.MESSAGE_SUCCESS, " reverse");

        ModelManager expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.sortApplicationListByInterview(true);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortByInterviewCommand = new SortByInterviewCommand(false);
        SortCommand sortByInterviewReversedCommand = new SortByInterviewCommand(true);
        SortCommand sortByDateCommand = new SortByDateCommand(false);

        // same object -> returns true
        assertEquals(sortByInterviewCommand, sortByInterviewCommand);

        // same values -> returns true
        SortCommand sortByInterviewCommandCopy = new SortByInterviewCommand(false);
        assertEquals(sortByInterviewCommand, sortByInterviewCommandCopy);

        // different types -> returns false
        assertNotEquals(sortByInterviewCommand, 1);

        // null -> returns false
        assertNotEquals(sortByInterviewCommand, null);

        // different values -> return false
        assertNotEquals(sortByInterviewCommand, sortByInterviewReversedCommand);

        // different sort command subtype -> return false
        assertNotEquals(sortByInterviewCommand, sortByDateCommand);
    }
}
