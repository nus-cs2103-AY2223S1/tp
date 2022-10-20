package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.phu.logic.commands.CommandTestUtil.DESC_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_APPLICATION_PROCESS_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_DATE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_EMAIL_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_NAME_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_PHONE_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_POSITION_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_REMARK_BLACKROCK;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.phu.logic.commands.CommandTestUtil.VALID_WEBSITE_BLACKROCK;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.phu.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternshipDescriptor descriptorWithSameValues = new EditInternshipDescriptor(DESC_APPLE);
        assertTrue(DESC_APPLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE.equals(DESC_APPLE));

        // null -> returns false
        assertFalse(DESC_APPLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE.equals(DESC_BLACKROCK));

        // different name -> returns false
        EditInternshipDescriptor editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE)
                .withName(VALID_NAME_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different phone -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withPhone(VALID_PHONE_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different email -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withEmail(VALID_EMAIL_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different internship -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withRemark(VALID_REMARK_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different position -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withPosition(VALID_POSITION_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different application process -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE)
                .withApplicationProcess(VALID_APPLICATION_PROCESS_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different date -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withDate(VALID_DATE_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different website -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withWebsite(VALID_WEBSITE_BLACKROCK).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withTags(VALID_TAG_TRANSPORT).build();
        assertFalse(DESC_APPLE.equals(editedApple));
    }
}
