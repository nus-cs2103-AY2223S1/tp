package seedu.address.testutil;

import seedu.address.model.NuScheduler;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * A utility class containing a NUScheduler with a list of
 * {@code Profile} and {@code Event} objects to be used in tests.
 */
public class TypicalNuScheduler {

    private TypicalNuScheduler() {} // prevents instantiation

    /**
     * Returns an {@code NuScheduler} with all the typical profiles.
     */
    public static NuScheduler getTypicalNuScheduler() {
        NuScheduler ab = new NuScheduler();
        for (Profile profile : TypicalProfiles.getTypicalProfiles()) {
            ab.addProfile(profile);
        }
        for (Event event : TypicalEvents.getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }

}
