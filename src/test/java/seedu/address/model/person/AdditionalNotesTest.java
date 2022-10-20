package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AdditionalNotesTest {

    AdditionalNotes additionalNotes = new AdditionalNotes("HELLO");

    @Test
    public void equals(){

        // same object -> returns true
        assertTrue(additionalNotes.equals(additionalNotes));

        // same notes -> returns true
        assertTrue(additionalNotes.equals(new AdditionalNotes("HELLO")));

        // different notes -> returns false
        assertFalse(additionalNotes.equals(new AdditionalNotes("hello")));
    }

    @Test
    public void appendNotes() {
        additionalNotes.appendNotes(new AdditionalNotes("!!"));
        String expectedNotes = "HELLO !!";
        assertTrue(additionalNotes.toString().equals(expectedNotes));
    }

}