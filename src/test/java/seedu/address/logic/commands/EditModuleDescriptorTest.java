package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.testutil.EditModuleDescriptorBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_DETAILS_CS2100;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(DESC_CS2103T);
        assertTrue(DESC_CS2103T.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2103T.equals(DESC_CS2103T));

        // null -> returns false
        assertFalse(DESC_CS2103T.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2103T.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2103T.equals(DESC_CS2100));

        // different name -> returns false
        EditModuleDescriptor editedCS2103T = new EditModuleDescriptorBuilder(DESC_CS2103T).withModuleCode(VALID_MODULE_CODE_CS2100).build();
        assertFalse(DESC_CS2103T.equals(editedCS2103T));

        // different phone -> returns false
        editedCS2103T = new EditModuleDescriptorBuilder(DESC_CS2103T).withLectureDetails(VALID_LECTURE_DETAILS_CS2100).build();
        assertFalse(DESC_CS2103T.equals(editedCS2103T));

        // different email -> returns false
        editedCS2103T = new EditModuleDescriptorBuilder(DESC_CS2103T).withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2100).build();
        assertFalse(DESC_CS2103T.equals(editedCS2103T));

        // different tags -> returns false
        editedCS2103T = new EditModuleDescriptorBuilder(DESC_CS2103T).withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_HARD).build();
        assertFalse(DESC_CS2103T.equals(editedCS2103T));
    }
}
