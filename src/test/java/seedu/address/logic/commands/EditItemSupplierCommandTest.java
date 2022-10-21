package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalPersons.ALICE_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.BENSON_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.CARL_SUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithSuppliers;
import static seedu.address.testutil.TypicalSupplyItems.BEEF;
import static seedu.address.testutil.TypicalSupplyItems.GINGER;
import static seedu.address.testutil.TypicalSupplyItems.LAMB;
import static seedu.address.testutil.TypicalSupplyItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class EditItemSupplierCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithSuppliers(),
            new UserPrefs(), new TaskList(), getTypicalInventory());

    @Test
    public void execute_supplierSupplyingItemNameChange_success() {
        Person editedPerson = new PersonBuilder(ALICE_SUPPLIER).withName("Alicia").build();
        SupplyItem expectedSupplyItem = new SupplyItem(GINGER.getName(), GINGER.getCurrentStock(),
                GINGER.getMinStock(), editedPerson, GINGER.getTags());
        EditItemSupplierCommand editItemSupplierCommand = new EditItemSupplierCommand(editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(), getTypicalInventory());
        expectedModel.setSupplyItem(expectedSupplyItem, INDEX_SECOND_SUPPLY_ITEM);
        String expectedMessage = EditItemSupplierCommand.MESSAGE_EDIT_ITEM_SUCCESS;

        assertCommandSuccess(editItemSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_supplierSupplyingItemItemChange_success() {
        Person editedPerson = new PersonBuilder(BENSON_SUPPLIER).withItem("Veal").build();
        SupplyItem expectedSupplyItem = new SupplyItem("Veal", BEEF.getCurrentStock(),
                BEEF.getMinStock(), editedPerson, BEEF.getTags());
        EditItemSupplierCommand editItemSupplierCommand = new EditItemSupplierCommand(editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(), getTypicalInventory());
        expectedModel.setSupplyItem(expectedSupplyItem, INDEX_THIRD_SUPPLY_ITEM);
        String expectedMessage = EditItemSupplierCommand.MESSAGE_EDIT_ITEM_SUCCESS;

        assertCommandSuccess(editItemSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_supplierSupplyingItemPriceChange_success() {
        Person editedPerson = new PersonBuilder(CARL_SUPPLIER).withPrice("$1.20").build();
        SupplyItem expectedSupplyItem = new SupplyItem(LAMB.getName(), LAMB.getCurrentStock(),
                LAMB.getMinStock(), editedPerson, LAMB.getTags());
        EditItemSupplierCommand editItemSupplierCommand = new EditItemSupplierCommand(editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(), getTypicalInventory());
        expectedModel.setSupplyItem(expectedSupplyItem, INDEX_FOURTH_SUPPLY_ITEM);
        String expectedMessage = EditItemSupplierCommand.MESSAGE_EDIT_ITEM_SUCCESS;

        assertCommandSuccess(editItemSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_supplierSupplyingItemAllChange_success() {
        Person editedPerson = new PersonBuilder(ALICE_SUPPLIER)
                .withName("Alicia").withItem("Garlic").withPrice("$1.20").build();
        SupplyItem expectedSupplyItem = new SupplyItem("Garlic", GINGER.getCurrentStock(),
                GINGER.getMinStock(), editedPerson, GINGER.getTags());
        EditItemSupplierCommand editItemSupplierCommand = new EditItemSupplierCommand(editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs(), new TaskList(), getTypicalInventory());
        expectedModel.setSupplyItem(expectedSupplyItem, INDEX_SECOND_SUPPLY_ITEM);
        String expectedMessage = EditItemSupplierCommand.MESSAGE_EDIT_ITEM_SUCCESS;

        assertCommandSuccess(editItemSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSupplyItemItemChange_failure() {
        Person editedPerson = new PersonBuilder(ALICE_SUPPLIER).withItem("Beef").build();
        EditItemSupplierCommand editItemSupplierCommand = new EditItemSupplierCommand(editedPerson);
        String expectedMessage = EditItemSupplierCommand.MESSAGE_DUPLICATE_ITEM;

        assertCommandFailure(editItemSupplierCommand, model, expectedMessage);
    }

}
