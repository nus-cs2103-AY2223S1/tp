package jarvis.model;

import java.util.Collection;
import java.util.HashMap;

import jarvis.commons.exceptions.IllegalValueException;

public class LessonAttendance {
    private final HashMap<MatricNum, Boolean> attendance;

    public LessonAttendance(Collection<MatricNum> students) {
        attendance = new HashMap<>();
        for (MatricNum matricNum : students) {
            attendance.put(matricNum, false);
        }
    }

    public void markAsPresent(Student student) throws IllegalValueException {
        MatricNum matricNum = student.getMatricNum();
        if (!attendance.containsKey(matricNum)) {
            throw new IllegalValueException("Student " + student + " is not part of the lesson");
        }
        attendance.put(matricNum, true);
    }

    public void markAsAbsent(Student student) throws IllegalValueException {
        MatricNum matricNum = student.getMatricNum();
        if (!attendance.containsKey(matricNum)) {
            throw new IllegalValueException("Student " + student + " is not part of the lesson");
        }
        attendance.put(matricNum, false);
    }

    public boolean isPresent(Student student) {
        return attendance.getOrDefault(student.getMatricNum(), false);
    }
}
