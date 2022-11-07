package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE_SUPPLIER;
import static seedu.address.testutil.TypicalSupplyItems.GINGER;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStockCommand.EditStockDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditStockDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditStockCommandTest {
    private static final int NEW_CURRENT_STOCK_VALID = 10;
    private static final int NEW_CURRENT_STOCK_INVALID = -10;
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList(), getTypicalInventory());

    @Test
    public void execute_newStockAmountFieldSpecified_success() {
        Person supplier = new PersonBuilder(ALICE_SUPPLIER).build();
        SupplyItem editedSupplyItem = new SupplyItem(GINGER.getName(), NEW_CURRENT_STOCK_VALID,
            GINGER.getMinStock(), supplier, GINGER.getTags());
        EditStockDescriptor editedStockDescriptor = new EditStockDescriptorBuilder(editedSupplyItem).build();

        Index targetIndex = Index.fromZeroBased(1);
        EditStockCommand editStockCommand = new EditStockCommand(targetIndex, editedStockDescriptor);

        String expectedMessage = String.format(EditStockCommand.MESSAGE_SUCCESS, editedSupplyItem);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(), model.getInventory());
        expectedModel.setSupplyItem(editedSupplyItem, targetIndex);

        assertCommandSuccess(editStockCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_newStockAmountFieldNegativeInteger_failure() {
        Person supplier = new PersonBuilder(ALICE_SUPPLIER).build();
        SupplyItem editedSupplyItem = new SupplyItem(GINGER.getName(), NEW_CURRENT_STOCK_INVALID,
                GINGER.getMinStock(), supplier, GINGER.getTags());
        EditStockDescriptor editedStockDescriptor = new EditStockDescriptorBuilder(editedSupplyItem).build();

        Index targetIndex = Index.fromZeroBased(1);
        EditStockCommand editStockCommand = new EditStockCommand(targetIndex, editedStockDescriptor);

        String expectedMessage = String.format(EditStockCommand.MESSAGE_COUNT_NEGATIVE);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(), model.getInventory());
        expectedModel.setSupplyItem(editedSupplyItem, targetIndex);

        assertCommandFailure(editStockCommand, model, expectedMessage);
    }
}
