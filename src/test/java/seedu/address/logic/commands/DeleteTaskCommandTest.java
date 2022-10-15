package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.ALPHA;
import static seedu.address.model.person.testutil.TypicalPersons.BETA;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
import static seedu.address.model.person.testutil.TypicalPersons.ORAL_PRESENTATION;
import static seedu.address.model.person.testutil.TypicalPersons.TEAM_PROJECT;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBookWithGroups;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {
    private Model model = new ModelManager(new AddressBook(getTypicalAddressBookWithGroups()), new UserPrefs());

    @Test
    public void execute_deleteTaskSuccessWithoutRemovingGroup_commandSuccessful() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        String groupName = "Alpha";
        String assignmentName = "Team A";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
                );

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS + "\n"
                + DeleteTaskCommand.MESSAGE_ARGUMENTS,
                ELLE.getName(), groupName, assignmentName);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteTaskSuccessAndRemovingGroup_commandSuccessful() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        Person expectedEditedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha"},
                        new String[][]{{"Team Project", "Team A"}}).build();

        String groupName = "Beta";
        String assignmentName = "Team Beta";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS + "\n"
                        + DeleteTaskCommand.MESSAGE_ARGUMENTS,
                ELLE.getName(), groupName, assignmentName);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        model.setPerson(personToDeleteTask, editedPerson);
        expectedModel.setPerson(personToDeleteTask, expectedEditedPerson);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteTaskInvalidPerson_throwsCommandExceptionWithInvalidPersonMessage() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        String groupName = "InvalidGroup";
        String assignmentName = "Team A";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                new Name("ABC"),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandFailure(deleteTaskCommand, model, DeleteTaskCommand.MESSAGE_INVALID_PERSON);

        assertThrows(CommandException.class,
                DeleteTaskCommand.MESSAGE_INVALID_PERSON, () -> deleteTaskCommand.execute(model));
    }

    @Test
    public void execute_deleteTaskInvalidGroup_throwsCommandExceptionWithGroupNotFoundMessage() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withGroups(new String[]{ORAL_PRESENTATION.getName().toString(),
                        ALPHA.getName().toString(), BETA.getName().toString()})
                .withAssignments(new String[]{ALPHA.getName().toString(), BETA.getName().toString()},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        String groupName = "InvalidGroup";
        String assignmentName = "Team A";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandFailure(deleteTaskCommand, model, DeleteTaskCommand.MESSAGE_GROUP_NOT_FOUND);

        assertThrows(CommandException.class,
                DeleteTaskCommand.MESSAGE_GROUP_NOT_FOUND, () -> deleteTaskCommand.execute(model));
    }

    @Test
    public void execute_deleteTaskInvalidTask_throwsCommandExceptionWithInvalidTaskMessage() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withGroups(new String[]{"Alpha"})
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        String groupName = "Alpha";
        String assignmentName = "InvalidTask";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandFailure(deleteTaskCommand, model, DeleteTaskCommand.MESSAGE_ASSIGNMENT_NOT_FOUND);

        assertThrows(CommandException.class,
                DeleteTaskCommand.MESSAGE_ASSIGNMENT_NOT_FOUND, () -> deleteTaskCommand.execute(model));
    }

    @Test
    public void execute_deleteTaskInGroupNoTask_throwsCommandExceptionWithTaskNotFoundMessage() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withGroups(new String[]{ORAL_PRESENTATION.getName().toString(),
                ALPHA.getName().toString(), BETA.getName().toString()})
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        String groupName = ORAL_PRESENTATION.getName().toString();
        String assignmentName = "InvalidTask";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandFailure(deleteTaskCommand, model, DeleteTaskCommand.MESSAGE_ASSIGNMENT_NOT_FOUND);

        assertThrows(CommandException.class,
                DeleteTaskCommand.MESSAGE_ASSIGNMENT_NOT_FOUND, () -> deleteTaskCommand.execute(model));
    }

    @Test
    public void execute_deleteTaskPersonNotInGroup_throwsCommandExceptionWithPersonNotInGroupMessage() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask).build();

        String groupName = TEAM_PROJECT.getName().toString();
        String assignmentName = "InvalidTask";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandFailure(deleteTaskCommand, model, DeleteTaskCommand.MESSAGE_INVALID_PERSON_NOT_IN_GROUP);

        assertThrows(CommandException.class,
                DeleteTaskCommand.MESSAGE_INVALID_PERSON_NOT_IN_GROUP, () -> deleteTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        final DeleteTaskCommand standardCommand = new DeleteTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final DeleteTaskCommand sameCommand = new DeleteTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final DeleteTaskCommand differentNameCommand = new DeleteTaskCommand(
                new Name(VALID_NAME_BOB), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final DeleteTaskCommand differentGroupCommand = new DeleteTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_2, new Assignment(VALID_ASSIGNMENT_1));

        final DeleteTaskCommand differentTaskCommand = new DeleteTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT));

        // same values -> returns true
        assertTrue(standardCommand.equals(sameCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different name -> returns false
        assertFalse(standardCommand.equals(differentNameCommand));

        // different group -> returns false
        assertFalse(standardCommand.equals(differentGroupCommand));

        // different task -> returns false
        assertFalse(standardCommand.equals(differentTaskCommand));
    }
}
