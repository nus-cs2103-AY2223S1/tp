package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;
import static taskbook.testutil.PersonBuilder.DEFAULT_NAME;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskAddCommand;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskAddCommandTest {

    private static final Name NAME = new Name(DEFAULT_NAME);
    private static final Description DESCRIPTION_ONE = new Description("Placeholder1");
    private static final Description DESCRIPTION_TWO = new Description("Placeholder2");
    private static final Assignment ASSIGNMENT = Assignment.TO;

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(null, DESCRIPTION_ONE, ASSIGNMENT));
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(NAME, null, ASSIGNMENT));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(NAME, DESCRIPTION_ONE, null));
    }

    @Test
    public void equals() {
        TaskAddCommand addTaskCommandOne = new TaskAddCommand(NAME, DESCRIPTION_ONE, ASSIGNMENT);
        TaskAddCommand addTaskCommandTwo = new TaskAddCommand(NAME, DESCRIPTION_TWO, ASSIGNMENT);

        // same object -> returns true
        assertTrue(addTaskCommandOne.equals(addTaskCommandOne));

        // same values -> returns true
        TaskAddCommand addTaskCommandOneCopy = new TaskAddCommand(NAME, DESCRIPTION_ONE, ASSIGNMENT);
        assertTrue(addTaskCommandOne.equals(addTaskCommandOneCopy));

        // different types -> returns false
        assertFalse(addTaskCommandOne.equals(1));

        // null -> returns false
        assertFalse(addTaskCommandOne.equals(null));

        // different task -> returns false
        assertFalse(addTaskCommandOne.equals(addTaskCommandTwo));
    }
}
