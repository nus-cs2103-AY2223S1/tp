package seedu.workbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.workbook.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.workbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.workbook.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.workbook.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternshipDescriptor descriptorWithSameValues = new EditInternshipDescriptor(DESC_AMY);
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
        EditInternshipDescriptor editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY)
                .withCompany(VALID_COMPANY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));


        // different tags -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
