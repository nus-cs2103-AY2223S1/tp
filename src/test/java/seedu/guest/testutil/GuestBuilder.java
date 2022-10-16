package seedu.guest.testutil;

import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Room;

/**
 * A utility class to help with building Guest objects.
 */
public class GuestBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ROOM = "05-73";
    public static final String DEFAULT_DATE_RANGE = "13/09/22 - 15/09/22";
    public static final String DEFAULT_NUMBER_OF_GUESTS = "1";
    public static final String DEFAULT_IS_ROOM_CLEAN = "yes";

    private Name name;
    private Phone phone;
    private Email email;
    private Room room;
    private DateRange dateRange;
    private NumberOfGuests numberOfGuests;
    private IsRoomClean isRoomClean;

    /**
     * Creates a {@code GuestBuilder} with the default details.
     */
    public GuestBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        room = new Room(DEFAULT_ROOM);
        dateRange = new DateRange(DEFAULT_DATE_RANGE);
        numberOfGuests = new NumberOfGuests(DEFAULT_NUMBER_OF_GUESTS);
        isRoomClean = new IsRoomClean(DEFAULT_IS_ROOM_CLEAN);
    }

    /**
     * Initializes the GuestBuilder with the data of {@code guestToCopy}.
     */
    public GuestBuilder(Guest guestToCopy) {
        name = guestToCopy.getName();
        phone = guestToCopy.getPhone();
        email = guestToCopy.getEmail();
        room = guestToCopy.getRoom();
        dateRange = guestToCopy.getDateRange();
        numberOfGuests = guestToCopy.getNumberOfGuests();
        isRoomClean = guestToCopy.getIsRoomClean();
    }

    /**
     * Sets the {@code Name} of the {@code Guest} that we are building.
     */
    public GuestBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Guest} that we are building.
     */
    public GuestBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Guest} that we are building.
     */
    public GuestBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Guest} that we are building.
     */
    public GuestBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code DateRange} of the {@code Guest} that we are building.
     */
    public GuestBuilder withDateRange(String dateRange) {
        this.dateRange = new DateRange(dateRange);
        return this;
    }

    /**
     * Sets the {@code NumberOfGuests} of the {@code Guest} that we are building.
     */
    public GuestBuilder withNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = new NumberOfGuests(numberOfGuests);
        return this;
    }

    /**
     * Sets the {@code IsRoomClean} of the {@code Guest} that we are building.
     */
    public GuestBuilder withIsRoomClean(String isRoomClean) {
        this.isRoomClean = new IsRoomClean(isRoomClean);
        return this;
    }

    public Guest build() {
        return new Guest(name, phone, email, room, dateRange, numberOfGuests, isRoomClean);
    }

}
