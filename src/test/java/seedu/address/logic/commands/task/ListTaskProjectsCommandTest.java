package seedu.address.logic.commands.task;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.*;
import seedu.address.model.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

public class ListTaskProjectsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());

    @Test
    public void execute_valid() {
        ListTaskProjectsCommand listTaskProjectsCommand = new ListTaskProjectsCommand();
        String expectedMessage = "1. CS2103T\n\nListed all projects";
        ModelManager expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new TaskPanel(model.getTaskPanel()), new UserPrefs());
        CommandResult commandResult = listTaskProjectsCommand.execute(expectedModel);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }
}
