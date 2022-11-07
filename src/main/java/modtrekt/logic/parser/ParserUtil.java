package modtrekt.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.stream.Stream;

import modtrekt.commons.core.index.Index;
import modtrekt.commons.util.StringUtil;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.task.Description;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index must be a non-zero unsigned integer.";

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
     * Parses a {@code String name} into a {@code ModName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Description parseDescription(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Description.isValidDescription(trimmedName)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedName);
    }

    /**
     * Parses a {@code String date} into a {@code localDate}
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDueDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d")
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDate.parse(trimmedDate, formatter);
        } catch (DateTimeParseException exception) {
            throw new ParseException("Invalid date, date must be in YYYY-MM-DD format");
        }
    }

    /**
     * Parses a {@code String name} into a {@code ModName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ModName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ModName.isValidName(trimmedName)) {
            throw new ParseException(ModName.MESSAGE_CONSTRAINTS);
        }
        return new ModName(trimmedName);
    }

    /**
     * Parses a {@code String credit} into an {@code ModCredit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code credit} is invalid.
     */
    public static ModCredit parseCredit(String credit) throws ParseException {
        requireNonNull(credit);
        String trimmedCredit = credit.trim();
        if (!ModCredit.isValidCredit(trimmedCredit)) {
            throw new ParseException(ModCredit.MESSAGE_CONSTRAINTS);
        }
        return new ModCredit(trimmedCredit);
    }
    /**
     * Parses a {@code String code} into an {@code ModCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static ModCode parseCode(String code) throws ParseException {
        requireNonNull(code);
        String trimmedCode = code.trim();
        if (!ModCode.isValidCode(trimmedCode)) {
            throw new ParseException(ModCode.MESSAGE_CONSTRAINTS);
        }
        return new ModCode(trimmedCode);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
