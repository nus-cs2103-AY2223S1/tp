package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.assertCommandFailure;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.logic.commands.CommandTestUtil.showTaskAtIndex;
import static swift.testutil.TypicalIntegratedAddressBook.PERSON1_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.PERSON2_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.TASK1_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.TASK3_UUID;
import static swift.testutil.TypicalIntegratedAddressBook.getTypicalAddressBook;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;
import static swift.testutil.TypicalTaskIndexes.INDEX_SECOND_TASK;
import static swift.testutil.TypicalTaskIndexes.INDEX_THIRD_TASK;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectTaskCommand}.
 */
public class SelectTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        SelectTaskCommand selectTaskCommand = new SelectTaskCommand(INDEX_THIRD_TASK);
        String expectedMessage = SelectTaskCommand.MESSAGE_SELECT_TASK_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList((p) -> p.getId().equals(UUID.fromString(PERSON2_UUID)));
        expectedModel.updateFilteredTaskList((t) -> t.getId().equals(UUID.fromString(TASK3_UUID)));
        expectedModel.updateFilteredBridgeList((b) -> b.getTaskId().equals(UUID.fromString(TASK3_UUID)));

        assertCommandSuccess(selectTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        SelectTaskCommand selectTaskCommand = new SelectTaskCommand(outOfBoundIndex);

        assertCommandFailure(selectTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        SelectTaskCommand selectTaskCommand = new SelectTaskCommand(INDEX_FIRST_TASK);
        String expectedMessage = SelectTaskCommand.MESSAGE_SELECT_TASK_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(p -> p.getId().equals(UUID.fromString(PERSON1_UUID)));
        expectedModel.updateFilteredTaskList(t -> t.getId().equals(UUID.fromString(TASK1_UUID)));
        expectedModel.updateFilteredBridgeList(b -> b.getTaskId().equals(UUID.fromString(TASK1_UUID)));

        assertCommandSuccess(selectTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        SelectTaskCommand selectTaskCommand = new SelectTaskCommand(outOfBoundIndex);

        assertCommandFailure(selectTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectTaskCommand selectFirstTaskCommand = new SelectTaskCommand(INDEX_FIRST_TASK);
        SelectTaskCommand selectSecondTaskCommand = new SelectTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(selectFirstTaskCommand.equals(selectFirstTaskCommand));

        // same values -> returns true
        SelectTaskCommand selectFirstTaskCommandCopy = new SelectTaskCommand(INDEX_FIRST_TASK);
        assertTrue(selectFirstTaskCommand.equals(selectFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(selectFirstTaskCommand.equals(selectSecondTaskCommand));
    }
}
