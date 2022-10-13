package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_BOB;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.phu.testutil.EditInternshipDescriptorBuilder;

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
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different internship -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withRemark(VALID_REMARK_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different position -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withPosition(VALID_POSITION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different application process -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY)
                .withApplicationProcess(VALID_APPLICATION_PROCESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different date -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withDate(VALID_DATE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different website -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withWebsite(VALID_WEBSITE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditInternshipDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
