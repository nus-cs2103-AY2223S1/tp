package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.Title;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_TITLE = "Review pull requests";
    public static final String DEFAULT_START = "01/09/2022 12:00";
    public static final String DEFAULT_END = "08/09/2022 12:00";

    private Title title;
    private DateTime startDateTime;
    private DateTime endDateTime;
    private Set<Tag> tags;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        title = new Title(DEFAULT_TITLE);
        startDateTime = new DateTime(DEFAULT_START);
        endDateTime = new DateTime(DEFAULT_END);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        title = eventToCopy.getTitle();
        startDateTime = eventToCopy.getStartDateTime();
        endDateTime = eventToCopy.getEndDateTime();
        tags = new HashSet<>(eventToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Event} that we are building.
     */
    public EventBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code startDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = new DateTime(startDateTime);
        return this;
    }

    /**
     * Sets the {@code endDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = new DateTime(endDateTime);
        return this;
    }

    public Event build() {
        return new Event(title, startDateTime, endDateTime, tags);
    }

}
