package seedu.address.logic.commands;

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
}