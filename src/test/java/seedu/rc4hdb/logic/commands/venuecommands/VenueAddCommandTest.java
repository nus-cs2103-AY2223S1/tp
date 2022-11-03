package seedu.rc4hdb.logic.commands.venuecommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.ModelStub;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.venues.Venue;
import seedu.rc4hdb.model.venues.VenueName;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;
import seedu.rc4hdb.testutil.TypicalVenues;

public class VenueAddCommandTest {

    @Test
        public void constructor_nullVenueName_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new VenueAddCommand(null));
        }

        @Test
        public void execute_venueAcceptedByModel_addSuccessful() throws Exception {
            ModelStubAcceptingVenueAdded modelStub = new ModelStubAcceptingVenueAdded();
            VenueName validVenue = new VenueName("Meeting Room");

            CommandResult commandResult = new VenueAddCommand(validVenue).execute(modelStub);

            assertEquals(String.format(VenueAddCommand.MESSAGE_SUCCESS, validVenue), commandResult.getFeedbackToUser());
            assertEquals(Arrays.asList(TypicalVenues.getTypicalVenue().get(0)), modelStub.venuesAdded);
        }

        @Test
        public void execute_duplicateVenue_throwsCommandException() {
            VenueName validVenueName = new VenueName("Meeting Room");
            Venue validVenue = new Venue(validVenueName);
            VenueAddCommand venueAddCommand = new VenueAddCommand(validVenueName);
            ModelStub modelStub = new ModelStubWithVenue(validVenue);

            assertThrows(CommandException.class, String.format(VenueAddCommand.MESSAGE_DUPLICATE_VENUE, validVenueName), ()
                    -> venueAddCommand.execute(modelStub));
        }

        @Test
        public void equals() {
            VenueName meetingRoomName = new VenueName("Meeting Room");
            VenueName hallName = new VenueName("Hall");
            VenueAddCommand addMeetingRoomCommand = new VenueAddCommand(meetingRoomName);
            VenueAddCommand addHallCommand = new VenueAddCommand(hallName);

            // same object -> returns true
            assertTrue(addMeetingRoomCommand.equals(addMeetingRoomCommand));

            // same values -> returns true
            VenueAddCommand addMeetingRoomCommandCopy = new VenueAddCommand(meetingRoomName);
            assertTrue(addMeetingRoomCommand.equals(addMeetingRoomCommandCopy));

            // different types -> returns false
            assertFalse(addMeetingRoomCommand.equals(1));

            // null -> returns false
            assertFalse(addMeetingRoomCommand.equals(null));

            // different person -> returns false
            assertFalse(addMeetingRoomCommand.equals(addHallCommand));
        }

        //======================== Start of model stubs ===============================================

        /**
         * A Model stub that contains a single venue.
         */
        public static class ModelStubWithVenue extends ModelStub {
            private final Venue venue;

            /**
             * Constructs a model stub with a single venue.
             */
            public ModelStubWithVenue(Venue venue) {
                requireNonNull(venue);
                this.venue = venue;
            }

            @Override
            public boolean hasVenue(Venue venue) {
                requireNonNull(venue);
                return this.venue.isSameVenue(venue);
            }

            @Override
            public void addVenue(Venue venue) throws DuplicateVenueException {
                requireNonNull(venue);
                if (this.hasVenue(venue)) {
                    throw new DuplicateVenueException();
                }
            }
        }

        /**
         * A Model stub that always accept the venue being added.
         */
        public static class ModelStubAcceptingVenueAdded extends ModelStub {
            public final ArrayList<Venue> venuesAdded = new ArrayList<>();

            @Override
            public boolean hasVenue(Venue venue) {
                requireNonNull(venue);
                return venuesAdded.stream().anyMatch(venue::isSameVenue);
            }

            @Override
            public void addVenue(Venue venue) {
                requireNonNull(venue);
                venuesAdded.add(venue);
            }

            @Override
            public ReadOnlyVenueBook getVenueBook() {
                return new VenueBook();
            }
        }
}
