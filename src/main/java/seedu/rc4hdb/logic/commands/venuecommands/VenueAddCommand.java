package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;

/**
 * Adds a venue to RC4HDB.
 */
public class VenueAddCommand extends VenueCommand implements ModelCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a venue to RC4HDB. \n"
            + "Parameters: "
            + "VENUE_NAME "
            + "Example: " + COMMAND_WORD + " "
            + "meeting room ";

    public static final String MESSAGE_SUCCESS = "The following venue was successfully added: %s";
    public static final String MESSAGE_DUPLICATE_VENUE = "The venue %s already exists.";

    /**
     * Creates a VenueAddCommand to add the specified {@code Venue}
     */
    public VenueAddCommand(VenueName venueName) {
        super(venueName);
    }

    //@@author nealetham
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            Venue venue = new Venue(venueName);
            model.addVenue(venue);
            return new CommandResult(String.format(MESSAGE_SUCCESS, venueName));
        } catch (DuplicateVenueException e) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_VENUE, venueName));
        }
    }

}
