package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.modelcommands.ModelCommand;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Adds a booking to the Venue.
 */
public class BookCommand extends ModelCommand {

    public static final String COMMAND_WORD = "book";

    //to change
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to RC4HDB. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ROOM + "ROOM "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_HOUSE + "HOUSE "
            + PREFIX_MATRIC_NUMBER + "MATRIC_NUMBER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ROOM + "03-08 "
            + PREFIX_GENDER + "M "
            + PREFIX_HOUSE + "D "
            + PREFIX_MATRIC_NUMBER + "A0000000A "
            + PREFIX_TAG + "BlockHead ";

    //to change
    public static final String MESSAGE_SUCCESS = "New booking made: %1$s";
    public static final String MESSAGE_CLASHING_BOOKING = "This booking clashes with an existing booking.";

    private final BookingDescriptor bookingDescriptor;
    private Index index;

    /**
     * Creates an BookCommand to add the specified {@code Booking}
     */
    public BookCommand(Index index, BookingDescriptor bookingDescriptor) {
        requireAllNonNull(index, bookingDescriptor);
        this.bookingDescriptor = new BookingDescriptor(bookingDescriptor);
        this.index = index;
    }

    //todo handle venue booking
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        }

        Resident resident = lastShownList.get(index.getZeroBased());
        bookingDescriptor.setResident(resident);

        Booking toMake = createNewBooking(bookingDescriptor);
        if (toMake.getVenue().hasClashes(toMake)) {
            throw new CommandException(MESSAGE_CLASHING_BOOKING);
        }

        toMake.getVenue().addBooking(toMake);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toMake));
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingDescriptor}.
     */
    private static Booking createNewBooking(BookingDescriptor bookingDescriptor) {
        assert bookingDescriptor != null;

        Resident resident = bookingDescriptor.getResident().get();
        LocalTime startTime = bookingDescriptor.getstartTime().get();
        LocalTime endTime = bookingDescriptor.getEndTime().get();
        DayOfWeek dayOfWeek = bookingDescriptor.getDayOfWeek().get();
        LocalDate date = bookingDescriptor.getDate().get();
        Venue venue = bookingDescriptor.getVenue().get();
        if (date == null) {
            return new RecurrentBooking(resident, dayOfWeek, startTime, endTime, venue);
        }

        return new AdHocBooking(resident, date, startTime, endTime, venue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCommand // instanceof handles nulls
                && bookingDescriptor.equals(((BookCommand) other).bookingDescriptor));
    }
}
