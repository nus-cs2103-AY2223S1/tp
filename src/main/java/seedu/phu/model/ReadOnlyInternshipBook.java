package seedu.phu.model;

import java.util.Comparator;

import javafx.collections.ObservableList;
import seedu.phu.model.internship.Internship;

/**
 * Unmodifiable view of an internship book
 */
public interface ReadOnlyInternshipBook {

    /**
     * Returns an unmodifiable view of the internships list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Internship> getInternshipList();

    void sortInternshipList(Comparator<Internship> comparator);

    void reverseList();

}
