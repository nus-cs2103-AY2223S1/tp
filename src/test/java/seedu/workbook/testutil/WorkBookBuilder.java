package seedu.workbook.testutil;

import seedu.workbook.model.WorkBook;
import seedu.workbook.model.internship.Internship;

/**
 * A utility class to help with building WorkBook objects.
 * Example usage: <br>
 *     {@code WorkBook ab = new WorkBookBuilder().withInternship("John", "Doe").build();}
 */
public class WorkBookBuilder {

    private WorkBook workBook;

    public WorkBookBuilder() {
        workBook = new WorkBook();
    }

    public WorkBookBuilder(WorkBook workBook) {
        this.workBook = workBook;
    }

    /**
     * Adds a new {@code Internship} to the {@code WorkBook} that we are building.
     */
    public WorkBookBuilder withInternship(Internship internship) {
        workBook.addInternship(internship);
        return this;
    }

    public WorkBook build() {
        return workBook;
    }
}
