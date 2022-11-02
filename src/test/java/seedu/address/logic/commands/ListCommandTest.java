package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
import static seedu.address.logic.commands.ListCommand.LIST_BUYER;
import static seedu.address.logic.commands.ListCommand.LIST_DELIVERER;
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
        CommandResult expectedCommandResult = CommandResult.createListCommandResult(ListCommand.MESSAGE_SUCCESS_EMPTY,
                ListCommand.LIST_EMPTY);
        assertCommandSuccess(new ListCommand(ListCommand.LIST_EMPTY), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandResult expectedCommandResult = CommandResult.createListCommandResult(ListCommand.MESSAGE_SUCCESS_EMPTY,
                ListCommand.LIST_EMPTY);
        showBuyerAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListCommand(ListCommand.LIST_EMPTY), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listBuyer_success() {
        CommandResult expectedCommandResult = CommandResult.createListCommandResult(
                String.format(ListCommand.MESSAGE_SUCCESS, LIST_BUYER), LIST_BUYER);
        assertCommandSuccess(new ListCommand(LIST_BUYER), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void equals() {
        ListCommand firstCommand = new ListCommand(LIST_BUYER);
        ListCommand secondCommand = new ListCommand(LIST_DELIVERER);

        //same object -> returns true
        assertEquals(firstCommand, firstCommand);

        //same list -> returns true
        ListCommand firstCommandCopy = new ListCommand(LIST_BUYER);
        assertEquals(firstCommand, firstCommandCopy);

        //different types -> returns false
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));

        //different lists -> return false
        assertFalse(secondCommand.equals(firstCommand));
    }
}
