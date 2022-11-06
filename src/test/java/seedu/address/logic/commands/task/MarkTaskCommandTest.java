package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
//import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkTaskCommand.
 */
public class MarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskCommand firstCommand = new MarkTaskCommand(INDEX_FIRST_TEAMMATE);
        MarkTaskCommand secondCommand = new MarkTaskCommand(INDEX_SECOND_TEAMMATE);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        MarkTaskCommand firstCommandCopy = new MarkTaskCommand(INDEX_FIRST_TEAMMATE);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different teammate -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTeammate(Model model) {
        model.updateFilteredTeammateList(p -> false);

        assertTrue(model.getFilteredTeammateList().isEmpty());
    }
}
