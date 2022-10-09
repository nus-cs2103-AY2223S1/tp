package seedu.address.model.person.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 */
public class UniqueStudentList extends UniquePersonList {
    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(students);
    }

    public ObservableList<Student> asUnmodifiableObservableStudentList() {
        return internalUnmodifiableList;
    }

    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSamePerson(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
