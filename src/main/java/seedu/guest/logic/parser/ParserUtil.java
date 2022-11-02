package seedu.guest.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.guest.commons.core.index.Index;
import seedu.guest.commons.util.StringUtil;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.guest.Bill;
import seedu.guest.model.guest.DateRange;
import seedu.guest.model.guest.Email;
import seedu.guest.model.guest.IsRoomClean;
import seedu.guest.model.guest.Name;
import seedu.guest.model.guest.NumberOfGuests;
import seedu.guest.model.guest.Phone;
import seedu.guest.model.guest.Request;
import seedu.guest.model.guest.Room;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String room} into an {@code Room}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code room} is invalid.
     */
    public static Room parseRoom(String room) throws ParseException {
        requireNonNull(room);
        String trimmedRoom = room.trim();
        if (!Room.isValidRoom(trimmedRoom)) {
            throw new ParseException(Room.MESSAGE_CONSTRAINTS);
        }
        return new Room(trimmedRoom);
    }

    /**
     * Parses a {@code String dateRange} into a {@code DateRange}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateRange} is invalid.
     */
    public static DateRange parseDateRange(String dateRange) throws ParseException {
        requireNonNull(dateRange);
        String trimmedDateRange = dateRange.trim();
        if (!DateRange.isValidDateRange(trimmedDateRange)) {
            throw new ParseException(DateRange.MESSAGE_CONSTRAINTS);
        }
        return new DateRange(trimmedDateRange);
    }

    /**
     * Parses a {@code String numberOfGuests} into a {@code NumberOfGuests}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code numberOfGuests} is invalid.
     */
    public static NumberOfGuests parseNumberOfGuests(String numberOfGuests) throws ParseException {
        requireNonNull(numberOfGuests);
        String trimmedNumberOfGuests = numberOfGuests.trim();
        if (!NumberOfGuests.isValidNumberOfGuests(trimmedNumberOfGuests)) {
            throw new ParseException(NumberOfGuests.MESSAGE_CONSTRAINTS);
        }
        return new NumberOfGuests(trimmedNumberOfGuests);
    }

    /**
     * Parses a {@code String isRoomClean} into an {@code IsRoomClean}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code isRoomClean} is invalid.
     */
    public static IsRoomClean parseIsRoomClean(String isRoomClean) throws ParseException {
        requireNonNull(isRoomClean);
        String trimmedIsRoomClean = isRoomClean.trim();
        if (!IsRoomClean.isValidIsRoomClean(trimmedIsRoomClean)) {
            throw new ParseException(IsRoomClean.MESSAGE_CONSTRAINTS);
        }
        return new IsRoomClean(trimmedIsRoomClean);
    }

    /**
     * Parses a {@code String bill} into a {@code Bill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bill} is invalid.
     */
    public static Bill parseBill(String bill) throws ParseException {
        requireNonNull(bill);
        String trimmedBill = bill.trim();
        if (!Bill.isValidBill(trimmedBill)) {
            throw new ParseException(Bill.MESSAGE_CONSTRAINTS);
        }
        return new Bill(trimmedBill);
    }

    /**
     * Parses a {@code String request} into a {@code Request}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code request} is invalid.
     */
    public static Request parseRequest(String request) throws ParseException {
        requireNonNull(request);
        String trimmedRequest = request.trim();
        if (!Request.isValidRequest(trimmedRequest)) {
            throw new ParseException(Request.MESSAGE_CONSTRAINTS);
        }
        if (trimmedRequest.equals("")) {
            return new Request();
        }
        return new Request(trimmedRequest);
    }
}
