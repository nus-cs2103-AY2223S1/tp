package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALILI;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteTask_success() {
        Person personToDeleteTask = model.getPersonWithName(ELLE.getName()).get(0);
        String groupName = "Alpha";
        String assignmentName = "Team Project";
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                ELLE.getName(),
                "Alpha",
                new Assignment("Team Project")
                );

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS + "\n"
                + DeleteTaskCommand.MESSAGE_ARGUMENTS,
                ELLE.getName(), groupName, assignmentName);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }
}
