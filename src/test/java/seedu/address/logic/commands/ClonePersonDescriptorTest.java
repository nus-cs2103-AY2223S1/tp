package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.C_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.C_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CloneCommand.ClonePersonDescriptor;
import seedu.address.testutil.ClonePersonDescriptorBuilder;

public class ClonePersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        ClonePersonDescriptor descriptorWithSameValues = new ClonePersonDescriptor(C_DESC_AMY);
        assertTrue(C_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(C_DESC_AMY.equals(C_DESC_AMY));

        // null -> returns false
        assertFalse(C_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(C_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(C_DESC_AMY.equals(C_DESC_BOB));

        // different name -> returns false
        ClonePersonDescriptor clonedAmy = new ClonePersonDescriptorBuilder(C_DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(C_DESC_AMY.equals(clonedAmy));

        // different phone -> returns false
        clonedAmy = new ClonePersonDescriptorBuilder(C_DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(C_DESC_AMY.equals(clonedAmy));

        // different email -> returns false
        clonedAmy = new ClonePersonDescriptorBuilder(C_DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(C_DESC_AMY.equals(clonedAmy));

        // different address -> returns false
        clonedAmy = new ClonePersonDescriptorBuilder(C_DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(C_DESC_AMY.equals(clonedAmy));

        // different tags -> returns false
        clonedAmy = new ClonePersonDescriptorBuilder(C_DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(C_DESC_AMY.equals(clonedAmy));
    }
}
