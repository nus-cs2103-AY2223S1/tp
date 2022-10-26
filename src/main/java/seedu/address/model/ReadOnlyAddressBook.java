package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the tutors list.
     * This list will not contain any duplicate tutors.
     */
    ObservableList<Tutor> getTutorList();

    /**
     * Returns an unmodifiable view of the tuition classes list.
     * This list will not contain any duplicate tuition classes.
     */
    ObservableList<TuitionClass> getTuitionClassList();


}
