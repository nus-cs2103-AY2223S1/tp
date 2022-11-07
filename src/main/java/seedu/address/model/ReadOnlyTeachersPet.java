package seedu.address.model;

import java.util.Comparator;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTeachersPet {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the schedule list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getScheduleList();

    void sortStudents(Comparator<Student> comparator);
}
