package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodrem.logic.commands.CommandTestUtil.DESC_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.DESC_POTATOES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_BOUGHT_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_EXPIRY_DATE_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_ITEM_UNIT_CUCUMBERS;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptor descriptorWithSameValues = new EditItemDescriptor(DESC_POTATOES);
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
        EditItemDescriptor editedPotato = new EditItemDescriptorBuilder(DESC_POTATOES)
                .withItemName(VALID_ITEM_NAME_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedPotato));

        // different quantity -> returns false
        editedPotato = new EditItemDescriptorBuilder(DESC_POTATOES)
                .withItemQuantity(VALID_ITEM_QUANTITY_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedPotato));

        // different unit -> returns false
        editedPotato = new EditItemDescriptorBuilder(DESC_POTATOES)
                .withItemUnit(VALID_ITEM_UNIT_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedPotato));

        // different bought date -> returns false
        editedPotato = new EditItemDescriptorBuilder(DESC_POTATOES)
                .withItemBoughtDate(VALID_ITEM_BOUGHT_DATE_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedPotato));

        // different expiry date -> returns false
        editedPotato = new EditItemDescriptorBuilder(DESC_POTATOES)
                .withItemExpiryDate(VALID_ITEM_EXPIRY_DATE_CUCUMBERS).build();
        assertFalse(DESC_POTATOES.equals(editedPotato));
    }
}
