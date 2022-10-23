package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TIME_PERIOD;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_VENUE_NAME;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.modelcommands.ModelCommand;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.model.venues.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * Adds a booking to the Venue.
 */
public class BookCommand extends VenueCommand implements ModelCommand {

    public static final String COMMAND_WORD = "book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to RC4HDB. "
            + "Parameters: "
            + PREFIX_VENUE_NAME + "VENUE_NAME "
            + PREFIX_TIME_PERIOD + "START_TIME-END_TIME "
            + PREFIX_DAY + "DAY "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENUE_NAME + "meeting "
            + PREFIX_TIME_PERIOD + "10-14 "
            + PREFIX_DAY + "TUE ";

    public static final String MESSAGE_SUCCESS = "New booking made: %1$s";
    public static final String MESSAGE_CLASHING_BOOKING = "This booking clashes with an existing booking.";
    public static final String MESSAGE_VENUE_NOT_FOUND = "The following venue was not found: %s";

    private final BookingDescriptor bookingDescriptor;
    private Index residentIndex;

    /**
     * Creates an BookCommand to add the specified {@code Booking}
     */
    public BookCommand(Index residentIndex, VenueName venueName, BookingDescriptor bookingDescriptor) {
        super(venueName);
        requireAllNonNull(residentIndex, bookingDescriptor);
        this.residentIndex = residentIndex;
        this.bookingDescriptor = new BookingDescriptor(bookingDescriptor);
    }

    //todo handle venue booking
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            List<Resident> lastShownList = model.getFilteredResidentList();
            Resident resident = lastShownList.get(residentIndex.getZeroBased());
            Booking toMake = createNewBooking(resident);
            VenueName venueName = bookingDescriptor.getVenueName().get();
            model.addBookingToVenueWithSameName(venueName, toMake);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toMake));
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_USAGE, e);
        } catch (VenueNotFoundException e) {
            // NoSuchElementException will not be thrown as it is caught above.
            VenueName venueName = bookingDescriptor.getVenueName().get();
            throw new CommandException(String.format(MESSAGE_VENUE_NOT_FOUND, venueName));
        } catch (BookingClashesException e) {
            throw new CommandException(MESSAGE_CLASHING_BOOKING, e);
        }
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingDescriptor}.
     */
    private Booking createNewBooking(Resident resident) throws NoSuchElementException {
        assert bookingDescriptor != null && resident != null;
        VenueName venueName = bookingDescriptor.getVenueName().get();
        HourPeriod hourPeriod = bookingDescriptor.getHourPeriod().get();
        Day dayOfWeek = bookingDescriptor.getDayOfWeek().get();
        return new RecurrentBooking(venueName, resident, hourPeriod, dayOfWeek);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCommand // instanceof handles nulls
                && bookingDescriptor.equals(((BookCommand) other).bookingDescriptor));
    }

}
