package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class RecitationTest {

    public static final Recitation VALID_RECITATION = (Recitation) new LessonBuilder().withType("rec").build();

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_RECITATION.typeToString();
        String expected = "Recitation";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_RECITATION.toFullString();
        String expected = "CS2103T Recitation on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

}
