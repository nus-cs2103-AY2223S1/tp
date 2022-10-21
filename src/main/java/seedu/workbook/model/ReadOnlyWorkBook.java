package seedu.workbook.model;

import javafx.collections.ObservableList;
import seedu.workbook.model.internship.Internship;

/**
 * Unmodifiable view of a WorkBook
 */
public interface ReadOnlyWorkBook {

    /**
     * Returns an unmodifiable view of the internships list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Internship> getInternshipList();

}
