package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.checkcommands.CheckCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.checkcommands.CheckCommand;
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
        CheckCommand firstCommand = new CheckCommand("BUYER", INDEX_FIRST);
        CheckCommand secondCommand = new CheckCommand("BUYER", INDEX_SECOND);
        CheckCommand thirdCommand = new CheckCommand("SUPPLIER", INDEX_FIRST);

        //same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        //same values -> returns true
        CheckCommand firstCommandCopy = new CheckCommand("BUYER", INDEX_FIRST);
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
        CheckCommand command = new CheckCommand("BUYER", Index.fromOneBased(100));
        assertThrows(CommandException.class, () -> command.execute(bModel));
    }

    @Test
    public void execute() {
        String checkType = "BUYER";
        CommandResult expected = CommandResult.createCheckCommandResult(String.format(MESSAGE_SUCCESS, checkType,
                        INDEX_FIRST.getOneBased()), checkType, INDEX_FIRST);
        CheckCommand command = new CheckCommand(checkType, INDEX_FIRST);

        // buyer -> success
        assertCommandSuccess(command, bModel, expected, bExpectedModel);

        // supplier -> success
        checkType = "SUPPLIER";
        expected = CommandResult.createCheckCommandResult(String.format(MESSAGE_SUCCESS, checkType,
                INDEX_FIRST.getOneBased()), checkType, INDEX_FIRST);
        command = new CheckCommand(checkType, INDEX_FIRST);
        assertCommandSuccess(command, sModel, expected, sExpectedModel);

        // order -> success
        checkType = "ORDER";
        expected = CommandResult.createCheckCommandResult(String.format(MESSAGE_SUCCESS, checkType,
                INDEX_FIRST.getOneBased()), checkType, INDEX_FIRST);
        command = new CheckCommand(checkType, INDEX_FIRST);
        assertCommandSuccess(command, oModel, expected, oExpectedModel);

        // pet -> success
        checkType = "PET";
        expected = CommandResult.createCheckCommandResult(String.format(MESSAGE_SUCCESS, checkType,
                INDEX_FIRST.getOneBased()), checkType, INDEX_FIRST);
        command = new CheckCommand(checkType, INDEX_FIRST);
        assertCommandSuccess(command, pModel, expected, pExpectedModel);

        // invalid check type -> do nothing
        checkType = "SOME CHECK TYPE";
        expected = CommandResult.createCheckCommandResult(String.format(MESSAGE_SUCCESS, checkType,
                INDEX_FIRST.getOneBased()), checkType, INDEX_FIRST);
        command = new CheckCommand(checkType, INDEX_FIRST);
        assertCommandSuccess(command, pModel, expected, pExpectedModel);
    }
}
