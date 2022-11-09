package seedu.guest.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.guest.commons.exceptions.IllegalValueException;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Request;
import seedu.guest.model.guest.Room;

/**
 * Jackson-friendly version of {@link Guest}.
 */
class JsonAdaptedGuest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Guest's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String room;
    private final String dateRange;
    private final String numberOfGuests;
    private final String isRoomClean;
    private final String bill;
    private final String request;

    /**
     * Constructs a {@code JsonAdaptedGuest} with the given guest details.
     */
    @JsonCreator
    public JsonAdaptedGuest(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("room") String room,
            @JsonProperty("dateRange") String dateRange,
            @JsonProperty("numberOfGuests") String numberOfGuests,
            @JsonProperty("isRoomClean") String isRoomClean, @JsonProperty("bill") String bill,
            @JsonProperty("request") String request) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.room = room;
        this.dateRange = dateRange;
        this.numberOfGuests = numberOfGuests;
        this.isRoomClean = isRoomClean;
        this.bill = bill;
        this.request = request;
    }

    /**
     * Converts a given {@code Guest} into this class for Jackson use.
     */
    public JsonAdaptedGuest(Guest source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        room = source.getRoom().value;
        dateRange = source.getDateRange().value;
        numberOfGuests = source.getNumberOfGuests().value;
        isRoomClean = source.getIsRoomClean().value;
        bill = source.getBill().value;
        request = source.getRequest().value;
    }

    /**
     * Converts this Jackson-friendly adapted guest object into the model's {@code Guest} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted guest.
     */
    public Guest toModelType() throws IllegalValueException {
        // Name
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        // Phone
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        // Email
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        // Room
        if (room == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName()));
        }
        if (!Room.isValidRoom(room)) {
            throw new IllegalValueException(Room.MESSAGE_CONSTRAINTS);
        }
        final Room modelRoom = new Room(room);

        // Date Range
        if (dateRange == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateRange.class.getSimpleName()));
        }
        if (!DateRange.isValidDateRange(dateRange)) {
            throw new IllegalValueException(DateRange.MESSAGE_CONSTRAINTS);
        }
        final DateRange modelDateRange = new DateRange(dateRange);

        // Number Of Guests
        if (numberOfGuests == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NumberOfGuests.class.getSimpleName()));
        }
        if (!NumberOfGuests.isValidNumberOfGuests(numberOfGuests)) {
            throw new IllegalValueException(NumberOfGuests.MESSAGE_CONSTRAINTS);
        }
        final NumberOfGuests modelNumberOfGuests = new NumberOfGuests(numberOfGuests);

        // Is Room Clean
        if (isRoomClean == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IsRoomClean.class.getSimpleName()));
        }
        if (!IsRoomClean.isValidIsRoomClean(isRoomClean)) {
            throw new IllegalValueException(IsRoomClean.MESSAGE_CONSTRAINTS);
        }
        final IsRoomClean modelIsRoomClean = new IsRoomClean(isRoomClean);

        // Bill
        if (bill == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Bill.class.getSimpleName()));
        }
        if (!Bill.isValidBill(bill)) {
            throw new IllegalValueException(Bill.MESSAGE_CONSTRAINTS);
        }
        final Bill modelBill = new Bill(bill);

        // Request
        if (request == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Request.class.getSimpleName()));
        }
        if (!Request.isValidRequest(request)) {
            throw new IllegalValueException(Request.MESSAGE_CONSTRAINTS);
        }
        final Request modelRequest = new Request(request);

        return new Guest(modelName, modelPhone, modelEmail, modelRoom,
                modelDateRange, modelNumberOfGuests, modelIsRoomClean,
                modelBill, modelRequest);
    }
}
