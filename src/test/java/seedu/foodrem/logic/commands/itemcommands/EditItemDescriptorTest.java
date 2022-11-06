package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;

/**
 * A class to test the EditItemDescriptor.
 */
public class EditItemDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptor descriptorWithSameValues = new EditItemDescriptor(CommandTestUtil.DESC_POTATOES);
        assertEquals(CommandTestUtil.DESC_POTATOES, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(CommandTestUtil.DESC_POTATOES, CommandTestUtil.DESC_POTATOES);

        // null -> returns false
        assertNotEquals(null, CommandTestUtil.DESC_POTATOES);

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(5));

        // different values -> returns false
        assertNotEquals(CommandTestUtil.DESC_POTATOES, CommandTestUtil.DESC_CUCUMBERS);

        // different name -> returns false
        EditItemDescriptor editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS).build();
        assertNotEquals(CommandTestUtil.DESC_POTATOES, editedPotato);

        // different quantity -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS).build();
        assertNotEquals(CommandTestUtil.DESC_POTATOES, editedPotato);

        // different unit -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemUnit(CommandTestUtil.VALID_ITEM_UNIT_CUCUMBERS).build();
        assertNotEquals(CommandTestUtil.DESC_POTATOES, editedPotato);

        // different bought date -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_CUCUMBERS).build();
        assertNotEquals(CommandTestUtil.DESC_POTATOES, editedPotato);

        // different expiry date -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_CUCUMBERS).build();
        assertNotEquals(CommandTestUtil.DESC_POTATOES, editedPotato);

        // different price -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemPrice(CommandTestUtil.VALID_ITEM_PRICE_CUCUMBERS).build();
        assertNotEquals(CommandTestUtil.DESC_POTATOES, editedPotato);
    }
}
