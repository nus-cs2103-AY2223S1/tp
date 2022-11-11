package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;
import static seedu.address.testutil.TypicalTeammates.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskPanel;
import seedu.address.model.UserPrefs;

public class ClearTaskCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
        expectedModel.setTaskPanel(new TaskPanel());

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ClearTaskCommand firstCommand = new ClearTaskCommand();
        ClearTaskCommand secondCommand = new ClearTaskCommand();

        // same object -> returns true
        assertEquals(firstCommand, secondCommand);

        // same values -> returns true
        ClearTaskCommand firstCommandCopy = new ClearTaskCommand();
        assertEquals(firstCommand, firstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, firstCommand);

        // null -> returns false
        assertNotEquals(null, firstCommand);

    }

}
