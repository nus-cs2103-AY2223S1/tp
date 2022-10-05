package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BREAKFAST;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LUNCH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.address.testutil.EditFoodDescriptorBuilder;

public class EditFoodDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFoodDescriptor descriptorWithSameValues = new EditFoodDescriptor("Bread", "150",
                "breakfast", "oneSlice");
        assertTrue(DESC_BREAKFAST.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BREAKFAST.equals(DESC_BREAKFAST));

        // null -> returns false
        assertFalse(DESC_BREAKFAST.equals(null));

        // different types -> returns false
        assertFalse(DESC_BREAKFAST.equals(5));

        // different values -> returns false
        assertFalse(DESC_BREAKFAST.equals(DESC_LUNCH));

        // different name -> returns false
        EditFoodDescriptor editedBreakfast = new EditFoodDescriptorBuilder(DESC_BREAKFAST)
                .withName("dumplings").build();
        assertFalse(DESC_BREAKFAST.equals(editedBreakfast));

        // different tags -> returns false
        editedBreakfast = new EditFoodDescriptorBuilder(DESC_BREAKFAST).withTags("breakfast").build();
        assertFalse(DESC_BREAKFAST.equals(editedBreakfast));
    }
}
