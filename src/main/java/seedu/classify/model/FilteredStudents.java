package seedu.classify.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.logging.Filter;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.classify.model.student.Student;

/**
 * Represents the students and type of information to be displayed on the GUI.
 */
public class FilteredStudents {
    private final FilteredList<Student> filteredStudents;
    // Only the student's name and ID should be displayed if true; all information should be shown otherwise
    private boolean hasConciseInfo;

    /**
     * Constructs a {@code FilteredStudent} with the given {@code filteredStudents}.
     */
    public FilteredStudents(FilteredList<Student> filteredStudents) {
        this.filteredStudents = filteredStudents;
        this.hasConciseInfo = false;
    }

    public ObservableList<Student> getFilteredStudentList() {
        return this.filteredStudents;
    }

    public void updateFilteredStudentList(Predicate<Student> predicate) {
        filteredStudents.setPredicate(predicate);
    }

    public void sortFilteredStudentList(Comparator<Student> comparator) {
        SortedList<Student> sortedList = this.filteredStudents.sorted(comparator);
        Collection<Student> studentCollection = new ArrayList<>();
        for (int i = 0; i < sortedList.size(); i++) {
            studentCollection.add(sortedList.get(i));
        }
        this.filteredStudents.setAll(studentCollection);
    }

    public double calculateExamMean(String exam) {
        Iterator<Student> studentIterator = this.filteredStudents.iterator();
        double sum = 0;
        int noOfStudents = this.filteredStudents.size();
        while (studentIterator.hasNext()) {
            sum = sum + studentIterator.next().getExamScore(exam);
        }
        return sum/noOfStudents;
    }

    /**
     * Returns true only if the student's name and ID should be displayed.
     */
    public boolean hasConciseInfo() {
        return this.hasConciseInfo;
    }

    /**
     * Toggles the filtered student list between showing all information and concise information.
     * Concise information hides the parent's details
     */
    public void toggleConciseInfo() {
        this.hasConciseInfo = !hasConciseInfo;
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
                && this.hasConciseInfo == other.hasConciseInfo;
    }
}
