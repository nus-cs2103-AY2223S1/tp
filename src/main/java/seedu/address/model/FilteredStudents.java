package seedu.address.model;

import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;

/**
 * Represents the students and type of information to be displayed on the GUI
 */
public class FilteredStudents {
    private final FilteredList<Person> filteredStudents;
    // Only the student's name and ID should be displayed if true; all information should be shown otherwise
    private boolean conciseInfo;

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

    public boolean hasConciseInfo() {
        return this.conciseInfo;
    }

    public void setConciseInfo(boolean b) {
        this.conciseInfo = b;
    }
}
