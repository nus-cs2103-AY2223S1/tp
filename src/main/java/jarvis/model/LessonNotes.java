package jarvis.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jarvis.model.exceptions.NoteNotFoundException;
import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Represents the notes for a lesson in JARVIS.
 */
public class LessonNotes {
    private final StringBuilder overallNotes = new StringBuilder("Lesson Notes:\n");
    private final HashMap<Student, StringBuilder> studentNotes;

    /**
     * Creates the notes for a lesson.
     * @param students Students who are involved in the lesson.
     */
    public LessonNotes(Collection<Student> students) {
        studentNotes = new HashMap<>();
        for (Student stu : students) {
            studentNotes.put(stu, new StringBuilder());
        }
    }

    /**
     * Adds on to the overall lesson notes.
     * @param notes Lines to append to the overall lesson notes.
     */
    public void addNote(String notes) {
        overallNotes.append(notes);
        overallNotes.append("\n");
    }

    /**
     * Adds a note regarding a specific student.
     * @param student Student that is involved in the lesson.
     * @param notes Note to add for the student.
     */
    public void addNote(Student student, String notes) {
        if (!studentNotes.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        studentNotes.get(student).append(notes);
        studentNotes.get(student).append("\n");
    }

    /**
     * Deletes note at given index from overall lesson notes.
     *
     * @param index Index of note according to order of notes in overall notes.
     * @return String of the deleted note.
     * @throws NoteNotFoundException
     */
    public String deleteNote(int index) throws NoteNotFoundException {
        String overallNotesString = overallNotes.toString();
        String[] notesSplit = overallNotesString.split("\n");
        if (index >= notesSplit.length) {
            throw new NoteNotFoundException();
        }
        int deleteStartIndex = 0;
        for (int i = 0; i < index; i++) {
            deleteStartIndex += notesSplit[i].length() + 1;
        }
        int deleteEndIndex = deleteStartIndex + notesSplit[index].length() + 1;
        String deletedNote = overallNotes.substring(deleteStartIndex, deleteEndIndex);
        overallNotes.delete(deleteStartIndex, deleteEndIndex);
        return deletedNote;
    }

    /**
     * Deletes note at given index of given student's notes.
     *
     * @param student Student to delete notes from.
     * @param index Index of note according to order of notes in student notes.
     * @return String of the deleted note.
     * @throws NoteNotFoundException
     */
    public String deleteNote(Student student, int index) throws NoteNotFoundException {
        if (!studentNotes.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        StringBuilder studentStringBuilder = studentNotes.get(student);
        String studentNotesString = studentStringBuilder.toString();
        String[] notesSplit = studentNotesString.split("\n");
        if (index >= notesSplit.length) {
            throw new NoteNotFoundException();
        }
        int deleteStartIndex = 0;
        for (int i = 0; i < index; i++) {
            deleteStartIndex += notesSplit[i].length() + 1;
        }
        int deleteEndIndex = deleteStartIndex + notesSplit[index].length() + 1;
        String deletedNote = studentStringBuilder.substring(deleteStartIndex, deleteEndIndex);
        StringBuilder editedStudentStringBuilder = studentStringBuilder.delete(deleteStartIndex, deleteEndIndex);
        studentNotes.put(student, editedStudentStringBuilder);
        return deletedNote;
    }

    public String getNotes() {
        return overallNotes.toString();
    }

    public String getNotes(Student student) {
        if (!studentNotes.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        return studentNotes.get(student).toString();
    }

    public String getAllNotes() {
        StringBuilder sb = new StringBuilder(overallNotes);
        sb.append("\nNotes for individual students:\n");
        for (Map.Entry<Student, StringBuilder> entry : studentNotes.entrySet()) {
            sb.append(entry.getKey());
            sb.append(":\n");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getAllNotes();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LessonNotes)) {
            return false;
        }

        LessonNotes otherLessonNotes = (LessonNotes) other;
        return otherLessonNotes.getAllNotes().equals(getAllNotes());
    }

    @Override
    public int hashCode() {
        return getAllNotes().hashCode();
    }
}
