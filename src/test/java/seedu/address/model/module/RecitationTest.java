package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class RecitationTest {

    public static final Recitation VALID_RECITATION1 = (Recitation) new LessonBuilder().withType("rec").build();
    public static final Recitation VALID_RECITATION2 = (Recitation) new LessonBuilder().withType("rec")
            .withModule("CS2100").build();
    public static final Tutorial VALID_TUTORIAL = (Tutorial) new LessonBuilder().withType("tut").build();

    @Test
    public void getRecitationType_returnsCorrectType() {
        String expected = "rec";
        assertEquals(expected, VALID_RECITATION1.getType());
    }

    @Test
    public void getNonRecitationType_returnsFalse() {
        String expected = "rec";
        assertFalse(expected.equals(VALID_TUTORIAL.getType()));
    }

    @Test
    public void toString_returnsCorrectString() {
        String expected = "CS2103T Recitation 14:00 to 15:00";
        assertEquals(expected, VALID_RECITATION1.toString());
    }

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_RECITATION1.typeToString();
        String expected = "Recitation";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_RECITATION1.toFullString();
        String expected = "CS2103T Recitation on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(VALID_RECITATION1.equals(VALID_RECITATION1));

        // same values -> returns true
        Recitation duplicateRecitation = (Recitation) new LessonBuilder().withType("rec").withModule("CS2103T")
                .withDay("4").withStartTime("14:00").withEndTime("15:00").build();
        assertTrue(VALID_RECITATION1.equals(duplicateRecitation));

        // different types -> returns false
        assertFalse(VALID_RECITATION1.equals(VALID_TUTORIAL));

        // null -> returns false
        assertFalse(VALID_RECITATION1.equals(null));

        // different recitation lessons -> returns false
        assertFalse(VALID_RECITATION1.equals(VALID_RECITATION2));
    }

}
