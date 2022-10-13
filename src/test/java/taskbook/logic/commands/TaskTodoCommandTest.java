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
        TaskTodoCommand addTodoCommandOne = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskTodoCommand addTodoCommandTwo = new TaskTodoCommand(NAME_AMY, DESCRIPTION_TWO, ASSIGNMENT_TO);

        // 2 to-dos with different names
        TaskTodoCommand addTodoCommandThree = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskTodoCommand addTodoCommandFour = new TaskTodoCommand(NAME_BOB, DESCRIPTION_ONE, ASSIGNMENT_TO);

        // 2 to-dos with different assignments
        TaskTodoCommand addTodoCommandFive = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        TaskTodoCommand addTodoCommandSix = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_FROM);

        // same object -> returns true
        assertTrue(addTodoCommandOne.equals(addTodoCommandOne));

        // same values -> returns true
        TaskTodoCommand addTaskCommandOneCopy = new TaskTodoCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO);
        assertTrue(addTodoCommandOne.equals(addTaskCommandOneCopy));

        // different types -> returns false
        assertFalse(addTodoCommandOne.equals(1));

        // null -> returns false
        assertFalse(addTodoCommandOne.equals(null));

        // different description -> returns false
        assertFalse(addTodoCommandOne.equals(addTodoCommandTwo));

        // different name -> returns false
        assertFalse(addTodoCommandThree.equals(addTodoCommandFour));

        // different assignment -> returns false
        assertFalse(addTodoCommandFive.equals(addTodoCommandSix));
    }
}
