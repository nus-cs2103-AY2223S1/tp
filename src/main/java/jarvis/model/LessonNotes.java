package jarvis.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Represents the notes for a lesson in JARVIS.
 */
public class LessonNotes {
    private final StringBuilder generalNotes = new StringBuilder("General Notes:\n");
    private final TreeMap<Student, StringBuilder> studentNotes;

    /**
     * Creates the notes for a lesson.
     * @param students Students who are involved in the lesson.
     */
    public LessonNotes(Collection<Student> students) {
        studentNotes = new TreeMap<>(Comparator.comparing(s -> s.getName().toString()));
        for (Student stu : students) {
            studentNotes.put(stu, new StringBuilder());
        }
    }

    /**
     * Adds on to the overall lesson notes.
     * @param notes Lines to append to the overall lesson notes.
     */
    public void addNote(String notes) {
        generalNotes.append(notes);
        generalNotes.append("\n");
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

    public String getGeneralNotes() {
        return generalNotes.toString();
    }

    public String getStudentNotes(Student student) {
        if (!studentNotes.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        return studentNotes.get(student).toString();
    }

    public String getAllNotes() {
        StringBuilder sb = new StringBuilder(generalNotes);
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
