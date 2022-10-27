package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.assertCommandFailure;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.testutil.TypicalIntegratedAddressBook.getTypicalAddressBook;
import static swift.testutil.TypicalPersonIndexes.INDEX_FIRST_PERSON;
import static swift.testutil.TypicalPersonIndexes.INDEX_SECOND_PERSON;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;
import static swift.testutil.TypicalTaskIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;
import swift.model.person.Person;
import swift.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AssignCommand}.
 */
public class AssignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        AssignCommand assignCommand = new AssignCommand(INDEX_SECOND_PERSON, INDEX_SECOND_TASK);
        Person expectedPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Task expectedTask = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_SUCCESS, expectedTask, expectedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addBridge(expectedPerson, expectedTask);

        assertCommandSuccess(assignCommand, model, expectedMessage, CommandType.ASSIGN, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AssignCommand assignCommand = new AssignCommand(outOfBoundIndex, INDEX_FIRST_TASK);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(assignCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AssignCommand assignCommand1 = new AssignCommand(INDEX_FIRST_PERSON, INDEX_SECOND_TASK);
        AssignCommand assignCommand2 = new AssignCommand(INDEX_SECOND_PERSON, INDEX_FIRST_TASK);

        // same object -> returns true
        assertTrue(assignCommand1.equals(assignCommand1));

        // same values -> returns true
        AssignCommand assignCommand3 = new AssignCommand(INDEX_FIRST_PERSON, INDEX_SECOND_TASK);
        assertTrue(assignCommand1.equals(assignCommand3));

        // different types -> returns false
        assertFalse(assignCommand1.equals(1));

        // null -> returns false
        assertFalse(assignCommand1.equals(null));

        // different task -> returns false
        assertFalse(assignCommand1.equals(assignCommand2));
    }
}
