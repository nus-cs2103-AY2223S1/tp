package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TIME_PERIOD;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_VENUE_NAME;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * Removes a specified booking from RC4HDB.
 */
public class UnbookCommand extends VenueCommand implements ModelCommand {

    public static final String COMMAND_WORD = "unbook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a booking from RC4HDB. \n"
            + "Parameters: "
            + PREFIX_VENUE_NAME + "VENUE_NAME "
            + PREFIX_TIME_PERIOD + "START_TIME-END_TIME "
            + PREFIX_DAY + "DAY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENUE_NAME + "meeting "
            + PREFIX_TIME_PERIOD + "10-14 "
            + PREFIX_DAY + "TUE ";

    public static final String MESSAGE_SUCCESS = "The booking at %s, on %s, from %s was removed.";
    public static final String MESSAGE_BOOKING_NOT_FOUND = "The booking at %s, on %s, from %s was not found.";
    public static final String MESSAGE_VENUE_NOT_FOUND = "The following venue was not found: %s";

    private final BookingDescriptor bookingDescriptor;

    /**
     * Creates a UnbookCommand to remove the specified {@code Booking}
     */
    public UnbookCommand(BookingDescriptor bookingDescriptor) {
        super(bookingDescriptor.getVenueName().get());
        requireNonNull(bookingDescriptor);
        this.bookingDescriptor = bookingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.removeBooking(bookingDescriptor);
            model.setCurrentlyDisplayedVenue(venueName);
            return new CommandResult(String.format(MESSAGE_SUCCESS, venueName,
                    bookingDescriptor.getDayOfWeek().orElse(null),
                    bookingDescriptor.getHourPeriod().orElse(null)));
        } catch (VenueNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_VENUE_NOT_FOUND, venueName));
        } catch (BookingNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_BOOKING_NOT_FOUND, venueName,
                    bookingDescriptor.getDayOfWeek().orElse(null),
                    bookingDescriptor.getHourPeriod().orElse(null)), e);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnbookCommand // instanceof handles nulls
                && bookingDescriptor.equals(((UnbookCommand) other).bookingDescriptor)
                && super.equals(other));
    }

}
