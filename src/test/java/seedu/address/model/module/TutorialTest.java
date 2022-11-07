package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class TutorialTest {

    public static final Tutorial VALID_TUTORIAL1 = (Tutorial) new LessonBuilder().withType("tut").build();
    public static final Tutorial VALID_TUTORIAL2 = (Tutorial) new LessonBuilder().withType("tut")
            .withModule("CS2100").build();
    public static final Lab VALID_LAB = (Lab) new LessonBuilder().withType("lab").build();

    @Test
    public void getTutorialType_returnsCorrectType() {
        String expected = "tut";
        assertEquals(expected, VALID_TUTORIAL1.getType());
    }

    @Test
    public void getNonTutorialType_returnsFalse() {
        String expected = "tut";
        assertFalse(expected.equals(VALID_LAB.getType()));
    }

    @Test
    public void toString_returnsCorrectString() {
        String expected = "CS2103T Tutorial 14:00 to 15:00";
        assertEquals(expected, VALID_TUTORIAL1.toString());
    }

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_TUTORIAL1.typeToString();
        String expected = "Tutorial";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_TUTORIAL1.toFullString();
        String expected = "CS2103T Tutorial on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(VALID_TUTORIAL1.equals(VALID_TUTORIAL1));

        // same values -> returns true
        Tutorial duplicateTutorial = (Tutorial) new LessonBuilder().withType("tut").withModule("CS2103T")
                .withDay("4").withStartTime("14:00").withEndTime("15:00").build();
        assertTrue(VALID_TUTORIAL1.equals(duplicateTutorial));

        // different types -> returns false
        assertFalse(VALID_TUTORIAL1.equals(VALID_LAB));

        // null -> returns false
        assertFalse(VALID_TUTORIAL1.equals(null));

        // different tutorial lessons -> returns false
        assertFalse(VALID_TUTORIAL1.equals(VALID_TUTORIAL2));
    }

}
