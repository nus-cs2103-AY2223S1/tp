package jarvis.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jarvis.model.exceptions.StudentNotFoundException;

/**
 * Represents the attendance for a lesson in JARVIS.
 */
public class LessonAttendance {

    private final HashMap<Student, Boolean> attendance;

    /**
     * Creates the attendance list for a lesson.
     * @param students Students who are involved in the lesson.
     */
    public LessonAttendance(Collection<Student> students) {
        attendance = new HashMap<>();
        for (Student stu : students) {
            attendance.put(stu, false);
        }
    }

    public LessonAttendance(HashMap<Student, Boolean> lessonAttendance) {
        attendance = lessonAttendance;
    }

    /**
     * Marks a student as present for that lesson.
     * @param student Student to mark as present.
     */
    public void markAsPresent(Student student) {
        if (!attendance.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        attendance.put(student, true);
    }

    /**
     * Marks a student as absent for that lesson.
     * @param student Student to mark as absent.
     */
    public void markAsAbsent(Student student) {
        if (!attendance.containsKey(student)) {
            throw new StudentNotFoundException();
        }
        attendance.put(student, false);
    }

    public boolean isPresent(Student student) {
        return attendance.getOrDefault(student, false);
    }

    public Set<Student> getAllStudents() {
        return attendance.keySet();
    }

    public String getAllStudentsName() {
        StringBuilder studentsNameBuilder = new StringBuilder();
        studentsNameBuilder.append("Students: ");
        Set<Student> students = getAllStudents();
        assert !students.isEmpty();

        for (Student student : students) {
            studentsNameBuilder.append(student.getName() + ", ");
        }
        studentsNameBuilder.deleteCharAt(studentsNameBuilder.length() - 2); //remove the last ','
        return studentsNameBuilder.toString();
    }

    public HashMap<Student, Boolean> getMap() {
        return attendance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Attendance:\n");
        for (Map.Entry<Student, Boolean> entry : attendance.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue() ? "PRESENT" : "ABSENT");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toFullString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Student, Boolean> entry : attendance.entrySet()) {
            sb.append(entry.getKey().toFullString());
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

        if (!(other instanceof LessonAttendance)) {
            return false;
        }

        LessonAttendance otherLessonAttendance = (LessonAttendance) other;
        return otherLessonAttendance.attendance.equals(attendance);
    }

    @Override
    public int hashCode() {
        return attendance.hashCode();
    }

    public HashMap<Student, Boolean> getAttendance() {
        return attendance;
    }
}
