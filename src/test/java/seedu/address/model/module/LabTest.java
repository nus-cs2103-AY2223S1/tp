package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LabTest {

    public static final Lab VALID_LAB = (Lab) new LessonBuilder().withType("lab").build();

    @Test
    public void typeToString_returnsCorrectType() {
        String validLabType = VALID_LAB.typeToString();
        String expected = "Lab";
        assertEquals(expected, validLabType);
    }

    @Test
    public void toFullString_returnsCorrectFullString() {
        String validLabFullString = VALID_LAB.toFullString();
        String expected = "CS2103T Lab on Thursday 14:00 to 15:00";
        assertEquals(expected, validLabFullString);
    }

}
