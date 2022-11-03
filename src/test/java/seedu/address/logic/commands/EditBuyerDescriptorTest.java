package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_DESIRED_CHARACTERISTICS_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRICE_RANGE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PRIORITY_LOW;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.buyer.EditBuyerCommand;
import seedu.address.logic.commands.buyer.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

public class EditBuyerDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditBuyerDescriptor descriptorWithSameValues = new EditBuyerCommand
                .EditBuyerDescriptor(DESC_AMY);
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
        EditBuyerDescriptor editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different price range -> returns false
        editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY).withPriceRange(VALID_PRICE_RANGE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different desired characteristics -> returns false
        editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY)
                .withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditBuyerDescriptorBuilder(DESC_AMY).withPriority(VALID_PRIORITY_LOW).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
