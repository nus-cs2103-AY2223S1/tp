package seedu.realtime.testutil;

import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.person.Name;
import seedu.realtime.model.tag.Tag;
import seedu.realtime.model.util.SampleDataUtil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_NAME = "John Doe";
    public static final String DEFAULT_ID = "1";
    public static final String DEFAULT_DATETIME = "2022-10-12 12:00";

    private Name client;
    private ListingId listing;
    private LocalDateTime dateTime;
    private Set<Tag> tags;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        client = new Name(DEFAULT_NAME);
        listing = new ListingId(DEFAULT_ID);
        dateTime = LocalDateTime.parse(DEFAULT_DATETIME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the MeetingBuilder with data of {@code toCopy}.
     */
    public MeetingBuilder(Meeting toCopy) {
        client = toCopy.getClient();
        listing = toCopy.getListing();
        dateTime = toCopy.getdateTime();
        tags = new HashSet<>(toCopy.getTags());
    }

    /**
     * Sets the {@code client} of the {@code Meeting} that we are building/
     */
    public MeetingBuilder withClient(String client) {
        this.client = new Name(client);
        return this;
    }

    /**
     * Sets the {@code listing} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withListing(String id) {
        this.listing = new ListingId(id);
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime);
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Builds a Meeting.
     */
    public Meeting build() {
        return new Meeting(client, listing, dateTime);
    }
}
