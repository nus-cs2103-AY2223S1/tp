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
     * Returns an unmodifiable view of the sorted students list by specialisation
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getSortedBySpecialisationStudentList();

    /**
     * Returns an unmodifiable view of the sorted students list by project status followed by alphabetical order
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getSortedByProjectStatusStudentList();

}
