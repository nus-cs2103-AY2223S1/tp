package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.watson.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeCommandTest {

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradeCommand(null, null));
    }

    @Test
    public void equals() {
        String subject = "CS2103T";
        String grade = "Finals";
        GradeCommand gradeCS2103TCommand = new GradeCommand(subject, grade);
        GradeCommand gradeCS2103TCommandCopy = new GradeCommand(subject, grade);

        // same object -> returns true
        assertEquals(gradeCS2103TCommand, gradeCS2103TCommand);

        // same values -> returns true
        assertEquals(gradeCS2103TCommand, gradeCS2103TCommandCopy);
    }
}
