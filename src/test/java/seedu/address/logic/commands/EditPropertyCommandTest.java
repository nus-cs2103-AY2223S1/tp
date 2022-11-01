package seedu.address.logic.commands;

import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_NAME_PROPERTY1;
import static seedu.address.logic.commands.PropertyCommandTestUtil.VALID_PRICE_PROPERTY1;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.BuyerBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;
import seedu.address.testutil.EditPropertyDescriptorBuilder;
import seedu.address.testutil.PropertyBuilder;

class EditPropertyCommandTest {

    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    void execute_allFieldsSpecifiedUnfilteredList_success() {
        Property currentProperty = model.getFilteredPropertyList().get(0);
        Property editedProperty = new PropertyBuilder().build();

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);
        Model expectedModel = new ModelManager(new BuyerBook(
                model.getBuyerBook()), new PropertyBook(model.getPropertyBook()), new UserPrefs());

        expectedModel.setProperty(currentProperty, editedProperty);
        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_someFieldsSpecifiedUnfilteredList_success() {
        Property currentProperty = model.getFilteredPropertyList().get(0);
        PropertyBuilder propertyInList = new PropertyBuilder(currentProperty);
        Property editedProperty = propertyInList.withName(VALID_NAME_PROPERTY1)
                .withPrice(VALID_PRICE_PROPERTY1)
                .build();

        EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(editedProperty).build();
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);
        Model expectedModel = new ModelManager(new BuyerBook(
                model.getBuyerBook()), new PropertyBook(model.getPropertyBook()), new UserPrefs());

        expectedModel.setProperty(currentProperty, editedProperty);
        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPropertyCommand editPropertyCommand = new EditPropertyCommand(INDEX_FIRST_ITEM,
                new EditPropertyDescriptor());
        Property editedProperty = model.getFilteredPropertyList().get(INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new BuyerBook(
                model.getBuyerBook()), new PropertyBook(model.getPropertyBook()), new UserPrefs());

        assertCommandSuccess(editPropertyCommand, model, expectedMessage, expectedModel);
    }
}
