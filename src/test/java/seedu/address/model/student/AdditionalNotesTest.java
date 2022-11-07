package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AdditionalNotesTest {

    private final AdditionalNotes additionalNotes = new AdditionalNotes("HELLO");
    private final AdditionalNotes emptyAdditionalNotes = new AdditionalNotes("");

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(additionalNotes.equals(additionalNotes));

        // same notes -> returns true
        assertTrue(additionalNotes.equals(new AdditionalNotes("HELLO")));

        // different notes -> returns false
        assertFalse(additionalNotes.equals(new AdditionalNotes("hello")));
    }

    @Test
    public void appendNotesToNonEmptyNotes() {
        // append to a non-empty additional notes
        additionalNotes.appendNotes(new AdditionalNotes("!!"));
        String expectedNotes = "HELLO !!";
        assertTrue(additionalNotes.toString().equals(expectedNotes));
    }

    @Test
    public void appendNotesToEmptyNotes() {
        // append to an empty additional notes
        emptyAdditionalNotes.appendNotes(new AdditionalNotes("!!"));
        String expectedNotes = "!!";
        assertTrue(emptyAdditionalNotes.toString().equals(expectedNotes));
    }
}
