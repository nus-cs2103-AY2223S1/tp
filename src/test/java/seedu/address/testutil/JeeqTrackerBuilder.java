package seedu.address.testutil;

import seedu.address.model.JeeqTracker;
import seedu.address.model.client.Client;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code JeeqTracker ab = new JeeqTrackerBuilder().withClient("John", "Doe").build();}
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
     * Adds a new {@code Client} to the {@code JeeqTracker} that we are building.
     */
    public JeeqTrackerBuilder withClient(Client client) {
        jeeqTracker.addClient(client);
        return this;
    }

    public JeeqTracker build() {
        return jeeqTracker;
    }
}
