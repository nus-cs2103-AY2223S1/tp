package seedu.studmap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.studmap.logic.commands.EditCommand.EditCommandStudentEditor;
import seedu.studmap.testutil.EditStudentDescriptorBuilder;

public class EditCommandStudentEditorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommandStudentEditor descriptorWithSameValues = new EditCommandStudentEditor(DESC_AMY);
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
        EditCommandStudentEditor editedAmy =
                new EditStudentDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
