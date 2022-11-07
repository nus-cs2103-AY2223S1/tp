package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_LISTING_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_LISTING_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_2;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_TAG_GARDEN;

import org.junit.jupiter.api.Test;

import seedu.realtime.logic.commands.EditListingCommand.EditListingDescriptor;
import seedu.realtime.testutil.EditListingDescriptorBuilder;

public class EditListingDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditListingDescriptor descriptorWithSameValues = new EditListingDescriptor(DESC_LISTING_AMY);
        assertTrue(DESC_LISTING_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_LISTING_AMY.equals(DESC_LISTING_AMY));

        // null -> returns false
        assertFalse(DESC_LISTING_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_LISTING_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_LISTING_AMY.equals(DESC_LISTING_BOB));

        // different name -> returns false
        EditListingDescriptor editedAmy = new EditListingDescriptorBuilder(DESC_LISTING_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_LISTING_AMY.equals(editedAmy));

        // different id -> returns false
        editedAmy = new EditListingDescriptorBuilder(DESC_LISTING_AMY).withId(VALID_ID_BOB).build();
        assertFalse(DESC_LISTING_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditListingDescriptorBuilder(DESC_LISTING_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_LISTING_AMY.equals(editedAmy));

        // different asking price -> returns false
        editedAmy = new EditListingDescriptorBuilder(DESC_LISTING_AMY).withAskingPrice(VALID_PRICE_2).build();
        assertFalse(DESC_LISTING_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditListingDescriptorBuilder(DESC_LISTING_AMY).withTags(VALID_TAG_GARDEN).build();
        assertFalse(DESC_LISTING_AMY.equals(editedAmy));
    }
}
