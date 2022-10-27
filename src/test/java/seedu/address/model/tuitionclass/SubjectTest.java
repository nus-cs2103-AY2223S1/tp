package seedu.address.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tuitionclass.exceptions.InvalidSubjectException;

public class SubjectTest {

    @Test
    public void createSubject_invalidSubject_throwsInvalidSubjectException() {
        String invalidSubject = "socialstudies";
        assertThrows(InvalidSubjectException.class, () -> Subject.createSubject(invalidSubject));
    }

    @Test
    public void createSubject_validSubject_success() {
        assertTrue(Subject.createSubject("ENGLISH") == Subject.ENGLISH);
        assertTrue(Subject.createSubject("MATHEMATICS") == Subject.MATHEMATICS);
        assertTrue(Subject.createSubject("PHYSICS") == Subject.PHYSICS);
        assertTrue(Subject.createSubject("CHEMISTRY") == Subject.CHEMISTRY);
        assertTrue(Subject.createSubject("BIOLOGY") == Subject.BIOLOGY);
    }
}
