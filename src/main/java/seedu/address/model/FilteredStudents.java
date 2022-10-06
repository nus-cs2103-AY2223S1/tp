package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;

/**
 * Represents the students and type of information to be displayed on the GUI.
 */
public class FilteredStudents {
    private final FilteredList<Person> filteredStudents;
    // Only the student's name and ID should be displayed if true; all information should be shown otherwise
    private boolean conciseInfo;

    /**
     * Constructs a {@code FilteredStudent} with the given {@code filteredStudents}.
     */
    public FilteredStudents(FilteredList<Person> filteredStudents) {
        this.filteredStudents = filteredStudents;
        this.conciseInfo = true;
    }

    public ObservableList<Person> getFilteredStudentList() {
        return this.filteredStudents;
    }

    public void updateFilteredStudentList(Predicate<Person> predicate) {
        filteredStudents.setPredicate(predicate);
    }

    /**
     * Returns true only if the student's name and ID should be displayed.
     */
    public boolean hasConciseInfo() {
        return this.conciseInfo;
    }

    /**
     * Sets the filtered student list to only concise information - the student's name and ID, or all information.
     */
    public void setConciseInfo(boolean b) {
        this.conciseInfo = b;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof FilteredStudents)) {
            return false;
        }

        // state check
        FilteredStudents other = (FilteredStudents) obj;
        return this.filteredStudents.equals(other.filteredStudents)
                && this.conciseInfo == other.conciseInfo;
    }
}
