package jeryl.fyp.model;

import javafx.collections.ObservableList;
import jeryl.fyp.model.student.Student;

/**
 * Unmodifiable view of a FYP manager
 */
public interface ReadOnlyFypManager {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the list of students who have yet to
     * complete their projects.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getUncompletedStudentList();

    /**
     * Returns an unmodifiable view of the list of students who have
     * completed their projects.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getCompletedStudentList();

}
