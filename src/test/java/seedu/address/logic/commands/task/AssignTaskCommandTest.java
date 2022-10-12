package seedu.address.logic.commands.task;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

public class AssignTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(outOfBoundTaskIndex, new HashSet<>());

        assertCommandFailure(assignTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        HashSet<Index> invalidAssignedPersons = new HashSet<Index>();
        invalidAssignedPersons.add(outOfBoundPersonIndex);
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK, invalidAssignedPersons);

        assertCommandFailure(assignTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validTaskAndPersonsIndexes_assignSuccessful() throws Exception {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON)));

        CommandResult commandResult = assignTaskCommand.execute(model);

        assertEquals(String.format(AssignTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validTaskAndEmptyPersonsIndexes_assignSuccessful() throws Exception {
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(INDEX_FIRST_TASK, new HashSet<>());

        CommandResult commandResult = assignTaskCommand.execute(model);

        assertEquals(String.format(AssignTaskCommand.MESSAGE_RESET_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {

        AssignTaskCommand firstCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
        AssignTaskCommand secondCommand = new AssignTaskCommand(INDEX_SECOND_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
        AssignTaskCommand thirdCommand = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<Index>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON)));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        AssignTaskCommand firstCommandCopy = new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different task -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different assigned contacts -> returns false
        assertFalse(firstCommand.equals(thirdCommand));
    }

}
