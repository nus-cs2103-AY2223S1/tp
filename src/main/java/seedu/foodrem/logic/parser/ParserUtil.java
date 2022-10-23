package seedu.foodrem.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.ItemBoughtDate;
import seedu.foodrem.model.item.ItemExpiryDate;
import seedu.foodrem.model.item.ItemName;
import seedu.foodrem.model.item.ItemPrice;
import seedu.foodrem.model.item.ItemQuantity;
import seedu.foodrem.model.item.ItemRemark;
import seedu.foodrem.model.item.ItemUnit;
import seedu.foodrem.model.tag.TagName;

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
     * @throws IllegalArgumentException if the given {@code name} is invalid.
     */
    public static ItemName parseItemName(String name) throws IllegalArgumentException {
        requireNonNull(name);
        String trimmedName = name.trim();
        return new ItemName(trimmedName);
    }

    /**
     * Parses a {@code String quantity} into an {@link ItemQuantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code quantity} is invalid.
     */
    public static ItemQuantity parseQuantity(String quantity) throws IllegalArgumentException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        return new ItemQuantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String unit} into an {@link ItemUnit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code unit} is invalid.
     */
    public static ItemUnit parseUnit(String unit) throws IllegalArgumentException {
        requireNonNull(unit);
        String trimmedUnit = unit.trim();
        return new ItemUnit(trimmedUnit);
    }

    /**
     * Parses a {@code String boughtDate} into an {@link ItemBoughtDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code boughtDate} is invalid.
     */
    public static ItemBoughtDate parseBoughtDate(String boughtDate) throws IllegalArgumentException {
        requireNonNull(boughtDate);
        String trimmedBoughtDate = boughtDate.trim();
        return new ItemBoughtDate(trimmedBoughtDate);
    }

    /**
     * Parses a {@code String expiryDate} into an {@link ItemExpiryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code expiryDate} is invalid.
     */
    public static ItemExpiryDate parseExpiryDate(String expiryDate) throws IllegalArgumentException {
        requireNonNull(expiryDate);
        String trimmedExpiryDate = expiryDate.trim();
        return new ItemExpiryDate(trimmedExpiryDate);
    }

    /**
     * Parses a {@code String price} into an {@link ItemPrice}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code price} is invalid.
     */
    public static ItemPrice parsePrice(String price) throws IllegalArgumentException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        return new ItemPrice(trimmedPrice);
    }

    /**
     * Parses a {@code String price} into an {@link ItemRemark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws IllegalArgumentException if the given {@code remarks} is invalid.
     */
    public static ItemRemark parseRemarks(String remarks) throws IllegalArgumentException {
        requireNonNull(remarks);
        String trimmedRemarks = remarks.trim();
        return new ItemRemark(trimmedRemarks);
    }

    /**
     * Parses a {@code String name} into a tag {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TagName parseTagName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TagName.isValidName(trimmedName)) {
            throw new ParseException(TagName.MESSAGE_CONSTRAINTS);
        }
        return new TagName(trimmedName);
    }

    /**
     * Returns {@code true} if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
