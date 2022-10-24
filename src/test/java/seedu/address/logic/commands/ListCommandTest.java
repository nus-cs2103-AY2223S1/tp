package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBuyers;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_EMPTY, false,
                false, true, ListCommand.LIST_EMPTY);
        assertCommandSuccess(new ListCommand(ListCommand.LIST_EMPTY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_SUCCESS_EMPTY, false,
                false, true, ListCommand.LIST_EMPTY);
        showBuyerAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListCommand(ListCommand.LIST_EMPTY), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listBuyer_success() {
        CommandResult expectedCommandResult = new CommandResult(
                String.format(ListCommand.MESSAGE_SUCCESS, ListCommand.LIST_BUYER),
                false,
                false, true, ListCommand.LIST_BUYER);
        assertCommandSuccess(new ListCommand(ListCommand.LIST_BUYER), model, expectedCommandResult,
                expectedModel);
    }
}
