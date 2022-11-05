package seedu.address.model.grade;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;


class GradeTest {

    @Test
    void stringT_isValidDescription() {
        assertTrue(Grade.isValidDescription("T"));
    }

    @Test
    void stringF_isValidDescription() {
        assertTrue(Grade.isValidDescription("F"));
    }

    @Test
    void stringOther_isValidDescription() {
        String testString = Long.toHexString(new Random().nextLong());
        while (testString.equals("T") || testString.equals("F")) {
            testString = Long.toHexString(new Random().nextLong());
        }
        assertFalse(Grade.isValidDescription(testString));
    }

    @Test
    void values() {
        Grade[] grades = {Grade.GRADED, Grade.UNGRADED};
        assertArrayEquals(grades, Grade.values());
    }

    @Test
    void graded_valueOf() {
        assertEquals(Grade.GRADED, Grade.valueOf("GRADED"));
    }

    @Test
    void ungraded_valueOf() {
        assertEquals(Grade.UNGRADED, Grade.valueOf("UNGRADED"));
    }

    @Test
    void special_valueOf() {
        String testString = Long.toHexString(new Random().nextLong());
        while (testString.equals("GRADED") || testString.equals("UNGRADED")) {
            testString = Long.toHexString(new Random().nextLong());
        }
        String finalTestString = testString;
        assertThrows(IllegalArgumentException.class, () -> Grade.valueOf(finalTestString));
    }
}
