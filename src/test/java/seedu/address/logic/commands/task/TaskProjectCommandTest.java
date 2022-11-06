package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskPanel;
import seedu.address.model.UserPrefs;


public class TaskProjectCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_valid() {
        TaskProjectCommand taskProjectCommand = new TaskProjectCommand();
        String expectedMessage = TaskProjectCommand.MESSAGE_SUCCESS + "\n1. CS2103T";
        ModelManager expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskPanel(model.getTaskPanel()), new UserPrefs());
        CommandResult commandResult = taskProjectCommand.execute(expectedModel);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }
}
