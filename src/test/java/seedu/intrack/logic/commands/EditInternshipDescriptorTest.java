package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.DESC_AAPL;
import static seedu.intrack.logic.commands.CommandTestUtil.DESC_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_EMAIL_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_NAME_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_PHONE_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_POSITION_MSFT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_WEBSITE_MSFT;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.intrack.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternshipDescriptor descriptorWithSameValues = new EditInternshipDescriptor(DESC_AAPL);
        assertTrue(DESC_AAPL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AAPL.equals(DESC_AAPL));

        // null -> returns false
        assertFalse(DESC_AAPL.equals(null));

        // different types -> returns false
        assertFalse(DESC_AAPL.equals(5));

        // different values -> returns false
        assertFalse(DESC_AAPL.equals(DESC_MSFT));

        // different name -> returns false
        EditInternshipDescriptor editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withName(VALID_NAME_MSFT)
                .build();
        assertFalse(DESC_AAPL.equals(editedAapl));

        // different position -> returns false
        editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withPosition(VALID_POSITION_MSFT).build();
        assertFalse(DESC_AAPL.equals(editedAapl));

        // different phone -> returns false
        editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withPhone(VALID_PHONE_MSFT).build();
        assertFalse(DESC_AAPL.equals(editedAapl));

        // different email -> returns false
        editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withEmail(VALID_EMAIL_MSFT).build();
        assertFalse(DESC_AAPL.equals(editedAapl));

        // different position -> returns false
        // editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withStatus(VALID_STATUS_MSFT).build();
        // assertFalse(DESC_AAPL.equals(editedAapl));

        // different address -> returns false
        editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withWebsite(VALID_WEBSITE_MSFT).build();
        assertFalse(DESC_AAPL.equals(editedAapl));

        // different tags -> returns false
        editedAapl = new EditInternshipDescriptorBuilder(DESC_AAPL).withTags(VALID_TAG_URGENT).build();
        assertFalse(DESC_AAPL.equals(editedAapl));
    }
}
