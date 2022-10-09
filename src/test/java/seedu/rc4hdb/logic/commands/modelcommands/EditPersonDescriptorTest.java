package seedu.rc4hdb.logic.commands.modelcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.DESC_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_EMAIL_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_NAME_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_PHONE_BOB;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.modelcommands.EditCommand.EditResidentDescriptor;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditResidentDescriptor descriptorWithSameValues = new EditResidentDescriptor(DESC_AMY);
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
        EditResidentDescriptor editedAmy = new ResidentDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new ResidentDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new ResidentDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new ResidentDescriptorBuilder(DESC_AMY).withRoom(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new ResidentDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
