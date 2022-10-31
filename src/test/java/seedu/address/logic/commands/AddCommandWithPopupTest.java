package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.addcommands.AddCommandWithPopup;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;

public class AddCommandWithPopupTest {
    Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());

    Model dModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        //For Buyers
        String toAdd = "BUYER";
        AddCommandWithPopup command = new AddCommandWithPopup(toAdd);
        CommandResult expected = CommandResult.createAddByPopupCommandResult(String
                .format(AddCommandWithPopup.MESSAGE_SUCCESS, toAdd), toAdd);
        assertEquals(command.execute(bModel), expected);

        //For Deliverers
        toAdd = "DELIVERER";
        command = new AddCommandWithPopup(toAdd);
        expected = CommandResult.createAddByPopupCommandResult(String.format(AddCommandWithPopup
                .MESSAGE_SUCCESS, toAdd), toAdd);
        assertEquals(command.execute(bModel), expected);

        //For Suppliers
        toAdd = "SUPPLIER";
        command = new AddCommandWithPopup(toAdd);
        expected = CommandResult.createAddByPopupCommandResult(String.format(AddCommandWithPopup
                .MESSAGE_SUCCESS, toAdd), toAdd);
        assertEquals(command.execute(bModel), expected);
    }
}
