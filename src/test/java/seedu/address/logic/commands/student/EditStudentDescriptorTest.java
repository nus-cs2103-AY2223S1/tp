package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STUDENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STUDENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.StudentEditCommand.EditStudentDescriptor;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_STUDENT_AMY);
        assertTrue(DESC_STUDENT_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_STUDENT_AMY.equals(DESC_STUDENT_AMY));

        // null -> returns false
        assertFalse(DESC_STUDENT_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_STUDENT_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_STUDENT_AMY.equals(DESC_STUDENT_BOB));

        // different name -> returns false
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_STUDENT_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_STUDENT_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_STUDENT_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_STUDENT_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_STUDENT_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_STUDENT_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_STUDENT_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_STUDENT_AMY.equals(editedAmy));
    }
}
