package seedu.condonery.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.commons.util.StringUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

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
     * Parses a {@code String price} into an {@code Price}.
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String val} into a {@code int}.
     */
    public static Integer parseNumber(String val) throws ParseException {
        requireNonNull(val);
        return Integer.parseInt(val.replaceAll(",", ""));
    }

    /**
     * Parses a {@code String clientName} into a {@code Client}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientName} does not match to a unique
     *     existing client's name.
     */
    public static Client parseClientName(String clientName) throws ParseException {
        requireNonNull(clientName);
        String trimmedClientName = clientName.trim();
        return new Client(
                new Name(clientName),
                new Address("placeholder"),
                new HashSet<>(),
                new HashSet<>()
                );
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Client>}.
     */
    public static Set<Client> parseClients(Collection<String> clients) throws ParseException {
        requireNonNull(clients);
        final Set<Client> clientSet = new HashSet<>();
        for (String clientName : clients) {
            clientSet.add(parseClientName(clientName));
        }
        return clientSet;
    }

    /**
     * Parses a {@code String propertyName} into a {@code Property}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code propretyName} does not match to a unique
     *     existing property's name.
     */
    public static Property parsePropertyName(String propertyName) throws ParseException {
        requireNonNull(propertyName);
        String trimmedPropertyName = propertyName.trim();
        return new Property(
                new Name(propertyName),
                new Address("placeholder"),
                new Price("1000000"),
                new HashSet<Tag>(),
                new HashSet<Client>(),
                PropertyTypeEnum.valueOf("CONDO"),
                PropertyStatusEnum.valueOf("AVAILABLE")
        );
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Property>}.
     */
    public static Set<Property> parseProperties(Collection<String> properties) throws ParseException {
        requireNonNull(properties);
        final Set<Property> propertySet = new HashSet<>();
        for (String propertyName : properties) {
            propertySet.add(parsePropertyName(propertyName));
        }
        return propertySet;
    }

    /**
     * Parses a string into {@code PropertyTypeEnum} enum
     * @param propertyType string to PropertyTypeEnum
     * @return a {@code PropertyStatusEnum} enum
     * @throws ParseException if the string does not match any valid enum
     */
    public static PropertyTypeEnum parsePropertyType(String propertyType) throws ParseException {
        requireNonNull(propertyType);
        try {
            return PropertyTypeEnum.valueOf(propertyType.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ParseException(
                "Invalid Property Type specified! Property Type must be one of HDB, CONDO, or LANDED");
        }
    }

    /**
     * Parses a string into {@code PropertyStatusEnum} enum
     * @param propertyStatus string to PropertyStatusEnum
     * @return a {@code PropertyStatusEnum} enum
     * @throws ParseException if the string does not match any valid enum
     */
    public static PropertyStatusEnum parsePropertyStatus(String propertyStatus) throws ParseException {
        requireNonNull(propertyStatus);
        try {
            return PropertyStatusEnum.valueOf(propertyStatus.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new ParseException(
                "Invalid Property Status specified! Property Type must be one of AVAILABLE, SOLD, or PENDING");
        }
    }

}
