package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.clinkedin.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.clinkedin.logic.commands.CommandTestUtil.VALID_STATUS_BOB;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.clinkedin.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different status -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withStatus(VALID_STATUS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different note -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withNote(VALID_NOTE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        // To be implemented for tags
        //        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        //        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
