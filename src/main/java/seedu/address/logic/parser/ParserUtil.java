package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;
import seedu.address.model.remark.Text;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Goods;
import seedu.address.model.transaction.Price;
import seedu.address.model.transaction.Quantity;

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
     * Parses a {@code String phone} into a {@code ClientPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static ClientPhone parseClientPhone(String phone) {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();

        ClientPhone newPhone = new ClientPhone(trimmedPhone);
        if (!ClientPhone.isValidPhone(trimmedPhone)) {
            newPhone.setWarning();
        }

        return newPhone;
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
     * Parses a {@code String text} into a {@code Text}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code text} is invalid.
     */
    public static Text parseText(String text) throws ParseException {
        requireNonNull(text);
        String trimmedText = text.trim();
        if (!Text.isValidText(trimmedText)) {
            throw new ParseException(Text.MESSAGE_CONSTRAINTS);
        }
        return new Text(trimmedText);
    }

    /**
     * Parses a {@code String email} into an {@code ClientEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static ClientEmail parseClientEmail(String email) {
        requireNonNull(email);
        String trimmedEmail = email.trim();

        ClientEmail newEmail = new ClientEmail(trimmedEmail);
        if (!ClientEmail.isValidEmail(trimmedEmail)) {
            newEmail.setWarning();
        }

        return newEmail;
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
     * Parses a {@code String goods} into an {@code Goods}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code goods} is invalid.
     */
    public static Goods parseGoods(String goods) throws ParseException {
        requireNonNull(goods);
        String trimmedGoods = goods.trim();
        if (!Goods.isValidName(trimmedGoods)) {
            throw new ParseException(Goods.MESSAGE_CONSTRAINTS);
        }
        return new Goods(goods);
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
        Price.parsePriceArguments(trimmedPrice);
        return new Price(price);
    }

    /**
     * Parses a {@code String quantity} into an {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        Quantity.parseQuantityArguments(trimmedQuantity);
        return new Quantity(quantity);
    }


    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDateFormat(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        }
        if (!Date.isValidDateInput(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS_WRONG_DATE);
        }
        return new Date(date);
    }
}
