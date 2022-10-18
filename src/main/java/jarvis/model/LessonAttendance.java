package jarvis.model;

import java.util.Collection;
import java.util.HashMap;

import jarvis.commons.exceptions.IllegalValueException;

public class LessonAttendance {
    private final HashMap<Student, Boolean> attendance;

    public LessonAttendance(Collection<Student> students) {
        attendance = new HashMap<>();
        for (Student stu : students) {
            attendance.put(stu, false);
        }
    }

    public void markAsPresent(Student student) throws IllegalValueException {
        if (!attendance.containsKey(student)) {
            throw new IllegalValueException("Student " + student + " is not part of the lesson");
        }
        attendance.put(student, true);
    }

    public void markAsAbsent(Student student) throws IllegalValueException {
        if (!attendance.containsKey(student)) {
            throw new IllegalValueException("Student " + student + " is not part of the lesson");
        }
        attendance.put(student, false);
    }

    public boolean isPresent(Student student) {
        return attendance.getOrDefault(student, false);
    }
}
