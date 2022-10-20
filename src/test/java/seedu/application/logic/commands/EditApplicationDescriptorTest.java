package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.DESC_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DATE_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_EMAIL_FACEBOOK;
import static seedu.application.logic.commands.CommandTestUtil.VALID_POSITION_FACEBOOK;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.application.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicationDescriptor descriptorWithSameValues = new EditApplicationDescriptor(DESC_GOOGLE);
        assertTrue(DESC_GOOGLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GOOGLE.equals(DESC_GOOGLE));

        // null -> returns false
        assertFalse(DESC_GOOGLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_GOOGLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_GOOGLE.equals(DESC_FACEBOOK));

        // different company -> returns false
        EditApplicationDescriptor editedGoogle = new EditApplicationDescriptorBuilder(DESC_GOOGLE)
                .withCompany(VALID_COMPANY_FACEBOOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different contact -> returns false
        editedGoogle = new EditApplicationDescriptorBuilder(DESC_GOOGLE).withContact(VALID_CONTACT_FACEBOOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different email -> returns false
        editedGoogle = new EditApplicationDescriptorBuilder(DESC_GOOGLE).withEmail(VALID_EMAIL_FACEBOOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different position -> returns false
        editedGoogle = new EditApplicationDescriptorBuilder(DESC_GOOGLE).withPosition(VALID_POSITION_FACEBOOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different date -> returns false
        editedGoogle = new EditApplicationDescriptorBuilder(DESC_GOOGLE).withDate(VALID_DATE_FACEBOOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));
    }
}
