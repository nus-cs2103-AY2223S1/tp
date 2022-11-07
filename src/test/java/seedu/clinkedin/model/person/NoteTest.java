package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void toStringTest() {
        String noteStr = "This is a note";
        Note note = new Note(noteStr);
        assertEquals(note.toString(), noteStr);
    }

    @Test
    public void equalityTests() {
        String noteStr1 = "This is a note";
        String noteStr2 = "This is a note";
        Note note1 = new Note(noteStr1);
        Note note2 = new Note(noteStr2);

        assertTrue(note1.equals(note1));
        assertTrue(note1.equals(note2));
        assertFalse(note1.equals(null));
        assertFalse(note1.equals(6));
    }

    @Test
    public void hashcodeTests() {
        String noteStr1 = "This is a note";
        String noteStr2 = "This is a note";
        int hashcode1 = noteStr1.hashCode();
        int hashcode2 = noteStr2.hashCode();
        assertEquals(hashcode1, hashcode2);
        assertEquals(hashcode1, hashcode1);
    }

}
