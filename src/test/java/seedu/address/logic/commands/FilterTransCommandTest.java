package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.transaction.TransactionLog;

class FilterTransCommandTest {
    private TransactionLog transactionLog = new TransactionLog();
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getJeeqTracker(), new UserPrefs());
    }

    @Test
    public void execute_buyFilteredList_success() {
        assertCommandSuccess(new FilterTransCommand(true), model,
                String.format(FilterTransCommand.MESSAGE_SUCCESS, "buy"), expectedModel);
    }

    @Test
    public void execute_sellFilteredList_success() {
        assertCommandSuccess(new FilterTransCommand(false), model,
                String.format(FilterTransCommand.MESSAGE_SUCCESS, "sell"), expectedModel);
    }

    @Test
    public void equals() {
        final FilterTransCommand command = new FilterTransCommand(true);

        // same object
        assertTrue(command.equals(command));

        // another object
        assertFalse(command.equals(new EditCommandTest()));
        // same values -> returns true
        assertTrue(command.equals(new FilterTransCommand(true)));

        assertFalse(command.equals(new FilterTransCommand(false)));

        final FilterTransCommand command2 = new FilterTransCommand(false);

        // same values -> returns true
        assertTrue(command2.equals(new FilterTransCommand(false)));

        assertFalse(command2.equals(new FilterTransCommand(true)));
    }
}
