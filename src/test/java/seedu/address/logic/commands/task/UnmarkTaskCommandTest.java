package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEAMMATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEAMMATE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkTaskCommand.
 */
public class UnmarkTaskCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkTaskCommand firstCommand = new UnmarkTaskCommand(INDEX_FIRST_TEAMMATE);
        UnmarkTaskCommand secondCommand = new UnmarkTaskCommand(INDEX_SECOND_TEAMMATE);

        // same object -> returns true
        assertEquals(firstCommand, firstCommand);

        // same values -> returns true
        UnmarkTaskCommand firstCommandCopy = new UnmarkTaskCommand(INDEX_FIRST_TEAMMATE);
        assertEquals(firstCommand, firstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstCommand);

        // null -> returns false
        assertNotEquals(null, firstCommand);

        // different teammate -> returns false
        assertNotEquals(firstCommand, secondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTeammate(Model model) {
        model.updateFilteredTeammateList(p -> false);

        assertTrue(model.getFilteredTeammateList().isEmpty());
    }
}
