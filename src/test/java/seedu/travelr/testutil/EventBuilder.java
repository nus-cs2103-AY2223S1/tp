package seedu.travelr.testutil;

import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;

/**
 * Builds sample event
 */
public class EventBuilder {
    public static final String DEFAULT_TITLE = "See the Merlion";
    public static final String DEFAULT_DESCRIPTION = "See the fish.";

    private Title title;
    private Description description;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Builds the trip from the inputs
     * @return
     */
    public Event build() {
        return new Event(title, description);
    }
}
