package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;

import seedu.rc4hdb.logic.commands.Command;
import seedu.rc4hdb.model.venues.VenueName;

/**
 * Encapsulates a command that targets a venue.
 */
public abstract class VenueCommand implements Command {

    public static final String COMMAND_WORD = "venue";

    protected VenueName venueName;

    /**
     * Base constructor for all venue commands.
     * @param venueName the name of the venue to be targeted.
     */
    public VenueCommand(VenueName venueName) {
        requireNonNull(venueName);
        this.venueName = venueName;
    }

}
