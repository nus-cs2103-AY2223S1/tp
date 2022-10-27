package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<TutorialGroup> getTutorialGroupList();

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the grades map.
     */
    ObservableMap<GradeKey, Grade> getGradeMap();

}
