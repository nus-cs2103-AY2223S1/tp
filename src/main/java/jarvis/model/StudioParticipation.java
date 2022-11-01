package jarvis.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Represents the participation for a Studio in JARVIS.
 * A student's participation score must be an integer between
 * 0 and 500 inclusive.
 */
public class StudioParticipation {
    private final TreeMap<Student, Integer> participation;

    /**
     * Creates a StudioParticipation and initializes the participation
     * scores of all students to 0.
     * @param students Students involved in the lesson.
     */
    public StudioParticipation(Collection<Student> students) {
        participation = new TreeMap<>();

        for (Student stu : students) {
            participation.put(stu, 0);
        }
    }

    /**
     * Creates a StudioParticipation with the given participation data.
     *
     * @param studentList Students involved in the lesson.
     * @param indexMap The participation data for each student mapped by student index.
     */
    public StudioParticipation(List<Student> studentList, Map<Integer, Integer> indexMap) {
        TreeMap<Student, Integer> participation = new TreeMap<>();
        for (int i : indexMap.keySet()) {
            participation.put(studentList.get(i), indexMap.get(i));
        }
        this.participation = participation;
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

    public void setStudent(Student targetStudent, Student editedStudent) {
        int studentParticipation = participation.get(targetStudent);
        participation.remove(targetStudent);
        participation.put(editedStudent, studentParticipation);
    }

    public Set<Student> getAllStudents() {
        return participation.keySet();
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
}
