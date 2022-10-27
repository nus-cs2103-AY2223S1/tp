package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * Deletes a venue from RC4HDB.
 */
public class VenueDeleteCommand extends VenueCommand implements ModelCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a venue from RC4HDB. \n"
            + "Parameters: "
            + "VENUE_NAME "
            + "Example: " + COMMAND_WORD + " "
            + "meeting room ";

    public static final String MESSAGE_SUCCESS = "The following venue was successfully deleted: %s";
    public static final String MESSAGE_VENUE_NOT_FOUND = "The venue %s was not found.";

    /**
     * Creates a VenueDeleteCommand to add the specified {@code Venue}
     */
    public VenueDeleteCommand(VenueName venueName) {
        super(venueName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.deleteVenue(venueName);
            return new CommandResult(String.format(MESSAGE_SUCCESS, venueName));
        } catch (VenueNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_VENUE_NOT_FOUND, venueName));
        }
    }

}
