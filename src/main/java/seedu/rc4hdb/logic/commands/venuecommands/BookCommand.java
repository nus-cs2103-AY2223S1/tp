package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.modelcommands.ModelCommand;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.RecurrentBooking;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.Hour;

/**
 * Adds a booking to the Venue.
 */
public class BookCommand implements ModelCommand {

    public static final String COMMAND_WORD = "book";

    //to change
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to RC4HDB. "
            + "Parameters: "
            + PREFIX_VENUE + "VENUE_NAME "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME "
            + PREFIX_DAY + "DAY "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_VENUE + "meeting "
            + PREFIX_START_TIME + "10 "
            + PREFIX_END_TIME + "15 "
            + PREFIX_DAY + "TUE ";

    //to change
    public static final String MESSAGE_SUCCESS = "New booking made: %1$s";
    public static final String MESSAGE_CLASHING_BOOKING = "This booking clashes with an existing booking.";

    private final BookingDescriptor bookingDescriptor;
    private Index residentIndex;

    /**
     * Creates an BookCommand to add the specified {@code Booking}
     */
    public BookCommand(Index residentIndex, BookingDescriptor bookingDescriptor) {
        requireAllNonNull(residentIndex, bookingDescriptor);
        this.bookingDescriptor = new BookingDescriptor(bookingDescriptor);
        this.residentIndex = residentIndex;
    }

    //todo handle venue booking
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (residentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        }

        Resident resident = lastShownList.get(residentIndex.getZeroBased());
        bookingDescriptor.setResident(resident);

        try {
            Booking toMake = createNewBooking();
            Venue venue = bookingDescriptor.getVenue().get();
            if (venue.hasClashes(toMake)) {
                throw new CommandException(MESSAGE_CLASHING_BOOKING);
            }
            venue.addBooking(toMake);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toMake));
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_USAGE, e);
        }
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingDescriptor}.
     */
    private Booking createNewBooking() throws NoSuchElementException {
        assert bookingDescriptor != null;

        Resident resident = bookingDescriptor.getResident().get();
        Hour startHour = bookingDescriptor.getStartHour().get();
        Hour endHour = bookingDescriptor.getEndHour().get();
        Day dayOfWeek = bookingDescriptor.getDayOfWeek().get();
        Venue venue = bookingDescriptor.getVenue().get();
        return new RecurrentBooking(resident, startHour, endHour, dayOfWeek, venue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCommand // instanceof handles nulls
                && bookingDescriptor.equals(((BookCommand) other).bookingDescriptor));
    }

}
