package seedu.address.logic.commands.grade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        assertEquals(DESC_GRADE_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_GRADE_AMY, DESC_GRADE_AMY);

        // null -> returns false
        assertNotEquals(null, DESC_GRADE_AMY);

        // different types -> returns false
        assertNotEquals(5, DESC_GRADE_AMY);

        // different values -> returns false
        assertNotEquals(DESC_GRADE_AMY, DESC_GRADE_BOB);

        // different grade -> returns false
        EditGradeDescriptor editedAmy = new EditGradeDescriptorBuilder(DESC_GRADE_AMY)
                .withGradeState(VALID_GRADE_STATE_BOB).build();
        assertNotEquals(DESC_GRADE_AMY, editedAmy);

    }
}
