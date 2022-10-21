package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.logic.commands.CommandTestUtil.showTaskAtIndex;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;
import static swift.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swift.commons.core.Messages;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactCommand.
 */
public class ListTaskCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ListTaskCommand firstCommand = new ListTaskCommand();
        ListTaskCommand secondCommand = new ListTaskCommand();

        // same object list command -> returns true
        assertEquals(firstCommand, secondCommand);

        // null -> returns false
        assertNotEquals(firstCommand, null);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expectedMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        assertCommandSuccess(new ListTaskCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        String expectedMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        assertCommandSuccess(new ListTaskCommand(), model, expectedMessage, expectedModel);
    }
}
