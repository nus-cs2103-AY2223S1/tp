package seedu.condonery.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_DESC_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_ADDRESS_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_NAME_WHISTLER;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_VALID_TAG_WHISTLER;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;

public class EditPropertyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPropertyDescriptor descriptorWithSameValues = new EditPropertyDescriptor(PROPERTY_DESC_SCOTTS);
        assertTrue(PROPERTY_DESC_SCOTTS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(PROPERTY_DESC_SCOTTS.equals(PROPERTY_DESC_SCOTTS));

        // null -> returns false
        assertFalse(PROPERTY_DESC_SCOTTS.equals(null));

        // different types -> returns false
        assertFalse(PROPERTY_DESC_SCOTTS.equals(5));

        // different values -> returns false
        assertFalse(PROPERTY_DESC_SCOTTS.equals(PROPERTY_DESC_WHISTLER));

        // different name -> returns false
        EditPropertyDescriptor editedScottsProperty =
                new EditPropertyDescriptorBuilder(PROPERTY_DESC_SCOTTS)
                        .withName(PROPERTY_VALID_NAME_WHISTLER)
                        .build();
        assertFalse(PROPERTY_DESC_SCOTTS.equals(editedScottsProperty));

        // different address -> returns false
        editedScottsProperty =
                new EditPropertyDescriptorBuilder(PROPERTY_DESC_SCOTTS)
                        .withAddress(PROPERTY_VALID_ADDRESS_WHISTLER)
                        .build();
        assertFalse(PROPERTY_DESC_SCOTTS.equals(editedScottsProperty));

        // different tags -> returns false
        editedScottsProperty =
                new EditPropertyDescriptorBuilder(PROPERTY_DESC_SCOTTS)
                .withTags(PROPERTY_VALID_TAG_WHISTLER)
                .build();
        assertFalse(PROPERTY_DESC_SCOTTS.equals(editedScottsProperty));
    }
}
