package jarvis.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import jarvis.model.exceptions.NoteNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Represents the notes for a lesson in JARVIS.
 */
public class LessonNotes {
    private final ArrayList<String> overallNotes;
    private final HashMap<Student, ArrayList<String>> studentNotes;

    /**
     * Creates the notes for a lesson.
     * @param students Students who are involved in the lesson.
     */
    public LessonNotes(Collection<Student> students) {
        overallNotes = new ArrayList<>();
        overallNotes.add("Lesson Notes:\n");

        studentNotes = new HashMap<>();
        for (Student stu : students) {
            studentNotes.put(stu, new ArrayList<>());
        }
    }

    /**
     * Creates the notes for a lesson with the specified student notes.
     *
     * @param studentNotes The specified student notes.
     */
    public LessonNotes(HashMap<Student, ArrayList<String>> studentNotes) {
        overallNotes = new ArrayList<>();
        this.studentNotes = studentNotes;
    }

    /**
     * Adds on to the overall lesson notes.
     * @param notes Lines to append to the overall lesson notes.
     */
    public void addNote(String notes) {
        overallNotes.add(notes);
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
        studentNotes.get(student).add(notes);
    }

    /**
     * Deletes note at given index from overall notes.
     *
     * @param index Index of the note according to the list of overall notes.
     * @return String of the deleted note.
     */
    public String deleteNote(int index) {
        if (index >= overallNotes.size()) {
            throw new NoteNotFoundException();
        }
        return overallNotes.remove(index);
    }

    /**
     * Deletes note at given index of from a specific student's notes.
     *
     * @param student Student to delete notes from.
     * @param index Index of the note according to the list of student notes.
     * @return String of the deleted note.
     */
    public String deleteNote(Student student, int index) {
        if (!studentNotes.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        ArrayList<String> specifiedStudentNotes = studentNotes.get(student);
        if (index >= specifiedStudentNotes.size()) {
            throw new NoteNotFoundException();
        }
        return specifiedStudentNotes.remove(index);
    }

    public String getNotes() {
        StringBuilder formattedOverallNotes = new StringBuilder();
        for (String overallNote: overallNotes) {
            formattedOverallNotes.append(overallNote);
            formattedOverallNotes.append("\n");
        }
        return formattedOverallNotes.toString();
    }

    public String getNotes(Student student) {
        if (!studentNotes.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        StringBuilder formattedStudentNotes = new StringBuilder();
        for (String studentNote: studentNotes.get(student)) {
            formattedStudentNotes.append(studentNote);
            formattedStudentNotes.append("\n");
        }
        return formattedStudentNotes.toString();
    }

    public String getAllNotes() {
        StringBuilder formattedAllNotes = new StringBuilder(getNotes());
        formattedAllNotes.append("\nNotes for individual students:\n");
        for (Student student: studentNotes.keySet()) {
            formattedAllNotes.append(student.toString());
            formattedAllNotes.append(":\n");
            formattedAllNotes.append(getNotes(student));
            formattedAllNotes.append("\n");
        }
        return formattedAllNotes.toString();
    }

    public String toFullString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNotes for individual students:\n");

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
