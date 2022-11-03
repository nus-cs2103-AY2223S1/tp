package seedu.trackascholar.model.major;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidMajor_throwsIllegalArgumentException() {
        String invalidMajor = "";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajor));
    }

    @Test
    public void isValidMajor() {
        // null major name
        assertThrows(NullPointerException.class, () -> Major.isValidMajor(null));
    }

    @Test
    public void isSameMajor() {
        String firstMajor = "computer science";
        String firstMajorMixedCase = "ComPUtEr ScIEnCe";
        String secondMajor = "mathematics";
        String secondMajorMixedCase = "mAThemaTics";

        // same major
        assertTrue(new Major(firstMajor).isSameMajor(new Major(firstMajor)));
        assertTrue(new Major(secondMajor).isSameMajor(new Major(secondMajor)));

        // same major with mixed case
        assertTrue(new Major(firstMajor).isSameMajor(new Major(firstMajorMixedCase)));
        assertTrue(new Major(secondMajor).isSameMajor(new Major(secondMajorMixedCase)));

        // different major
        assertFalse(new Major(firstMajor).isSameMajor(new Major(secondMajor)));

        // different major with mixed case
        assertFalse(new Major(firstMajor).isSameMajor(new Major(secondMajorMixedCase)));
        assertFalse(new Major(secondMajor).isSameMajor(new Major(firstMajorMixedCase)));
        assertFalse(new Major(firstMajorMixedCase).isSameMajor(new Major(secondMajorMixedCase)));
    }
}
