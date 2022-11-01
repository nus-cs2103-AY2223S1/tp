package seedu.rc4hdb.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_MONDAY_5_TO_6PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_ALICE_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalBookings.MR_BOB_TUESDAY_6_TO_7PM;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL;
import static seedu.rc4hdb.testutil.TypicalVenues.HALL_VENUE_NAME;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM;
import static seedu.rc4hdb.testutil.TypicalVenues.MEETING_ROOM_VENUE_NAME;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.model.resident.predicates.NameContainsKeywordsPredicate;
import seedu.rc4hdb.model.venues.booking.BookingDescriptor;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingClashesException;
import seedu.rc4hdb.model.venues.booking.exceptions.BookingNotFoundException;
import seedu.rc4hdb.model.venues.booking.fields.Day;
import seedu.rc4hdb.model.venues.booking.fields.HourPeriod;
import seedu.rc4hdb.model.venues.exceptions.DuplicateVenueException;
import seedu.rc4hdb.model.venues.exceptions.VenueNotFoundException;
import seedu.rc4hdb.testutil.ResidentBookBuilder;
import seedu.rc4hdb.testutil.VenueBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager;

    @BeforeEach
    public void setUp() {
        modelManager = new ModelManager();
    }
    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ResidentBook(), new ResidentBook(modelManager.getResidentBook()));
        assertEquals(new VenueBook(), new VenueBook(modelManager.getVenueBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDataStorageFilePath(Paths.get("rc4hdb/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setDataStorageFilePath(Paths.get("new/rc4hdb/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setResidentBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefDataFilePath(null));
    }

    @Test
    public void setResidentBookFilePath_validPath_setsResidentBookFilePath() {
        Path path = Paths.get("rc4hdb/book/file/path");
        modelManager.setUserPrefDataFilePath(path);
        assertEquals(path, modelManager.getUserPrefDataFilePath());
    }

    @Test
    public void hasResident_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasResident(null));
    }


    @Test
    public void hasResident_residentNotInResidentBook_returnsFalse() {
        assertFalse(modelManager.hasResident(ALICE));
    }

    @Test
    public void hasResident_residentInResidentBook_returnsTrue() {
        modelManager.addResident(ALICE);
        assertTrue(modelManager.hasResident(ALICE));
    }

    @Test
    public void getFilteredResidentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredResidentList().remove(0));
    }

    @Test
    public void equals() {
        ResidentBook residentBook = new ResidentBookBuilder().withResident(ALICE).withResident(BENSON).build();
        ResidentBook differentResidentBook = new ResidentBook();
        VenueBook venueBook = new VenueBookBuilder().withVenue(HALL).withVenue(MEETING_ROOM).build();
        VenueBook differentVenueBook = new VenueBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(residentBook, venueBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(residentBook, venueBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different residentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentResidentBook, venueBook, userPrefs)));

        // different venueBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(residentBook, differentVenueBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().value.split("\\s+");
        modelManager.updateFilteredResidentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(residentBook, venueBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setDataStorageFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(residentBook, venueBook, differentUserPrefs)));
    }

    //=========== Venue book tests ========================================

    @Test
    public void hasVenue_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasVenue(null));
    }
    @Test
    public void hasVenue_venueNotInResidentBook_returnsFalse() {
        assertFalse(modelManager.hasVenue(MEETING_ROOM));
    }
    @Test
    public void hasVenue_venueInResidentBook_returnsTrue() {
        modelManager.addVenue(MEETING_ROOM);
        assertTrue(modelManager.hasVenue(MEETING_ROOM));
    }

    @Test
    public void deleteVenue_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteVenue(null));
    }

    @Test
    public void deleteVenue_venueNotInResidentBook_returnsFalse() {
        assertThrows(VenueNotFoundException.class, () -> modelManager.deleteVenue(MEETING_ROOM_VENUE_NAME));
    }

    @Test
    public void deleteVenue_venueInVenueBook_returnsTrue() {
        modelManager.addVenue(MEETING_ROOM);
        modelManager.deleteVenue(MEETING_ROOM_VENUE_NAME);
        assertEquals(new VenueBook(), new VenueBook(modelManager.getVenueBook()));
    }

    @Test
    public void addVenue_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addVenue(null));
    }

    @Test
    public void addVenue_duplicateVenue_throwsDuplicateVenueException() {
        modelManager.addVenue(MEETING_ROOM);
        assertThrows(DuplicateVenueException.class, () -> modelManager.addVenue(MEETING_ROOM));
    }

    @Test
    public void addVenue_venueNotInVenueBook_returnsTrue() {
        modelManager.addVenue(MEETING_ROOM);
        assertEquals(new VenueBookBuilder().withVenue(MEETING_ROOM).build(),
                new VenueBook(modelManager.getVenueBook()));
    }

    @Test
    public void addBooking_nullVenueName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addBooking(null,
                MR_ALICE_MONDAY_5_TO_6PM));
    }

    @Test
    public void addBooking_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addBooking(MEETING_ROOM_VENUE_NAME, null));
    }

    @Test
    public void addBooking_venueNotInVenueBook_throwsVenueNotFoundException() {
        assertThrows(VenueNotFoundException.class, () -> modelManager.addBooking(MEETING_ROOM_VENUE_NAME,
                MR_ALICE_MONDAY_5_TO_6PM));
    }

    @Test
    public void addBooking_bookingClash_throwsBookingClashException() {
        modelManager.addVenue(MEETING_ROOM);
        modelManager.addBooking(MEETING_ROOM_VENUE_NAME, MR_BOB_TUESDAY_6_TO_7PM);
        assertThrows(BookingClashesException.class, () -> modelManager.addBooking(MEETING_ROOM_VENUE_NAME,
                MR_BOB_TUESDAY_6_TO_7PM));
    }

    @Test
    public void addBooking_validVenueValidBooking_returnsTrue() {
        modelManager.addVenue(MEETING_ROOM);
        modelManager.addBooking(MEETING_ROOM_VENUE_NAME, MR_ALICE_MONDAY_5_TO_6PM);
        VenueBook expected = new VenueBookBuilder()
                .withVenue(MEETING_ROOM).build(); // VenueBuilder has default bookings
        assertEquals(expected, new VenueBook(modelManager.getVenueBook()));
    }

    @Test
    public void removeBooking_nullBookingDescriptor_throwsBookingNotFoundException() {
        assertThrows(NullPointerException.class, () -> modelManager.removeBooking(null));
    }

    @Test
    public void removeBooking_venueNotInVenueBook_throwsVenueNotFoundException() {
        BookingDescriptor booking = new BookingDescriptor(createBookingDescriptor());
        modelManager.addVenue(HALL);
        modelManager.addBooking(HALL_VENUE_NAME, MR_ALICE_TUESDAY_6_TO_7PM);
        assertThrows(VenueNotFoundException.class, () -> modelManager.removeBooking(booking));
    }

    @Test
    public void removeBooking_bookingNotInVenueBook_throwsBookingNotFoundException() {
        BookingDescriptor booking = new BookingDescriptor(createBookingDescriptor());
        modelManager.addVenue(MEETING_ROOM);
        assertThrows(BookingNotFoundException.class, () -> modelManager.removeBooking(booking));
    }

    public BookingDescriptor createBookingDescriptor() {
        BookingDescriptor booking = new BookingDescriptor();
        booking.setVenueName(MEETING_ROOM_VENUE_NAME);
        booking.setResident(ALICE);
        booking.setHourPeriod(new HourPeriod("22-23"));
        booking.setDayOfWeek(new Day("TUE"));
        return booking;
    }


}
