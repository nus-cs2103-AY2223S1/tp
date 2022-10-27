package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.assertCommandFailure;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.testutil.TypicalIntegratedAddressBook.getTypicalAddressBook;
import static swift.testutil.TypicalPersonIndexes.INDEX_FIRST_PERSON;
import static swift.testutil.TypicalPersonIndexes.INDEX_SECOND_PERSON;
import static swift.testutil.TypicalTaskIndexes.*;

import org.junit.jupiter.api.Test;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnassignCommand}.
 */
public class UnassignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);
        Person expectedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Task expectedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        String expectedMessage = String.format(UnassignCommand.MESSAGE_UNASSIGN_SUCCESS, expectedTask, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        PersonTaskBridge target = new PersonTaskBridge(expectedPerson.getId(), expectedTask.getId());
        expectedModel.deleteBridge(target);

        assertCommandSuccess(unassignCommand, model, expectedMessage, CommandType.UNASSIGN, expectedModel);
    }

    @Test
    public void execute_bridgeDoesNotExist_throwsCommandException() {
        UnassignCommand unassign = new UnassignCommand(INDEX_FIRST_PERSON, INDEX_THIRD_TASK);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandFailure(unassign, expectedModel, UnassignCommand.MESSAGE_BRIDGE_DOESNT_EXIST);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnassignCommand unassignCommand = new UnassignCommand(outOfBoundIndex, INDEX_FIRST_TASK);

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnassignCommand unassignCommand1 = new UnassignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);
        UnassignCommand unassignCommand2 = new UnassignCommand(INDEX_SECOND_PERSON, INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unassignCommand1.equals(unassignCommand1));

        // same values -> returns true
        UnassignCommand assignCommand3 = new UnassignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);
        assertTrue(unassignCommand1.equals(assignCommand3));

        // different types -> returns false
        assertFalse(unassignCommand1.equals(1));

        // null -> returns false
        assertFalse(unassignCommand1.equals(null));

        // different task -> returns false
        assertFalse(unassignCommand1.equals(unassignCommand2));
    }
}
