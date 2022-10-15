package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class MarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalTaskList(), getTypicalInventory());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task markedTask = new TaskBuilder().withStatus(true).build();
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(model.getTaskList()), new Inventory(model.getInventory()));
        expectedModel.setTask(model.getFilteredTaskList().get(0), INDEX_FIRST_PERSON);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

}
