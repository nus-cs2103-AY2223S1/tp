package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskTodoCommandTest {

    private static final Name NAME_AMY = new Name("Amy");
    private static final Name NAME_BOB = new Name("Bob");
    private static final Description DESCRIPTION_ONE = new Description("DESCRIPTION ONE");
    private static final Description DESCRIPTION_TWO = new Description("DESCRIPTION TWO");
    private static final Assignment ASSIGNMENT_TO = Assignment.TO;
    private static final Assignment ASSIGNMENT_FROM = Assignment.FROM;

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTodoCommand(null, DESCRIPTION_ONE, ASSIGNMENT_TO));
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTodoCommand(NAME_AMY, null, ASSIGNMENT_TO));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, null));
    }

    @Test
    public void equals() {
        // 2 to-dos with different descriptions
        TaskTodoCommand taskTodoCommandOne = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskTodoCommand taskTodoCommandTwo = new TaskTodoCommand(NAME_AMY, DESCRIPTION_TWO, ASSIGNMENT_TO);

        // 2 to-dos with different names
        TaskTodoCommand taskTodoCommandThree = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskTodoCommand taskTodoCommandFour = new TaskTodoCommand(NAME_BOB, DESCRIPTION_ONE, ASSIGNMENT_TO);

        // 2 to-dos with different assignments
        TaskTodoCommand taskTodoCommandFive = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskTodoCommand taskTodoCommandSix = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_FROM);

        // same object -> returns true
        assertTrue(taskTodoCommandOne.equals(taskTodoCommandOne));

        // same values -> returns true
        TaskTodoCommand taskTaskCommandOneCopy = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        assertTrue(taskTodoCommandOne.equals(taskTaskCommandOneCopy));

        // different types -> returns false
        assertFalse(taskTodoCommandOne.equals(1));

        // null -> returns false
        assertFalse(taskTodoCommandOne.equals(null));

        // different description -> returns false
        assertFalse(taskTodoCommandOne.equals(taskTodoCommandTwo));

        // different name -> returns false
        assertFalse(taskTodoCommandThree.equals(taskTodoCommandFour));

        // different assignment -> returns false
        assertFalse(taskTodoCommandFive.equals(taskTodoCommandSix));
    }
}
