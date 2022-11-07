package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LabTest {

    public static final Lab VALID_LAB1 = (Lab) new LessonBuilder().withType("lab").build();
    public static final Lab VALID_LAB2 = (Lab) new LessonBuilder().withType("lab")
            .withModule("CS2100").build();
    public static final Tutorial VALID_TUTORIAL = (Tutorial) new LessonBuilder().withType("tut").build();

    @Test
    public void getLabType_returnsCorrectType() {
        String expected = "lab";
        assertEquals(expected, VALID_LAB1.getType());
    }

    @Test
    public void getNonLabType_returnsFalse() {
        String expected = "lab";
        assertFalse(expected.equals(VALID_TUTORIAL.getType()));
    }

    @Test
    public void toString_returnsCorrectString() {
        String expected = "CS2103T Lab 14:00 to 15:00";
        assertEquals(expected, VALID_LAB1.toString());
    }

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_LAB1.typeToString();
        String expected = "Lab";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_LAB1.toFullString();
        String expected = "CS2103T Lab on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(VALID_LAB1.equals(VALID_LAB1));

        // same values -> returns true
        Lab duplicateLab = (Lab) new LessonBuilder().withType("lab").withModule("CS2103T")
                .withDay("4").withStartTime("14:00").withEndTime("15:00").build();
        assertTrue(VALID_LAB1.equals(duplicateLab));

        // different types -> returns false
        assertFalse(VALID_LAB1.equals(VALID_TUTORIAL));

        // null -> returns false
        assertFalse(VALID_LAB1.equals(null));

        // different lab lessons -> returns false
        assertFalse(VALID_LAB1.equals(VALID_LAB2));
    }

}
