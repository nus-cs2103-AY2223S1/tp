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

public class AssignTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignTaskCommand(null, null, null));
    }

    @Test
    public void execute_assignTaskSuccess_commandSuccessful() {
        Person personToAssignTask = model.getPersonWithName(ELLE.getName()).get(0);

        String groupName = ORAL_PRESENTATION.getName().toString();
        String assignmentName = "Assignment ABC";

        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        Person expectedEditedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", groupName},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}, {assignmentName}}).build();

        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_ASSIGN_TASK_SUCCESS + "\n"
                        + AssignTaskCommand.MESSAGE_ARGUMENTS,
                ELLE.getName(), groupName, assignmentName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAssignTask, editedPerson);
        expectedModel.setPerson(personToAssignTask, expectedEditedPerson);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignTaskSuccessWithTask_commandSuccessful() {
        Person personToAssignTask = model.getPersonWithName(ELLE.getName()).get(0);

        String groupName = ORAL_PRESENTATION.getName().toString();
        String assignmentName = "Assignment ABC";

        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{groupName},
                        new String[][]{{}}).build();

        Person expectedEditedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{groupName},
                        new String[][]{{assignmentName}}).build();

        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        String expectedMessage = String.format(AssignTaskCommand.MESSAGE_ASSIGN_TASK_SUCCESS + "\n"
                        + AssignTaskCommand.MESSAGE_ARGUMENTS,
                ELLE.getName(), groupName, assignmentName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToAssignTask, editedPerson);
        expectedModel.setPerson(personToAssignTask, expectedEditedPerson);

        assertCommandSuccess(assignTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignTaskSuccessWithSameTaskName_commandSuccessful() {
        Person personToAssignTask = model.getPersonWithName(ELLE.getName()).get(0);

        String groupName = ORAL_PRESENTATION.getName().toString();
        String assignmentName = "Assignment ABC";

        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{groupName},
                        new String[][]{{assignmentName}}).build();

        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToAssignTask, editedPerson);
        assertCommandFailure(assignTaskCommand, model, AssignTaskCommand.MESSAGE_DUPLICATE_TASK);

        assertThrows(CommandException.class,
                AssignTaskCommand.MESSAGE_DUPLICATE_TASK, () -> assignTaskCommand.execute(model));
    }

    @Test
    public void execute_assignInvalidPerson_throwsCommandExceptionWithInvalidPersonMessage() {
        Person personToAssignTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", "Gamma"},
                        new String[][]{{"Team Project, Team A"}, {"Team Beta"}, {"Team Gamma"}}).build();

        String groupName = "Gamma";
        String assignmentName = "Team Gamma";
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(
                new Name("ABC"),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToAssignTask, editedPerson);
        assertCommandFailure(assignTaskCommand, model, AssignTaskCommand.MESSAGE_INVALID_PERSON);

        assertThrows(CommandException.class,
                AssignTaskCommand.MESSAGE_INVALID_PERSON, () -> assignTaskCommand.execute(model));
    }

    @Test
    public void execute_invalidGroup_throwsCommandExceptionWithInvalidGroupMessage() {
        Person personToAssignTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAssignTask)
                .withAssignments(new String[]{"Alpha", "Beta", "Gamma"},
                        new String[][]{{"Team Project, Team A"}, {"Team Beta"}, {"Team Gamma"}}).build();

        String groupName = "Gamma";
        String assignmentName = "Team Gamma";
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        model.setPerson(personToAssignTask, editedPerson);

        assertCommandFailure(assignTaskCommand, model, AssignTaskCommand.MESSAGE_INVALID_GROUP);

        assertThrows(CommandException.class,
                AssignTaskCommand.MESSAGE_INVALID_GROUP, () -> assignTaskCommand.execute(model));
    }

    @Test
    public void execute_personNotInGroup_throwsCommandExceptionWithInvalidPersonNotInGroupMessage() {
        Person personToAssignTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToAssignTask).withGroups(new String[]{"Alpha"})
                .withAssignments(new String[]{"Alpha"},
                        new String[][]{{"Team Project, Team A"}}).build();

        String groupName = TEAM_PROJECT.getName().toString();
        String assignmentName = "InvalidTask";
        AssignTaskCommand assignTaskCommand = new AssignTaskCommand(
                ELLE.getName(),
                groupName,
                new Assignment(assignmentName)
        );

        System.out.println(model.getFilteredGroupList());

        model.setPerson(personToAssignTask, editedPerson);

        assertCommandFailure(assignTaskCommand, model, AssignTaskCommand.MESSAGE_INVALID_PERSON_NOT_IN_GROUP);

        assertThrows(CommandException.class,
                AssignTaskCommand.MESSAGE_INVALID_PERSON_NOT_IN_GROUP, () -> assignTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        final AssignTaskCommand standardCommand = new AssignTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final AssignTaskCommand sameCommand = new AssignTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final AssignTaskCommand differentNameCommand = new AssignTaskCommand(
                new Name(VALID_NAME_BOB), VALID_GROUP_1, new Assignment(VALID_ASSIGNMENT_1));

        final AssignTaskCommand differentGroupCommand = new AssignTaskCommand(
                new Name(VALID_NAME_AMY), VALID_GROUP_2, new Assignment(VALID_ASSIGNMENT_1));

        final AssignTaskCommand differentTaskCommand = new AssignTaskCommand(
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
