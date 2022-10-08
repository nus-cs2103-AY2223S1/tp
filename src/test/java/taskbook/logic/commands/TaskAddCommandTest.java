package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskAddCommand;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskAddCommandTest {

    private static final Name NAME_AMY = new Name("Amy");
    private static final Name NAME_BOB = new Name("Bob");
    private static final Description DESCRIPTION_ONE = new Description("DESCRIPTION ONE");
    private static final Description DESCRIPTION_TWO = new Description("DESCRIPTION TWO");
    private static final Assignment ASSIGNMENT_TO = Assignment.TO;
    private static final Assignment ASSIGNMENT_FROM = Assignment.FROM;

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(null, DESCRIPTION_ONE, ASSIGNMENT_TO));
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(NAME_AMY, null, ASSIGNMENT_TO));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskAddCommand(NAME_AMY, DESCRIPTION_ONE, null));
    }

    @Test
    public void equals() {
        // 2 tasks with different descriptions
        TaskAddCommand addTaskCommandOne = new TaskAddCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskAddCommand addTaskCommandTwo = new TaskAddCommand(NAME_AMY, DESCRIPTION_TWO, ASSIGNMENT_TO);

        // 2 tasks with different names
        TaskAddCommand addTaskCommandThree = new TaskAddCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskAddCommand addTaskCommandFour = new TaskAddCommand(NAME_BOB, DESCRIPTION_ONE, ASSIGNMENT_TO);

        // 2 tasks with different assignments
        TaskAddCommand addTaskCommandFive = new TaskAddCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskAddCommand addTaskCommandSix = new TaskAddCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_FROM);

        // same object -> returns true
        assertTrue(addTaskCommandOne.equals(addTaskCommandOne));

        // same values -> returns true
        TaskAddCommand addTaskCommandOneCopy = new TaskAddCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        assertTrue(addTaskCommandOne.equals(addTaskCommandOneCopy));

        // different types -> returns false
        assertFalse(addTaskCommandOne.equals(1));

        // null -> returns false
        assertFalse(addTaskCommandOne.equals(null));

        // different description -> returns false
        assertFalse(addTaskCommandOne.equals(addTaskCommandTwo));

        // different name -> returns false
        assertFalse(addTaskCommandThree.equals(addTaskCommandFour));

        // different assignment -> returns false
        assertFalse(addTaskCommandFive.equals(addTaskCommandSix));
    }
}
