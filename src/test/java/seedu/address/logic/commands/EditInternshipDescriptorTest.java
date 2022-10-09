package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditInternshipDescriptor descriptorWithSameValues =
                new EditCommand.EditInternshipDescriptor(DESC_GOOGLE);
        assertTrue(DESC_GOOGLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GOOGLE.equals(DESC_GOOGLE));

        // null -> returns false
        assertFalse(DESC_GOOGLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_GOOGLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_GOOGLE.equals(DESC_TIKTOK));

        // different name -> returns false
        EditCommand.EditInternshipDescriptor editedGoogle =
                new EditInternshipDescriptorBuilder(DESC_GOOGLE).withName(VALID_NAME_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different phone -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE).withPhone(VALID_PHONE_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different email -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE).withEmail(VALID_EMAIL_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different address -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE).withAddress(VALID_ADDRESS_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different tags -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));
    }
}
