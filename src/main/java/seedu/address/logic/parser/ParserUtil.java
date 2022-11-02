package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.sortcomparators.Order;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;
import seedu.address.model.property.Description;
import seedu.address.model.property.Price;
import seedu.address.model.property.Properties;
import seedu.address.model.property.PropertyName;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a positive integer (may be too large)" +
            " or is unspecified.";

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
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim().toUpperCase();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses {@code String range} into a {@code PriceRange}.
     */
    public static PriceRange parsePriceRange(String range) throws ParseException {
        requireNonNull(range);
        String trimmedRange = range.trim();
        /*TODO: Currently the price range accepted has to be in the exact format of LOW - HIGH
        with no allowance for extra white spaces within e.g 200 -     500.
        Accepted white spaces are only around e.g "    200 - 500     ".
        Something to consider for further enhancement if we want to make it
        more flexible, but not a priority!*/
        if (!PriceRange.isValidPriceRange(trimmedRange)) {
            throw new ParseException(PriceRange.MESSAGE_CONSTRAINTS);
        }
        return new PriceRange(trimmedRange);
    }

    /**
     * Parses {@code String characteristics} into a {@code Characteristics}.
     */
    public static Characteristics parseCharacteristics(String characteristics) throws ParseException {
        requireNonNull(characteristics);
        String trimmedCharacteristics = characteristics.trim();
        if (!Characteristics.isValidCharacteristics(trimmedCharacteristics)) {
            throw new ParseException(Characteristics.MESSAGE_CONSTRAINTS);
        }
        return new Characteristics(trimmedCharacteristics);
    }

    /**
     * Parses {@code String properties} into a {@code List<Integer>}.
     */
    // TODO: Actually parse properties here?
    public static Properties parseProperties(String properties) throws ParseException {
        if (properties.isEmpty()) {
            return new Properties("");
        }
        String trimmedProperties = properties.trim();
        List<Integer> propertyArray = Arrays.stream(trimmedProperties.split(";"))
                .map(item -> Integer.parseInt(item.trim()))
                .collect(Collectors.toList());

        // TODO: should add a check that all values that are ; separated are integers

        return new Properties("");
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses a {@code String propertyName} into a {@code PropertyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code propertyName} is invalid.
     */
    public static PropertyName parsePropertyName(String propertyName) throws ParseException {
        requireNonNull(propertyName);
        String trimmedPropertyName = propertyName.trim();
        if (!PropertyName.isValidPropertyName(trimmedPropertyName)) {
            throw new ParseException(PropertyName.MESSAGE_CONSTRAINTS);
        }
        return new PropertyName(trimmedPropertyName);
    }

    /**
     * Parses a {@code String order} into an {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static Order parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (!Order.isValidOrder(trimmedOrder)) {
            throw new ParseException(Order.MESSAGE_CONSTRAINTS);
        }
        return new Order(trimmedOrder);
    }
}
