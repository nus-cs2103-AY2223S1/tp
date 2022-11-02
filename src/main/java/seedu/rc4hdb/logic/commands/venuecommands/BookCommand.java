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
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;

/**
 * Adds a booking to the Venue.
 */
public class BookCommand extends VenueCommand implements ModelCommand {

    public static final String COMMAND_WORD = "book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to RC4HDB. \n"
            + "Parameters: INDEX "
            + PREFIX_VENUE_NAME + "VENUE_NAME "
            + PREFIX_TIME_PERIOD + "START_TIME-END_TIME "
            + PREFIX_DAY + "DAY \n"
            + "Example: " + COMMAND_WORD + " 3"
            + PREFIX_VENUE_NAME + "meeting "
            + PREFIX_TIME_PERIOD + "10-14 "
            + PREFIX_DAY + "TUE ";

    public static final String MESSAGE_SUCCESS = "New booking made: %1$s";
    public static final String MESSAGE_CLASHING_BOOKING = "This booking clashes with an existing booking.";
    public static final String MESSAGE_VENUE_NOT_FOUND = "The following venue was not found: %s";

    private final BookingDescriptor bookingDescriptor;
    private final Index residentIndex;

    /**
     * Creates a BookCommand to add the specified {@code Booking}
     */
    public BookCommand(Index residentIndex, BookingDescriptor bookingDescriptor) {
        super(bookingDescriptor.getVenueName().get());
        requireAllNonNull(residentIndex, bookingDescriptor);
        this.residentIndex = residentIndex;
        this.bookingDescriptor = bookingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            List<Resident> lastShownList = model.getFilteredResidentList();
            Resident resident = lastShownList.get(residentIndex.getZeroBased());
            Booking toMake = createNewBooking(resident);
            model.addBooking(venueName, toMake);
            model.setCurrentlyDisplayedVenue(venueName);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toMake), false, false);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_USAGE, e);
        } catch (VenueNotFoundException e) {
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
        HourPeriod hourPeriod = bookingDescriptor.getHourPeriod().get();
        Day dayOfWeek = bookingDescriptor.getDayOfWeek().get();
        VenueName venueName = bookingDescriptor.getVenueName().get();
        return new RecurrentBooking(venueName, resident, hourPeriod, dayOfWeek);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCommand // instanceof handles nulls
                && bookingDescriptor.equals(((BookCommand) other).bookingDescriptor)
                && residentIndex.equals(((BookCommand) other).residentIndex)
                && super.equals(other));
    }

}
