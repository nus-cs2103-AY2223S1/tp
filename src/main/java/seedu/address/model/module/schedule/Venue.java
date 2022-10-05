package seedu.address.model.module.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a venue in NUS
 */
public class Venue {
    private final String name;

    /**
     * Every field must be present and not null
     * @param venueName the name of the venue
     */
    public Venue(String venueName) {
        requireAllNonNull(venueName);
        this.name = venueName;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Venue)) {
            return false;
        }
        Venue otherVenue = (Venue) other;
        return otherVenue.name.equals(this.name);
    }
}
