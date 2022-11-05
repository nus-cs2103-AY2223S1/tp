package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class TutorialTest {

    public static final Tutorial VALID_TUTORIAL = (Tutorial) new LessonBuilder().withType("tut").build();

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_TUTORIAL.typeToString();
        String expected = "Tutorial";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_TUTORIAL.toFullString();
        String expected = "CS2103T Tutorial on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

}
