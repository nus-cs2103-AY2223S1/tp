package seedu.address.logic.commands.grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_STATE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.grade.GradeEditCommand.EditGradeDescriptor;
import seedu.address.testutil.EditGradeDescriptorBuilder;

public class EditGradeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditGradeDescriptor descriptorWithSameValues = new EditGradeDescriptor(DESC_GRADE_AMY);
        assertTrue(DESC_GRADE_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GRADE_AMY.equals(DESC_GRADE_AMY));

        // null -> returns false
        assertFalse(DESC_GRADE_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_GRADE_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_GRADE_AMY.equals(DESC_GRADE_BOB));

        // different grade -> returns false
        EditGradeDescriptor editedAmy = new EditGradeDescriptorBuilder(DESC_GRADE_AMY)
                .withGradeState(VALID_GRADE_STATE_BOB).build();
        assertFalse(DESC_GRADE_AMY.equals(editedAmy));

    }
}
