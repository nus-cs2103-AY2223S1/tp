package seedu.rc4hdb.logic.commands.venuecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.showResidentAtIndex;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalBookings.DESC_MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.DESC_MR_ALICE_MONDAY_5_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.DESC_MR_BOB_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.DESC_MR_CARL_MONDAY_5_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.HP_5_TO_6PM_STRING;
import static seedu.rc4hdb.testutil.TypicalBookings.MONDAY_STRING;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_FIRST_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalIndexes.INDEX_SECOND_RESIDENT;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL_STRING;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenueBook;
import static seedu.rc4hdb.testutil.TypicalVenues.getTypicalVenues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.residentcommands.ClearCommand;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ModelStub;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.booking.Booking;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;
import seedu.rc4hdb.testutil.BookingBuilder;
import seedu.rc4hdb.testutil.BookingDescriptorBuilder;
import seedu.rc4hdb.testutil.TypicalResidents;

/**
 * Contains unit tests for BookCommand.
 */
public class BookCommandTest {

    private Model model = new ModelManager(getTypicalResidentBook(), getTypicalVenueBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Booking validBooking = new BookingBuilder().build();
        BookingDescriptor descriptor = new BookingDescriptorBuilder(validBooking).build();
        assertThrows(NullPointerException.class, () -> new BookCommand(null, descriptor));
    }

