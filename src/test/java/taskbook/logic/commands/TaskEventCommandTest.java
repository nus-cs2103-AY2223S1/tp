package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskEventCommand;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskEventCommandTest {

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
                new TaskEventCommand(null, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE));
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskEventCommand(NAME_AMY, null, ASSIGNMENT_TO, DATE_ONE));
    }

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, null, DATE_ONE));
    }

    @Test
    public void constructor_nullLocalDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, null));
    }

    @Test
    public void equals() {
        // 2 events with different descriptions
        TaskEventCommand taskEventCommandOne = new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskEventCommand taskEventCommandTwo = new TaskEventCommand(NAME_AMY, DESCRIPTION_TWO, ASSIGNMENT_TO, DATE_ONE);

        // 2 events with different names
        TaskEventCommand taskEventCommandThree =
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskEventCommand taskEventCommandFour =
                new TaskEventCommand(NAME_BOB, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);

        // 2 events with different assignments
        TaskEventCommand taskEventCommandFive =
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskEventCommand taskEventCommandSix =
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_FROM, DATE_ONE);

        // 2 events with different dates
        TaskEventCommand taskEventCommandSeven =
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        TaskEventCommand taskEventCommandEight =
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_TWO);

        // same object -> returns true
        assertTrue(taskEventCommandOne.equals(taskEventCommandOne));

        // same values -> returns true
        TaskEventCommand taskEventCommandOneCopy =
                new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE, ASSIGNMENT_TO, DATE_ONE);
        assertTrue(taskEventCommandOne.equals(taskEventCommandOneCopy));

        // different types -> returns false
        assertFalse(taskEventCommandOne.equals(1));

        // null -> returns false
        assertFalse(taskEventCommandOne.equals(null));

        // different description -> returns false
        assertFalse(taskEventCommandOne.equals(taskEventCommandTwo));

        // different name -> returns false
        assertFalse(taskEventCommandThree.equals(taskEventCommandFour));

        // different assignment -> returns false
        assertFalse(taskEventCommandFive.equals(taskEventCommandSix));

        // different date -> returns false
        assertFalse(taskEventCommandSeven.equals(taskEventCommandEight));
    }
}
