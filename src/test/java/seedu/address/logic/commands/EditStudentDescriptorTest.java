package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_AMY_STUDENT);
        assertTrue(DESC_AMY_STUDENT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_STUDENT.equals(DESC_AMY_STUDENT));

        // null -> returns false
        assertFalse(DESC_AMY_STUDENT.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_STUDENT.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_STUDENT.equals(DESC_BOB_STUDENT));

        // different name -> returns false
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_AMY_STUDENT)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY_STUDENT.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY_STUDENT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_STUDENT.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY_STUDENT).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY_STUDENT.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY_STUDENT).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY_STUDENT.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY_STUDENT).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY_STUDENT.equals(editedAmy));
    }
}
