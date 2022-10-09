package seedu.workbook.testutil;

import seedu.workbook.model.WorkBook;
import seedu.workbook.model.person.Person;

/**
 * A utility class to help with building WorkBook objects.
 * Example usage: <br>
 *     {@code WorkBook ab = new WorkBookBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Person} to the {@code WorkBook} that we are building.
     */
    public WorkBookBuilder withPerson(Person person) {
        workBook.addPerson(person);
        return this;
    }

    public WorkBook build() {
        return workBook;
    }
}
