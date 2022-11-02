package seedu.address.testutil;

import seedu.address.model.NuScheduler;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * A utility class to help with building NuScheduler objects.
 * Example usage: <br>
 *     {@code NuScheduler ns = new NuSchedulerBuilder().withProfile("John", "Doe").build();}
 */
public class NuSchedulerBuilder {

    private NuScheduler nuScheduler;

    public NuSchedulerBuilder() {
        nuScheduler = new NuScheduler();
    }

    public NuSchedulerBuilder(NuScheduler nuScheduler) {
        this.nuScheduler = nuScheduler;
    }

    /**
     * Adds a new {@code Profile} to the {@code NuScheduler} that we are building.
     */
    public NuSchedulerBuilder withProfile(Profile profile) {
        nuScheduler.addProfile(profile);
        return this;
    }

    /**
     * Adds a new {@code Event} to the {@code NuScheduler} that we are building.
     */
    public NuSchedulerBuilder withEvent(Event event) {
        nuScheduler.addEvent(event);
        return this;
    }

    public NuScheduler build() {
        return nuScheduler;
    }
}
