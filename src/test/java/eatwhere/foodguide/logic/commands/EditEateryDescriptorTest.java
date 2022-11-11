package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.DESC_AMY;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.DESC_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_CUISINE_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.EditCommand.EditEateryDescriptor;
import eatwhere.foodguide.testutil.EditEateryDescriptorBuilder;

public class EditEateryDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEateryDescriptor descriptorWithSameValues = new EditEateryDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditEateryDescriptor editedAmy = new EditEateryDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditEateryDescriptorBuilder(DESC_AMY).withPhone(VALID_PRICE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditEateryDescriptorBuilder(DESC_AMY).withEmail(VALID_CUISINE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditEateryDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditEateryDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
