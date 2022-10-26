package seedu.classify.model.tag;

import static seedu.classify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExamTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Exam(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidExam = "";
        assertThrows(IllegalArgumentException.class, () -> new Exam(invalidExam));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Exam.isValidFormat(null));
    }

}
