package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAXIMUM_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_KIV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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

        // different gender -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withGender(VALID_GENDER_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different graduationDate -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withGraduationDate(VALID_GRADUATION_DATE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different CAP -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different university -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withUniversity(VALID_UNIVERSITY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different major -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withMajor(VALID_MAJOR_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different job id -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withId(VALID_JOB_ID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different job title -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTitle(VALID_JOB_TITLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_KIV).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
