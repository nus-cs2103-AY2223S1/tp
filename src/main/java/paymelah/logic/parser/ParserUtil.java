package paymelah.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import paymelah.commons.core.index.Index;
import paymelah.commons.util.StringUtil;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.model.person.Address;
import paymelah.model.person.DebtContainsKeywordsPredicate;
import paymelah.model.person.Email;
import paymelah.model.person.Name;
import paymelah.model.person.NameContainsKeywordsPredicate;
import paymelah.model.person.Phone;
import paymelah.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
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
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param description The description to turn into a {@code Description} object.
     * @return The corresponding {@code Description}.
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
     * Parses a {@code String money} into a {@code Money}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param money The money to turn into a {@code Money} object.
     * @return The corresponding {@code Money}.
     * @throws ParseException if the given {@code money} is invalid.
     */
    public static Money parseMoney(String money) throws ParseException {
        requireNonNull(money);
        String trimmedMoney = money.trim();
        if (!Money.isValidMoney(trimmedMoney)) {
            throw new ParseException(Money.MESSAGE_CONSTRAINTS);
        }
        return new Money(trimmedMoney);
    }

    /**
     * Parses {@code String s} into a {@code NameContainsKeywordsPredicate}.
     */
    public static NameContainsKeywordsPredicate parseNameContainsKeywordsPredicate(String s) throws ParseException {
        requireNonNull(s);
        String trimmed = s.trim();
        if (s.isEmpty()) {
            throw new ParseException(NameContainsKeywordsPredicate.MESSAGE_CONSTRAINTS);
        }
        return new NameContainsKeywordsPredicate(Arrays.asList(trimmed.split("\\s+")));
    }

    /**
     * Parses {@code String s} into a {@code DebtContainsKeywordsPredicate}.
     */
    public static DebtContainsKeywordsPredicate parseDebtContainsKeywordsPredicate(String s) throws ParseException {
        requireNonNull(s);
        String trimmed = s.trim();
        if (s.isEmpty()) {
            throw new ParseException(DebtContainsKeywordsPredicate.MESSAGE_CONSTRAINTS);
        }
        return new DebtContainsKeywordsPredicate(Arrays.asList(trimmed.split("\\s+")));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
