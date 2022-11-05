package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_OFFER_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_OFFER_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_2;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_2;

import org.junit.jupiter.api.Test;

import seedu.realtime.testutil.EditOfferDescriptorBuilder;

public class EditOfferDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditOfferCommand.EditOfferDescriptor descriptorWithSameValues = new EditOfferCommand.EditOfferDescriptor(DESC_OFFER_AMY);
        assertTrue(DESC_OFFER_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_OFFER_AMY.equals(DESC_OFFER_AMY));

        // null -> returns false
        assertFalse(DESC_OFFER_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_OFFER_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_OFFER_AMY.equals(DESC_OFFER_BOB));

        // different name -> returns false
        EditOfferCommand.EditOfferDescriptor editedAmy = new EditOfferDescriptorBuilder(DESC_OFFER_AMY).withBuyer(VALID_NAME_BOB).build();
        assertFalse(DESC_OFFER_AMY.equals(editedAmy));

        // different offer price -> returns false
        editedAmy = new EditOfferDescriptorBuilder(DESC_OFFER_AMY).withOfferPrice(VALID_PRICE_2).build();
        assertFalse(DESC_OFFER_AMY.equals(editedAmy));

        // different listing ID -> returns false
        editedAmy = new EditOfferDescriptorBuilder(DESC_OFFER_AMY).withListing(VALID_LISTING_ID_2).build();
        assertFalse(DESC_OFFER_AMY.equals(editedAmy));


    }
}
