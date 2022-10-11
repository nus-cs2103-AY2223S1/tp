package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.DeleteCommand;
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
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkTaskCommand MarkTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(MarkTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskCommand FirstCommand = new MarkTaskCommand(INDEX_FIRST_PERSON);
        MarkTaskCommand SecondCommand = new MarkTaskCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(FirstCommand.equals(FirstCommand));

        // same values -> returns true
        MarkTaskCommand FirstCommandCopy = new MarkTaskCommand(INDEX_FIRST_PERSON);
        assertTrue(FirstCommand.equals(FirstCommandCopy));

        // different types -> returns false
        assertFalse(FirstCommand.equals(1));

        // null -> returns false
        assertFalse(FirstCommand.equals(null));

        // different person -> returns false
        assertFalse(FirstCommand.equals(SecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
