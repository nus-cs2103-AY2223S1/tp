package jarvis.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Represents the participation for a Studio in JARVIS.
 * A student's participation score must be an integer between
 * 0 and 500 inclusive.
 */
public class StudioParticipation {
    private final HashMap<Student, Integer> participation;

    /**
     * Creates a StudioParticipation and initializes the participation
     * scores of all students to 0.
     * @param students Students involved in the lesson.
     */
    public StudioParticipation(Collection<Student> students) {
        participation = new HashMap<>();
        for (Student stu : students) {
            participation.put(stu, 0);
        }
    }

    public void setParticipationForStudent(Student student, int i) {
        assert i >= 0 && i <= 500 : "Participation value must be between 0 and 500 inclusive";
        if (!participation.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        participation.put(student, i);
    }

    public int getParticipationForStudent(Student student) {
        if (!participation.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        return participation.get(student);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Studio Participation:\n");
        for (Map.Entry<Student, Integer> entry : participation.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudioParticipation)) {
            return false;
        }

        StudioParticipation otherStudioParticipation = (StudioParticipation) other;
        return otherStudioParticipation.participation.equals(participation);
    }

    @Override
    public int hashCode() {
        return participation.hashCode();
    }

    public HashMap<Student, Integer> getParticipation() {
        return participation;
    }

    public String toFullString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Student, Integer> entry : participation.entrySet()) {
            sb.append(entry.getKey().toFullString());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
