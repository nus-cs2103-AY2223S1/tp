package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * Displays the booking data of the specified venue.
 */
public class VenueViewCommand extends VenueCommand implements ModelCommand {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the venue booking data of the specified venue. \n"
            + "Parameters: "
            + "VENUE_NAME "
            + "Example: " + COMMAND_WORD + " "
            + "meeting room ";

    public static final String MESSAGE_SUCCESS = "Successfully switched to viewing booking data of %s.";
    public static final String MESSAGE_VENUE_NOT_FOUND = "The venue %s was not found.";

    /**
     * Creates a VenueViewCommand to view the booking data of the specified {@code Venue}.
     */
    public VenueViewCommand(VenueName venueName) {
        super(venueName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.setCurrentlyDisplayedVenue(venueName);
            return new CommandResult(String.format(MESSAGE_SUCCESS, venueName));
        } catch (VenueNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_VENUE_NOT_FOUND, venueName));
        }
    }

}
