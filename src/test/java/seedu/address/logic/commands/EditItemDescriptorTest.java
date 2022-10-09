package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_POTATOES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_UNIT_CUCUMBERS;

import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.testutil.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptorBuilder descriptorWithSameValues = new EditItemDescriptorBuilder(DESC_POTATOES);
        assertTrue(DESC_POTATOES.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_POTATOES.equals(DESC_POTATOES));

        // null -> returns false
        assertFalse(DESC_POTATOES.equals(null));

        // different types -> returns false
        assertFalse(DESC_POTATOES.equals(5));

        // different values -> returns false
        assertFalse(DESC_POTATOES.equals(DESC_CUCUMBERS));

        // different name -> returns false
        EditItemDescriptor editedAmy = new EditItemDescriptorBuilder(DESC_POTATOES)
            .withItemName(VALID_ITEM_NAME_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_POTATOES)
            .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_POTATOES)
            .withItemUnit(VALID_ITEM_UNIT_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_POTATOES)
            .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditItemDescriptorBuilder(DESC_POTATOES)
            .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedAmy));
    }
}