    @Test
    public void constructor_nullBookingDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BookCommand(INDEX_FIRST_RESIDENT, null));
    }

    @Test
    public void execute_validBooking_success() throws Exception {
        Booking validBooking = new BookingBuilder().build();
        BookingDescriptor descriptor = new BookingDescriptorBuilder(validBooking).build();
        BookCommand bookCommand = new BookCommand(INDEX_FIRST_RESIDENT, descriptor);

        String expectedMessage = String.format(BookCommand.MESSAGE_SUCCESS, validBooking);

        ModelStubAcceptingBookingAdded modelStub = new ModelStubAcceptingBookingAdded();

        CommandResult commandResult = bookCommand.execute(modelStub);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBooking), modelStub.bookingsAdded);
    }

    @Test
    public void execute_noVenueName_throwsNoSuchElementException() {
        BookingDescriptor invalidBookingDescriptor =
                new BookingDescriptorBuilder()
                        .withDay(MONDAY_STRING)
                        .withHourPeriod(HP_5_TO_6PM_STRING)
                        .build();

        String expectedMessage = "No value present";

        assertThrows(NoSuchElementException.class, expectedMessage, ()
                -> new BookCommand(INDEX_FIRST_RESIDENT, invalidBookingDescriptor));
    }

    @Test
    public void execute_noDay_throwsCommandException() {
        BookingDescriptor invalidBookingDescriptor =
                new BookingDescriptorBuilder()
                        .withVenueName(HALL_STRING)
                        .withHourPeriod(HP_5_TO_6PM_STRING)
                        .build();
        BookCommand bookCommand = new BookCommand(INDEX_FIRST_RESIDENT, invalidBookingDescriptor);

        String expectedMessage = BookCommand.MESSAGE_USAGE;

        assertThrows(CommandException.class, expectedMessage, ()
                -> bookCommand.execute(model));
    }

    @Test
    public void execute_noTimePeriod_throwsCommandException() {
        BookingDescriptor invalidBookingDescriptor =
                new BookingDescriptorBuilder()
                        .withVenueName(HALL_STRING)
                        .withDay(MONDAY_STRING)
                        .build();
        BookCommand bookCommand = new BookCommand(INDEX_FIRST_RESIDENT, invalidBookingDescriptor);

        String expectedMessage = BookCommand.MESSAGE_USAGE;

        assertThrows(CommandException.class, expectedMessage, ()
                -> bookCommand.execute(model));
    }

    @Test
    public void execute_venueNotFound_throwsCommandException() {
        BookingDescriptor validBookingDescriptor = DESC_MR_CARL_MONDAY_5_TO_7PM;
        BookCommand bookCommand = new BookCommand(INDEX_FIRST_RESIDENT, validBookingDescriptor);

        String expectedMessage = String.format(BookCommand.MESSAGE_VENUE_NOT_FOUND,
                validBookingDescriptor.getVenueName().orElse(null));

        ModelStubWithExistingBooking modelStub = new ModelStubWithExistingBooking();

        assertThrows(CommandException.class, expectedMessage, ()
                -> bookCommand.execute(modelStub));

    }

    @Test
    public void execute_bookingClash_throwsCommandException() {
        BookingDescriptor clashingBookingDescriptor = DESC_MR_ALICE_MONDAY_5_TO_7PM;
        BookCommand bookCommand = new BookCommand(INDEX_FIRST_RESIDENT, clashingBookingDescriptor);

        String expectedMessage = BookCommand.MESSAGE_CLASHING_BOOKING;

        ModelStubWithExistingBooking modelStub = new ModelStubWithExistingBooking();

        assertThrows(CommandException.class, expectedMessage, ()
                -> bookCommand.execute(modelStub));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidResidentIndex_throwsCommandException() {
        showResidentAtIndex(model, INDEX_FIRST_RESIDENT);
        Index outOfBoundIndex = INDEX_SECOND_RESIDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResidentBook().getResidentList().size());

        String expectedMessage = Messages.MESSAGE_NO_RESIDENT_WITH_INDEX;

        BookCommand bookCommand = new BookCommand(outOfBoundIndex,
                new BookingDescriptorBuilder()
                        .withVenueName(HALL_STRING)
                        .withDay(MONDAY_STRING)
                        .withHourPeriod(HP_5_TO_6PM_STRING)
                        .build());

        assertThrows(CommandException.class, expectedMessage, ()
                -> bookCommand.execute(model));
    }

    @Test
    public void equals() {
        BookingDescriptor validBookingDescriptor = DESC_MR_ALICE_MONDAY_5_TO_6PM;
        final BookCommand standardCommand = new BookCommand(INDEX_FIRST_RESIDENT,
                validBookingDescriptor);

        // same values -> returns true
        BookingDescriptor copyDescriptor = new BookingDescriptorBuilder(MR_ALICE_MONDAY_5_TO_6PM).build();
        BookCommand commandWithSameValues = new BookCommand(INDEX_FIRST_RESIDENT, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new BookCommand(INDEX_SECOND_RESIDENT, validBookingDescriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new BookCommand(INDEX_FIRST_RESIDENT, DESC_MR_BOB_TUESDAY_6_TO_7PM)));
    }

    //======================== Start of model stubs ===============================================

    /**
     * A Model stub that always accept the Booking being added.
     */
    public static class ModelStubAcceptingBookingAdded extends ModelStub {
        public final ArrayList<Booking> bookingsAdded = new ArrayList<>();

        @Override
        public ObservableList<Resident> getFilteredResidentList() {
            return TypicalResidents.getTypicalResidentBook().getResidentList();
        }

        @Override
        public void addBooking(VenueName venueName, Booking booking) {
            requireAllNonNull(venueName, booking);
            bookingsAdded.add(booking);
        }

        @Override
        public void setCurrentlyDisplayedVenue(VenueName venueName) {
        }
    }

    /**
     * A Model stub with an existing booking.
     */

    public static class ModelStubWithExistingBooking extends ModelStub {

        public final Booking booking = MR_ALICE_MONDAY_5_TO_7PM;
        private Venue meetingRoom = new Venue(getTypicalVenues().get(0).getVenueName());

        /**
         * Constructs a model stub with a single venue that has a single booking.
         */
        private ModelStubWithExistingBooking() {
            meetingRoom.addBooking(MR_ALICE_MONDAY_5_TO_7PM);
        }

        @Override
        public ObservableList<Resident> getFilteredResidentList() {
            return TypicalResidents.getTypicalResidentBook().getResidentList();
        }

        @Override
        public void addBooking(VenueName venueName, Booking booking) throws VenueNotFoundException {
            requireAllNonNull(venueName, booking);
            if (meetingRoom.isSameVenue(venueName)) {
                meetingRoom.addBooking(booking);
            } else {
                throw new VenueNotFoundException();
            }
        }

        @Override
        public void setCurrentlyDisplayedVenue(VenueName venueName) {
        }

    }

}
