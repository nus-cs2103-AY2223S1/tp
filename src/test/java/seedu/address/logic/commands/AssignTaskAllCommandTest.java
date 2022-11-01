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

public class AssignTaskAllCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignTaskAllCommand(null, null));
    }

    @Test
    public void execute_assignSingleTaskSuccess_commandSuccessful() {
        Person personToAssignTask = model.getPersonWithName(DANIEL.getName()).get(0);

        String groupName = INDIVIDUAL_PROJECT.getName().toString();
        String assignmentName = "Assignment ABC";
        Assignment assignmentToAssign = new Assignment(assignmentName, Workload.LOW);

        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        Person expectedEditedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", groupName},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}, {assignmentName}}).build();

        AssignTaskAllCommand assignTaskAllCommand = new AssignTaskAllCommand(
                groupName,
                assignmentToAssign
        );

        String expectedMessage = String.format(AssignTaskAllCommand.MESSAGE_ASSIGN_TASK_SUCCESS + "\n"
                + AssignTaskAllCommand.MESSAGE_ARGUMENTS,
                DANIEL.getName(), groupName, assignmentToAssign);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAssignTask, editedPerson);
        expectedModel.setPerson(personToAssignTask, expectedEditedPerson);

        assertCommandSuccess(assignTaskAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignDuplicateTask_commandSuccessful() {
        Person personToAssignTask = model.getPersonWithName(DANIEL.getName()).get(0);

        String groupName = INDIVIDUAL_PROJECT.getName().toString();
        String assignmentName = "Assignment ABC";
        Assignment assignmentToAssign = new Assignment(assignmentName, Workload.LOW);

        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", groupName},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}, {assignmentName}}).build();

        AssignTaskAllCommand assignTaskAllCommand = new AssignTaskAllCommand(
                groupName,
                assignmentToAssign
        );

        model.setPerson(personToAssignTask, editedPerson);

        assertCommandFailure(assignTaskAllCommand, model, AssignTaskAllCommand.MESSAGE_NO_TASKS_ADDED);

        assertThrows(CommandException.class,
                AssignTaskAllCommand.MESSAGE_NO_TASKS_ADDED, () -> assignTaskAllCommand.execute(model));
    }

    @Test
    public void execute_invalidGroup_throwsCommandExceptionWithInvalidGroupMessage() {
        Person personToAssignTask = model.getPersonWithName(DANIEL.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", "Gamma"},
                        new String[][]{{"Team Project, Team A"}, {"Team Beta"}, {"Team Gamma"}}).build();

        String groupName = "Gamma";
        String assignmentName = "Team Gamma";
        AssignTaskAllCommand assignTaskAllCommand = new AssignTaskAllCommand(
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToAssignTask, editedPerson);

        assertCommandFailure(assignTaskAllCommand, model, AssignTaskAllCommand.MESSAGE_INVALID_GROUP);

        assertThrows(CommandException.class,
                AssignTaskAllCommand.MESSAGE_INVALID_GROUP, () -> assignTaskAllCommand.execute(model));
    }

    @Test
    public void equals() {
        final AssignTaskAllCommand standardCommand = new AssignTaskAllCommand(
                VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));


        final AssignTaskAllCommand sameCommand = new AssignTaskAllCommand(
                VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final AssignTaskAllCommand differentGroupCommand = new AssignTaskAllCommand(
                VALID_GROUP_2, new Assignment(VALID_ASSIGNMENT_1));

        final AssignTaskAllCommand differentTaskCommand = new AssignTaskAllCommand(
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
