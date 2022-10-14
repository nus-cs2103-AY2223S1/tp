package seedu.address.testutil;

import seedu.address.model.JeeqTracker;
import seedu.address.model.client.Company;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code JeeqTracker ab = new JeeqTrackerBuilder().withCompany("John", "Doe").build();}
 */
public class JeeqTrackerBuilder {

    private JeeqTracker jeeqTracker;

    public JeeqTrackerBuilder() {
        jeeqTracker = new JeeqTracker();
    }

    public JeeqTrackerBuilder(JeeqTracker jeeqTracker) {
        this.jeeqTracker = jeeqTracker;
    }

    /**
     * Adds a new {@code Company} to the {@code JeeqTracker} that we are building.
     */
    public JeeqTrackerBuilder withCompany(Company company) {
        jeeqTracker.addCompany(company);
        return this;
    }

    public JeeqTracker build() {
        return jeeqTracker;
    }
}
