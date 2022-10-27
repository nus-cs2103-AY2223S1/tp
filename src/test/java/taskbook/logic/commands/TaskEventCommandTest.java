package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.commands.modelstubs.ModelStub;
import taskbook.logic.commands.modelstubs.ModelStubAcceptingTaskAdded;
import taskbook.logic.commands.modelstubs.ModelStubWithPerson;
import taskbook.logic.commands.tasks.TaskAddCommand;
import taskbook.logic.commands.tasks.TaskEventCommand;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Description;
import taskbook.model.task.Event;
import taskbook.model.task.enums.Assignment;
import taskbook.testutil.EventBuilder;
import taskbook.testutil.PersonBuilder;

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
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        Person validPerson = new PersonBuilder().withName(String.valueOf(NAME_BOB)).build();
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded(validPerson);

        Event validTask = new EventBuilder().withPersonName(validPerson).withEventDate(DATE_TWO).build();
        CommandResult commandResult = new TaskEventCommand(validTask.getName(), validTask.getDescription(),
                validTask.getAssignment(), validTask.getDate()).execute(modelStub);

        assertEquals(String.format(TaskEventCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.getTasks());
    }

    @Test
    public void execute_personAssociatedWithTaskNotFound_throwsCommandException() {
        Person johnny = new PersonBuilder().withName("Johnny").build();
        TaskEventCommand taskEventCommand = new TaskEventCommand(NAME_AMY, DESCRIPTION_ONE,
                ASSIGNMENT_TO, DATE_ONE);
        ModelStub modelStub = new ModelStubWithPerson(johnny);

        assertThrows(CommandException.class,
                TaskAddCommand.MESSAGE_PERSON_NOT_FOUND, () -> taskEventCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws CommandException {
        Person validPerson = new PersonBuilder().withName(String.valueOf(NAME_BOB)).build();
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded(validPerson);

        Event validTask = new EventBuilder().withPersonName(validPerson).withEventDate(DATE_TWO).build();
        TaskEventCommand taskEventCommand = new TaskEventCommand(validTask.getName(), validTask.getDescription(),
                validTask.getAssignment(), validTask.getDate());

        // Adds the task into the modelStub.
        taskEventCommand.execute(modelStub);

        assertThrows(CommandException.class,
                TaskAddCommand.MESSAGE_DUPLICATE_TASK_FAILURE, () -> taskEventCommand.execute(modelStub));
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
