package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearTasksCommandTest {
    @Test
    public void execute_emptyTaskList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.updateFilteredTaskList(t -> false);

        assertThrows(CommandException.class, ClearTasksCommand.MESSAGE_TASK_LIST_ALREADY_EMPTY, () ->
                new ClearTasksCommand().execute(model));
    }

    @Test
    public void execute_nonEmptyTaskList_success() {
        AddressBook addressBook = getTypicalAddressBook();
        Model model = new ModelManager(addressBook, new UserPrefs());

        addressBook.setTasks(new ArrayList<>());
        addressBook.resetAllTaskCount();
        Model expectedModel = new ModelManager(addressBook, new UserPrefs());

        assertCommandSuccess(new ClearTasksCommand(), model, ClearTasksCommand.MESSAGE_SUCCESSS, expectedModel);
    }

}
