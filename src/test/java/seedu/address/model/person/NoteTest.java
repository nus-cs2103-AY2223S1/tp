package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_validNote_success() {
        String validNote = "This is a valid note";
        assertEquals(new Note(validNote).getClass(), Note.class);
    }

    @Test
    public void equals_sameNote_success() {
        String note = "This is a note";
        assertEquals(new Note(note), new Note(note));
    }

    @Test
    public void equals_differentNote_success() {
        String note1 = "This is a note";
        String note2 = "This is another note";
        assertEquals(new Note(note1).equals(new Note(note2)), false);
    }

    @Test
    public void equals_differentNotesSameValues_success() {
        String note1 = "This is a note";
        String note2 = "This is a note";
        assertEquals(new Note(note1).equals(new Note(note2)), true);
    }

    @Test
    public void value_success() {
        String note = "This is a note";
        assertEquals(new Note(note).value, note);
    }
}
