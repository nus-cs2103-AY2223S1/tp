package seedu.address.testutil;

import seedu.address.model.module.schedule.Venue;

/**
 * A utility class to help with building Venue objects.
 */
public class VenueBuilder {
    public static final String NAME = "I3-AUD";

    private String name;

    /**
     * Creates a {@code VenueBuilder} with the default details.
     */
    public VenueBuilder() {
        String name = NAME;
    }

    /**
     * Initializes the VenueBuilder with the data of {@code venueToCopy}.
     */
    public VenueBuilder(Venue venueToCopy) {
        this.name = venueToCopy.toString();
    }

    /**
     * Sets the {@code venueName} of the {@code Venue} that we are building.
     */
    public VenueBuilder buildWithName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Builds a venue for testing.
     *
     * @return a Venue that we are building
     */
    public Venue build() {
        return new Venue(name);
    }
}
