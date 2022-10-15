package seedu.application.testutil;

import seedu.application.model.ApplicationBook;
import seedu.application.model.application.Application;

/**
 * A utility class to help with building Applicationbook objects.
 * Example usage: <br>
 *     {@code ApplicationBook ab = new ApplicationBookBuilder().withApplication("Jane Street", "Meta").build();}
 */
public class ApplicationBookBuilder {

    private ApplicationBook applicationBook;

    public ApplicationBookBuilder() {
        applicationBook = new ApplicationBook();
    }

    public ApplicationBookBuilder(ApplicationBook applicationBook) {
        this.applicationBook = applicationBook;
    }

    /**
     * Adds a new {@code Application} to the {@code ApplicationBook} that we are building.
     */
    public ApplicationBookBuilder withApplication(Application application) {
        applicationBook.addApplication(application);
        return this;
    }

    public ApplicationBook build() {
        return applicationBook;
    }
}
