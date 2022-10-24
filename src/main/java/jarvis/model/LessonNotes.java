package jarvis.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

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
     * Creates the notes for a lesson with the specified student notes.
     *
     * @param studentNotes The specified student notes.
     */
    public LessonNotes(HashMap<Student, StringBuilder> studentNotes) {
        this.studentNotes = studentNotes;
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

    public String getOverallNotes() {
        if (overallNotes.length() < 14) {
            return "";
        }
        return overallNotes.toString().substring(14);   // header not included
    }

    public HashMap<Student, StringBuilder> getStudentNotes() {
        return studentNotes;
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

    public String toFullString() {
        StringBuilder sb = new StringBuilder(overallNotes);
        sb.append("\nNotes for individual students:\n");
        for (Map.Entry<Student, StringBuilder> entry : studentNotes.entrySet()) {
            sb.append(entry.getKey().toFullString());
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
