package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.listcommands.ListAllCommand;
import seedu.address.logic.commands.listcommands.ListBuyerCommand;
import seedu.address.logic.commands.listcommands.ListCommand;
import seedu.address.logic.commands.listcommands.ListDelivererCommand;
import seedu.address.logic.commands.listcommands.ListOrderCommand;
import seedu.address.logic.commands.listcommands.ListPetCommand;
import seedu.address.logic.commands.listcommands.ListSupplierCommand;
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
    public void execute_listBuyer_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(ListBuyerCommand.MESSAGE_SUCCESS));
        assertCommandSuccess(new ListBuyerCommand(), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listSupplier_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(ListSupplierCommand.MESSAGE_SUCCESS));
        assertCommandSuccess(new ListSupplierCommand(), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listDeliverer_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(ListDelivererCommand.MESSAGE_SUCCESS));
        assertCommandSuccess(new ListDelivererCommand(), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listOrder_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(ListOrderCommand.MESSAGE_SUCCESS));
        assertCommandSuccess(new ListOrderCommand(), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listPet_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(ListPetCommand.MESSAGE_SUCCESS));
        assertCommandSuccess(new ListPetCommand(), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_listAll_success() {
        CommandResult expectedCommandResult = new CommandResult(String.format(ListAllCommand.MESSAGE_SUCCESS));
        assertCommandSuccess(new ListAllCommand(), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void equals() {
        ListCommand firstCommand = new ListBuyerCommand();
        ListCommand secondCommand = new ListDelivererCommand();

        //same object -> returns true
        assertEquals(firstCommand, firstCommand);

        //same list -> returns true
        ListCommand firstCommandCopy = new ListBuyerCommand();
        assertEquals(firstCommand, firstCommandCopy);

        //different types -> returns false
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));

        //different lists -> return false
        assertFalse(secondCommand.equals(firstCommand));
    }
}
