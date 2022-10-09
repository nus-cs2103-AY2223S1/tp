package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemBoughtDate;
import seedu.address.model.item.ItemExpiryDate;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.ItemQuantity;
import seedu.address.model.item.ItemUnit;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
    public static ItemName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        // TODO: Rewrite validation logic for item name
        //if (!ItemName.isValidName(trimmedName)) {
        //    throw new ParseException(ItemName.MESSAGE_CONSTRAINTS);
        //}
        return new ItemName(trimmedName);
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
     * Parses a {@code String quantity} into a {@link ItemQuantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static ItemQuantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        // TODO: Rewrite validation logic for item quantity
        // if (!ItemQuantity.isValid(trimmedQuantity)) {
        //     throw new ParseException(ItemQuantity.MESSAGE_CONSTRAINTS);
        // }
        return new ItemQuantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String unit} into a {@link ItemUnit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code unit} is invalid.
     */
    public static ItemUnit parseUnit(String unit) throws ParseException {
        requireNonNull(unit);
        String trimmedUnit = unit.trim();
        // TODO: Rewrite validation logic for item unit
        // if (!ItemUnit.isValid(trimmedUnit)) {
        //     throw new ParseException(ItemUnit.MESSAGE_CONSTRAINTS);
        // }
        return new ItemUnit(trimmedUnit);
    }

    /**
     * Parses a {@code String boughtDate} into a {@link ItemBoughtDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code boughtDate} is invalid.
     */
    public static ItemBoughtDate parseBoughtDate(String boughtDate) throws ParseException {
        requireNonNull(boughtDate);
        String trimmedBoughtDate = boughtDate.trim();
        // TODO: Rewrite validation logic for item boughtDate
        // if (!ItemBoughtDate.isValid(trimmedBoughtDate)) {
        //     throw new ParseException(ItemBoughtDate.MESSAGE_CONSTRAINTS);
        // }
        return new ItemBoughtDate(trimmedBoughtDate);
    }

    /**
     * Parses a {@code String expiryDate} into a {@link ItemExpiryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code expiryDate} is invalid.
     */
    public static ItemExpiryDate parseExpiryDate(String expiryDate) throws ParseException {
        requireNonNull(expiryDate);
        String trimmedExpiryDate = expiryDate.trim();
        // TODO: Rewrite validation logic for item expiryDate
        // if (!ItemExpiryDate.isValid(trimmedExpiryDate)) {
        //     throw new ParseException(ItemExpiryDate.MESSAGE_CONSTRAINTS);
        // }
        return new ItemExpiryDate(trimmedExpiryDate);
    }
}
