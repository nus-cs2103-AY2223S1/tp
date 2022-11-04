package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.checkcommands.CheckBuyerCommand;
import seedu.address.logic.commands.checkcommands.CheckCommand;
//import seedu.address.logic.commands.checkcommands.CheckOrderCommand;
//import seedu.address.logic.commands.checkcommands.CheckPetCommand;
import seedu.address.logic.commands.checkcommands.CheckSupplierCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;
import seedu.address.testutil.TypicalSuppliers;


public class CheckCommandTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model bExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model sModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());
    private Model sExpectedModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());
    private Model oModel = new ModelManager(TypicalOrders.getTypicalOrdersAddressBook(), new UserPrefs());
    private Model oExpectedModel = new ModelManager(TypicalOrders.getTypicalOrdersAddressBook(), new UserPrefs());
    private Model pModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
    private Model pExpectedModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CheckCommand firstCommand = new CheckBuyerCommand(INDEX_FIRST);
        CheckCommand secondCommand = new CheckBuyerCommand(INDEX_SECOND);
        CheckCommand thirdCommand = new CheckSupplierCommand(INDEX_FIRST);

        //same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        //same values -> returns true
        CheckCommand firstCommandCopy = new CheckBuyerCommand(INDEX_FIRST);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different types -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different index -> returns false
        assertFalse(firstCommand.equals(thirdCommand));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        CheckCommand command = new CheckBuyerCommand(Index.fromOneBased(100));
        assertThrows(CommandException.class, () -> command.execute(bModel));
    }

    @Test
    public void execute() {
        String checkType = "BUYER";
        CommandResult expected = new CommandResult(String.format(CheckBuyerCommand.MESSAGE_SUCCESS,
                        INDEX_FIRST.getOneBased()));
        CheckCommand command = new CheckBuyerCommand(INDEX_FIRST);

        // buyer -> success
        assertCommandSuccess(command, bModel, expected, bExpectedModel);

        // supplier -> success
        expected = new CommandResult(String.format(CheckSupplierCommand.MESSAGE_SUCCESS,
                INDEX_FIRST.getOneBased()));
        command = new CheckSupplierCommand(INDEX_FIRST);
        assertCommandSuccess(command, sModel, expected, sExpectedModel);

        // order -> success
        //expected = new CommandResult(String.format(CheckOrderCommand.MESSAGE_SUCCESS,
        //        INDEX_FIRST.getOneBased()));
        //command = new CheckOrderCommand(INDEX_FIRST);
        //assertCommandSuccess(command, oModel, expected, oExpectedModel);

        // pet -> success
        //expected = new CommandResult(String.format(CheckPetCommand.MESSAGE_SUCCESS,
        //        INDEX_FIRST.getOneBased()));
        //command = new CheckPetCommand(INDEX_FIRST);
        //assertCommandSuccess(command, pModel, expected, pExpectedModel);
    }
}
