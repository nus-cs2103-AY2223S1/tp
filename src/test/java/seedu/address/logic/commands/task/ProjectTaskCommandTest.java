package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskPanel;
import seedu.address.model.UserPrefs;


public class ProjectTaskCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_valid() {
        ProjectTaskCommand projectTaskCommand = new ProjectTaskCommand();
        String expectedMessage = ProjectTaskCommand.MESSAGE_SUCCESS + "\n1. CS2103T";
        ModelManager expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskPanel(model.getTaskPanel()), new UserPrefs());
        CommandResult commandResult = projectTaskCommand.execute(expectedModel);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }
}
