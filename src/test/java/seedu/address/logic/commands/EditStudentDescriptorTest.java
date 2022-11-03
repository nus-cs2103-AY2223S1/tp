package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STU_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STU_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_STU_AMY);
        assertTrue(DESC_STU_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_STU_AMY.equals(DESC_STU_AMY));

        // null -> returns false
        assertFalse(DESC_STU_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_STU_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_STU_AMY.equals(DESC_STU_BOB));

        // different name -> returns false
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_STU_AMY).withName(VALID_NAME_BOB)
                .build();
        assertFalse(DESC_STU_AMY.equals(editedAmy));

        // different telegram -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_STU_AMY).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(DESC_STU_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_STU_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_STU_AMY.equals(editedAmy));

        // different attendance -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_STU_AMY).withAttendance(VALID_ATTENDANCE_BOB).build();
        assertFalse(DESC_STU_AMY.equals(editedAmy));
    }
}
