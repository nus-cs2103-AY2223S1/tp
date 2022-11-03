package jarvis.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import jarvis.model.exceptions.InvalidNoteException;
import jarvis.model.exceptions.NoStudentsInLessonException;
import jarvis.model.exceptions.StudentNotFoundException;
import jarvis.model.util.SampleStudentUtil;

class LessonNotesTest {

    private final TreeSet<Student> students = new TreeSet<>(List.of(SampleStudentUtil.getSampleStudents()));
    private final LessonNotes lessonNotes = new LessonNotes(students);

    @Test
    public void constructor_invalidStudents_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new LessonNotes(null));

        assertThrows(NoStudentsInLessonException.class, () -> new LessonNotes(new TreeSet<>()));
    }

    @Test
    public void addGeneralNote_invalidNote_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> lessonNotes.addNote(null));

        String empty = "";
        assertThrows(InvalidNoteException.class, () -> lessonNotes.addNote(empty));

        String whitespace = "    ";
        assertThrows(InvalidNoteException.class, () -> lessonNotes.addNote(whitespace));
    }

    @Test
    public void addStudentNote_invalidNote_exceptionThrown() {
        Student student = students.first();
        assertThrows(NullPointerException.class, () -> lessonNotes.addNote(student,null));

        String empty = "";
        assertThrows(InvalidNoteException.class, () -> lessonNotes.addNote(student, empty));

        String whitespace = "    ";
        assertThrows(InvalidNoteException.class, () -> lessonNotes.addNote(student, whitespace));
    }

    @Test
    public void addStudentNote_invalidStudent_exceptionThrown() {
        String note = "Valid note";
        assertThrows(NullPointerException.class, () -> lessonNotes.addNote(null, note));

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        LessonNotes differentStudents = new LessonNotes(students);
        assertThrows(StudentNotFoundException.class, () -> differentStudents.addNote(studentToRemove, note));
    }

    @Test
    public void addGeneralNote_validNote() {
        String note1 = "Line 1";
        String note2 = "2nd line: ^*!"; // non-alphanumeric characters
        String note3 = "  text   "; // leading and trailing whitespace
        StringBuilder sb = new StringBuilder(LessonNotes.GENERAL_NOTES_HEADER);

        lessonNotes.addNote(note1);
        sb.append("1. ").append(note1.strip()).append("\n");
        assertEquals(sb.toString(), lessonNotes.getGeneralNotesString());
        lessonNotes.addNote(note2);
        sb.append("2. ").append(note2.strip()).append("\n");
        assertEquals(sb.toString(), lessonNotes.getGeneralNotesString());
        lessonNotes.addNote(note3);
        sb.append("3. ").append(note3.strip()).append("\n");
        assertEquals(sb.toString(), lessonNotes.getGeneralNotesString());
    }

    @Test
    public void addStudentNote_validNote() {
        Student student = students.first();
        String note1 = "Line 1";
        String note2 = "2nd line: ^*!"; // non-alphanumeric characters
        String note3 = "  text   "; // leading and trailing whitespace
        StringBuilder sb = new StringBuilder();

        lessonNotes.addNote(student, note1);
        sb.append("1. ").append(note1.strip()).append("\n");
        assertEquals(sb.toString(), lessonNotes.getStudentNotesString(student));
        lessonNotes.addNote(student, note2);
        sb.append("2. ").append(note2.strip()).append("\n");
        assertEquals(sb.toString(), lessonNotes.getStudentNotesString(student));
        lessonNotes.addNote(student, note3);
        sb.append("3. ").append(note3.strip()).append("\n");
        assertEquals(sb.toString(), lessonNotes.getStudentNotesString(student));
    }

    @Test
    public void testEquals() {
        LessonNotes sameValues = new LessonNotes(students);

        Student studentToRemove = students.first();
        students.remove(studentToRemove);
        LessonNotes differentStudents = new LessonNotes(students);

        LessonNotes differentGeneralNotes = new LessonNotes(students);
        differentGeneralNotes.addNote("Line 1");

        LessonNotes differentStudentNote = new LessonNotes(students);
        differentStudentNote.addNote(students.first(), "Line 1");

        // same values -> returns true
        assertTrue(lessonNotes.equals(sameValues));

        // same object -> returns true
        assertTrue(lessonNotes.equals(lessonNotes));

        // null -> returns false
        assertFalse(lessonNotes.equals(null));

        // different type -> returns false
        assertFalse(lessonNotes.equals(5));

        // different students -> returns false
        assertFalse(lessonNotes.equals(differentStudents));

        // same students but different general notes -> returns false
        assertFalse(lessonNotes.equals(differentGeneralNotes));

        // same students but different general notes -> returns false
        assertFalse(lessonNotes.equals(differentStudentNote));
    }
}