package seedu.foodrem.logic.commands.itemcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.testutil.EditItemDescriptorBuilder;

public class EditItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditItemDescriptor descriptorWithSameValues = new EditItemDescriptor(CommandTestUtil.DESC_POTATOES);
        assertTrue(CommandTestUtil.DESC_POTATOES.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_POTATOES.equals(CommandTestUtil.DESC_POTATOES));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(CommandTestUtil.DESC_CUCUMBERS));

        // different name -> returns false
        EditItemDescriptor editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemName(CommandTestUtil.VALID_ITEM_NAME_CUCUMBERS).build();
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(editedPotato));

        // different quantity -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemQuantity(CommandTestUtil.VALID_ITEM_QUANTITY_CUCUMBERS).build();
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(editedPotato));

        // different unit -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemUnit(CommandTestUtil.VALID_ITEM_UNIT_CUCUMBERS).build();
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(editedPotato));

        // different bought date -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemBoughtDate(CommandTestUtil.VALID_ITEM_BOUGHT_DATE_CUCUMBERS).build();
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(editedPotato));

        // different expiry date -> returns false
        editedPotato = new EditItemDescriptorBuilder(CommandTestUtil.DESC_POTATOES)
                .withItemExpiryDate(CommandTestUtil.VALID_ITEM_EXPIRY_DATE_CUCUMBERS).build();
        assertFalse(CommandTestUtil.DESC_POTATOES.equals(editedPotato));
    }
}
