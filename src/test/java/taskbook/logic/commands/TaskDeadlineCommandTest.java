package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskDeadlineCommand;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskDeadlineCommandTest {

    private static final Name NAME_AMY = new Name("Amy");
    private static final Name NAME_BOB = new Name("Bob");
    private static final Description DESCRIPTION_ONE = new Description("DESCRIPTION ONE");
    private static final Description DESCRIPTION_TWO = new Description("DESCRIPTION TWO");
    private static final Assignment ASSIGNMENT_TO = Assignment.TO;
    private static final Assignment ASSIGNMENT_FROM = Assignment.FROM;
    private static final LocalDate DATE_ONE = LocalDate.parse("2014-06-21");
    private static final LocalDate DATE_TWO = LocalDate.parse("1989-02-16");

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskDeadlineCommand(null, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE));
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskDeadlineCommand(NAME_AMY, null, ASSIGNMENT_TO, DATE_ONE));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, null, DATE_ONE));
    }

    @Test
    public void constructor_nullLocalDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, null));
    }

    @Test
    public void equals() {
        // 2 deadlines with different descriptions
        TaskDeadlineCommand taskDeadlineCommandOne =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskDeadlineCommand taskDeadlineCommandTwo =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_TWO, ASSIGNMENT_TO, DATE_ONE);

        // 2 deadlines with different names
        TaskDeadlineCommand taskDeadlineCommandThree =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskDeadlineCommand taskDeadlineCommandFour =
                new TaskDeadlineCommand(NAME_BOB, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);

        // 2 deadlines with different assignments
        TaskDeadlineCommand taskDeadlineCommandFive =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskDeadlineCommand taskDeadlineCommandSix =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_FROM, DATE_ONE);

        // 2 deadlines with different dates
        TaskDeadlineCommand taskDeadlineCommandSeven =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskDeadlineCommand taskDeadlineCommandEight =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_TWO);

        // same object -> returns true
        assertTrue(taskDeadlineCommandOne.equals(taskDeadlineCommandOne));

        // same values -> returns true
        TaskDeadlineCommand taskDeadlineCommandOneCopy =
                new TaskDeadlineCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        assertTrue(taskDeadlineCommandOne.equals(taskDeadlineCommandOneCopy));

        // different types -> returns false
        assertFalse(taskDeadlineCommandOne.equals(1));

        // null -> returns false
        assertFalse(taskDeadlineCommandOne.equals(null));

        // different description -> returns false
        assertFalse(taskDeadlineCommandOne.equals(taskDeadlineCommandTwo));

        // different name -> returns false
        assertFalse(taskDeadlineCommandThree.equals(taskDeadlineCommandFour));

        // different assignment -> returns false
        assertFalse(taskDeadlineCommandFive.equals(taskDeadlineCommandSix));

        // different date -> returns false
        assertFalse(taskDeadlineCommandSeven.equals(taskDeadlineCommandEight));
    }
}
