package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AdditionalNotes;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.NokPhone;
import seedu.address.model.person.Phone;

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
     * Parses a {@code String nokPhone} into a {@code NokPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code NokPhone} is invalid.
     */
    public static NokPhone parseNokPhone(String nokPhone) throws ParseException {
        requireNonNull(nokPhone);
        String trimmedNokPhone = nokPhone.trim();
        if (!NokPhone.isValidNokPhone(trimmedNokPhone)) {
            throw new ParseException(NokPhone.MESSAGE_CONSTRAINTS);
        }
        return new NokPhone(trimmedNokPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String classDatetime} into a {@code Class}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static Class parseClass(String classDatetime) throws ParseException {
        requireNonNull(classDatetime);
        String trimmedClassDatetime = classDatetime.trim();
        if (!Class.isValidClassString(trimmedClassDatetime)) {
            throw new ParseException(Class.MESSAGE_CONSTRAINTS);
        }

        // the format has been validated in isValidClassString method
        // ie yyyy-MM-dd 0000-2359
        String datetimeStr = trimmedClassDatetime.substring(0, 10);
        String startTimeStr = trimmedClassDatetime.substring(11, 15);
        String endTimeStr = trimmedClassDatetime.substring(16);

        LocalDate date = parseDate(datetimeStr);
        LocalTime startTime = parseTime(startTimeStr);
        LocalTime endTime = parseTime(endTimeStr);
        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            throw new ParseException(Class.INVALID_DURATION_ERROR_MESSAGE);
        }
        return new Class(date, startTime, endTime, classDatetime);
    }

    /**
     * Helper method to parse {@code date} as part of {@code parseClass}.
     */
    private static LocalDate parseDate(String date) throws ParseException {
        LocalDate result;
        try {
            result = LocalDate.parse(date);
        } catch (DateTimeParseException de) {
            throw new ParseException(Class.INVALID_DATETIME_ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * Helper method to parse {@code time} as part of {@code parseClass}.
     */
    private static LocalTime parseTime(String time) throws ParseException {
        Integer hour = Integer.valueOf(time.substring(0, 2));
        Integer minute = Integer.valueOf(time.substring(2));
        LocalTime result;
        try {
            result = LocalTime.of(hour, minute);
        } catch (DateTimeException de) {
            throw new ParseException(Class.INVALID_TIME_ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * Parses a {@code String money} into an {@code Money}.
     *
     * @throws ParseException if the given {@code money} is invalid.
     */
    public static Money parseMoney(String money) throws ParseException {
        requireNonNull(money);
        Integer value;
        try {
            value = Integer.valueOf(money);
        } catch (NumberFormatException ex) {
            throw new ParseException(Money.MESSAGE_CONSTRAINTS);
        }

        if (!Money.isValidMoney(value)) {
            throw new ParseException(Money.MESSAGE_CONSTRAINTS);
        }
        return new Money(value);
    }

    /**
     * Parses an {@code String additionalNotes} into an {@code AdditionalNotes}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static AdditionalNotes parseAdditionalNotes(String additionalNotes) {
        requireNonNull(additionalNotes);
        return new AdditionalNotes(additionalNotes.trim());
    }

}
