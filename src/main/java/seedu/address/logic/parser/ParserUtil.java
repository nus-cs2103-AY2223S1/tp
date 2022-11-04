package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.note.Content;
import seedu.address.model.note.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Loan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reason;
import seedu.address.model.tag.Tag;

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
    public static Tag parseTag(String tag, Model model) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        //INSERTION POINT: Retrieve tag from unique list
        //return new Tag(trimmedTag);
        return Optional.ofNullable(model.getTagMapping().get(trimmedTag)).orElseGet(
                // CHECKSTYLE.OFF: SeparatorWrap
                () -> {
                    Tag newTag = new Tag(trimmedTag);
                    model.addTag(newTag);
                    return newTag;
                });
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags, Model model) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName, model));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String content} into an {@code Content}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code content} is invalid.
     */
    public static Content parseContent(String content) throws ParseException {
        requireNonNull(content);
        String trimmedContent = content.trim();
        if (!Content.isValidContent(trimmedContent)) {
            throw new ParseException(Content.MESSAGE_CONSTRAINTS);
        }
        return new Content(trimmedContent);
    }

    /**
     * Parses a {@code String birthday} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        if (!Birthday.isInPast(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_DATE_NOT_FUTURE);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String loanAmout} into a {@code Loan}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code loanAmount} is invalid.
     */
    public static Loan parseLoan(String loanAmount) throws ParseException {
        requireNonNull(loanAmount);
        String trimmedLoan = loanAmount.trim();
        if (!Loan.isValidLoan(trimmedLoan)) {
            throw new ParseException(Loan.MESSAGE_CONSTRAINTS);
        }
        return new Loan(trimmedLoan);
    }

    /**
     * Parses a {@code String reason} into a {@code Reason}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reason} is invalid.
     */
    public static Reason parseReason(String reason) throws ParseException {
        requireNonNull(reason);
        String trimmedReason = reason.trim();
        if (!Reason.isValidReason(reason)) {
            throw new ParseException(Reason.MESSAGE_CONSTRAINTS);
        }
        return new Reason(trimmedReason);
    }
}
