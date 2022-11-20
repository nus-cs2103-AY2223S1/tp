package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.DANIEL;
import static seedu.address.model.person.testutil.TypicalPersons.INDIVIDUAL_PROJECT;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBookWithGroups;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Workload;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.PersonBuilder;

public class DeleteTaskAllCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskAllCommand(null, null));
    }

    @Test
    public void execute_deleteSingleTaskSuccess_commandSuccessful() {
        Person personToDeleteTask = model.getPersonWithName(DANIEL.getName()).get(0);

        String groupName = INDIVIDUAL_PROJECT.getName().toString();
        String assignmentName = "Assignment ABC";
        Assignment assignmentToDelete = new Assignment(assignmentName, Workload.LOW);

        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta", groupName},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}, {assignmentName}}).build();

        Person expectedEditedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        DeleteTaskAllCommand deleteTaskAllCommand = new DeleteTaskAllCommand(
                groupName,
                assignmentToDelete
        );

        String expectedMessage = String.format(DeleteTaskAllCommand.MESSAGE_ASSIGN_TASK_SUCCESS + "\n"
                + DeleteTaskAllCommand.MESSAGE_ARGUMENTS,
                DANIEL.getName(), groupName, assignmentToDelete);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToDeleteTask, editedPerson);
        expectedModel.setPerson(personToDeleteTask, expectedEditedPerson);

        assertCommandSuccess(deleteTaskAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNoTask_commandSuccessful() {
        Person personToDeleteTask = model.getPersonWithName(DANIEL.getName()).get(0);

        String groupName = INDIVIDUAL_PROJECT.getName().toString();
        String assignmentName = "Assignment ABC";
        Assignment assignmentToDelete = new Assignment(assignmentName, Workload.LOW);

        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        DeleteTaskAllCommand deleteTaskAllCommand = new DeleteTaskAllCommand(
                groupName,
                assignmentToDelete
        );

        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandFailure(deleteTaskAllCommand, model, DeleteTaskAllCommand.MESSAGE_NO_TASKS_DELETED);

        assertThrows(CommandException.class,
                DeleteTaskAllCommand.MESSAGE_NO_TASKS_DELETED, () -> deleteTaskAllCommand.execute(model));
    }

    @Test
    public void execute_invalidGroup_throwsCommandExceptionWithInvalidGroupMessage() {
        Person personToAssignTask = model.getPersonWithName(DANIEL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", "Gamma"},
                        new String[][]{{"Team Project, Team A"}, {"Team Beta"}, {"Team Gamma"}}).build();

        String groupName = "Gamma";
        String assignmentName = "Team Gamma";
        DeleteTaskAllCommand deleteTaskAllCommand = new DeleteTaskAllCommand(
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToAssignTask, editedPerson);

        assertCommandFailure(deleteTaskAllCommand, model, DeleteTaskAllCommand.MESSAGE_INVALID_GROUP);

        assertThrows(CommandException.class,
                DeleteTaskAllCommand.MESSAGE_INVALID_GROUP, () -> deleteTaskAllCommand.execute(model));
    }

    @Test
    public void equals() {
        final DeleteTaskAllCommand standardCommand = new DeleteTaskAllCommand(
                VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));


        final DeleteTaskAllCommand sameCommand = new DeleteTaskAllCommand(
                VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final DeleteTaskAllCommand differentGroupCommand = new DeleteTaskAllCommand(
                VALID_GROUP_2, new Assignment(VALID_ASSIGNMENT_1));

        final DeleteTaskAllCommand differentTaskCommand = new DeleteTaskAllCommand(
                VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT));

        // same values -> returns true
        assertTrue(standardCommand.equals(sameCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different group -> returns false
        assertFalse(standardCommand.equals(differentGroupCommand));

        // different task -> returns false
        assertFalse(standardCommand.equals(differentTaskCommand));
    }
}
