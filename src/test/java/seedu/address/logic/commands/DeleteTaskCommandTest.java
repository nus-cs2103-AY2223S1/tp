package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.testutil.TypicalPersons.ELLE;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {
    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_deleteTaskSuccess_commandSuccessful() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        Person editedPerson = new PersonBuilder(personToDeleteTask)
                .withAssignments(new String[]{"Alpha", "Beta"},
                        new String[][]{{"Team Project", "Team A"}, {"Team Beta"}}).build();

        String groupName = "Alpha";
        String assignmentName = "Team A";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                "Alpha",
                new Assignment("Team A")
                );

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS + "\n"
                + DeleteTaskCommand.MESSAGE_ARGUMENTS,
                ELLE.getName(), groupName, assignmentName);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setPerson(personToDeleteTask, editedPerson);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }
}
