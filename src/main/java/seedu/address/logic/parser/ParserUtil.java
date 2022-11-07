package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_INDEXES;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX_VALUE;
import static seedu.address.model.event.StartTime.MESSAGE_FORMAT_CONSTRAINTS;
import static seedu.address.model.event.StartTime.MESSAGE_VALUE_CONSTRAINTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.Date;
import seedu.address.model.event.EventSortField;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonSortField;
import seedu.address.model.person.Phone;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX_VALUE);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code sortFieldLetter} into a {@code PersonSortField}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param sortFieldLetter A valid sort field letter.
     * @return {@code PersonSortField} representing the sort field letter.
     * @throws ParseException if the given {@code sortFieldLetter} is invalid.
     */
    public static PersonSortField parsePersonSortField(String sortFieldLetter) throws ParseException {
        requireNonNull(sortFieldLetter);
        String trimmedSortFieldLetter = sortFieldLetter.trim();

        if (!PersonSortField.isValidPersonSortField(trimmedSortFieldLetter)) {
            throw new ParseException(PersonSortField.MESSAGE_CONSTRAINTS);
        }

        return PersonSortField.createSortField(trimmedSortFieldLetter);
    }

    /**
     * Parses {@code sortFieldLetter} into a {@code EventSortField}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param sortFieldLetter A valid sort field letter.
     * @return {@code EventSortField} representing the sort field letter.
     * @throws ParseException if the given {@code sortFieldLetter} is invalid.
     */
    public static EventSortField parseEventSortField(String sortFieldLetter) throws ParseException {
        requireNonNull(sortFieldLetter);
        String trimmedSortFieldLetter = sortFieldLetter.trim();

        if (!EventSortField.isValidEventSortField(trimmedSortFieldLetter)) {
            throw new ParseException(EventSortField.MESSAGE_CONSTRAINTS);
        }

        return EventSortField.createSortField(trimmedSortFieldLetter);
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
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses {@code eventTitle} into a {@code EventTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventTitle} is invalid.
     */
    public static EventTitle parseEventTitle(String eventTitle) throws ParseException {
        requireNonNull(eventTitle);
        String trimmedEventTitle = eventTitle.trim();
        if (!EventTitle.isValidEventTitle(trimmedEventTitle)) {
            throw new ParseException(EventTitle.MESSAGE_CONSTRAINTS);
        }
        return new EventTitle(trimmedEventTitle);
    }

    /**
     * Parses a {@code String Date} into a {@code Date}.
     * The {@code isFutureDateAllowed} flag controls whether a date after the current date is allowed or not.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date, boolean isFutureDateAllowed) throws ParseException {
        requireNonNull(date);
        requireNonNull(isFutureDateAllowed);
        String trimmedDate = date.trim();
        //Check if date format is valid.
        if (!Date.isValidDateFormat(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        } else if (!Date.isValidDateValue(trimmedDate)) {
            throw new ParseException(String.format(Date.MESSAGE_VALUE_CONSTRAINTS, trimmedDate));
        }
        //Check if date is after current date and if it is allowed.
        if (Date.isAfterCurrentDate(trimmedDate) && !isFutureDateAllowed) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS_DOB);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String startTime} into a {@code StartTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static StartTime parseStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        if (!StartTime.isValidStartTimeFormat(startTime)) {
            throw new ParseException(MESSAGE_FORMAT_CONSTRAINTS);
        } else if (!StartTime.isValidStartTimeValue(startTime)) {
            throw new ParseException(String.format(MESSAGE_VALUE_CONSTRAINTS, startTime));
        }
        return new StartTime(trimmedStartTime);
    }

    /**
     * Parses {@code purpose} into a {@code Purpose}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code purpose} is invalid.
     */
    public static Purpose parsePurpose(String purpose) throws ParseException {
        requireNonNull(purpose);
        String trimmedPurpose = purpose.trim();
        if (!Purpose.isValidPurpose(trimmedPurpose)) {
            throw new ParseException(Purpose.MESSAGE_CONSTRAINTS);
        }
        return new Purpose(trimmedPurpose);
    }

    /**
     * Parses {@code indexes} into a {@code List<Index>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code indexes} is invalid.
     */
    public static List<Index> parseIndexes(String indexes) throws ParseException {
        requireNonNull(indexes);
        String trimmedIndexes = indexes.trim();
        List<String> strIndexes = Arrays.asList(trimmedIndexes.split("\\s+"));
        List<Index> indexList = new ArrayList<>();
        for (String index : strIndexes) {
            if (!StringUtil.isNonZeroUnsignedInteger(index)) {
                throw new ParseException(String.format(MESSAGE_INVALID_INDEX_VALUE, index));
            }
            Index parsedIndex = Index.fromOneBased(Integer.parseInt(index));
            if (indexList.contains(parsedIndex)) {
                throw new ParseException(String.format(MESSAGE_DUPLICATE_INDEXES, index));
            }
            indexList.add(parsedIndex);
        }
        return indexList;
    }
}
