package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLIED_DATE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BACKEND;

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
                new EditInternshipDescriptorBuilder(DESC_GOOGLE).withCompany(VALID_COMPANY_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different phone -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE).withLink(VALID_LINK_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different email -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE)
                .withDescription(VALID_DESCRIPTION_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different address -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE)
                .withAppliedDate(VALID_APPLIED_DATE_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different tags -> returns false
        editedGoogle = new EditInternshipDescriptorBuilder(DESC_GOOGLE).withTags(VALID_TAG_BACKEND).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));
    }
}
