package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Deadline;

public class DeadlineTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeadlineTaskCommand command = new DeadlineTaskCommand(outOfBoundTaskIndex, Deadline.of(LocalDate.now()));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validTask_commandSuccessful() throws Exception {
        DeadlineTaskCommand command = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(LocalDate.now()));

        CommandResult commandResult = command.execute(model);

        assertEquals(String.format(DeadlineTaskCommand.MESSAGE_SUCCESS, INDEX_FIRST_TASK.getOneBased()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DeadlineTaskCommand firstCommand = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(today));
        DeadlineTaskCommand secondCommand = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(tomorrow));
        DeadlineTaskCommand thirdCommand = new DeadlineTaskCommand(INDEX_SECOND_TASK, Deadline.of(today));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeadlineTaskCommand firstCommandCopy = new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(today));
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
