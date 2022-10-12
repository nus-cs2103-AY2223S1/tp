package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.link.Link;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.task.Task;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods used for parsing strings in the various
 * {@code Parser} classes.
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
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String moduleTitle} into a {@code ModuleTitle}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ModuleTitle parseModuleTitle(String moduleTitle) {
        requireNonNull(moduleTitle);
        String trimmedModuleTitle = moduleTitle.trim();
        return new ModuleTitle(trimmedModuleTitle);
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
     * Parses a {@code String link} into a {@code Link}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code link} is invalid.
     */
    public static Link parseLink(String link) throws ParseException {
        requireNonNull(link);
        String trimmedLink = link.trim();
        if (!Link.isValidLinkName(trimmedLink)) {
            throw new ParseException(Link.MESSAGE_CONSTRAINTS);
        }
        return new Link(trimmedLink);
    }

    /**
     * Parses {@code Collection<String> links} into a {@code Set<Link>}.
     */
    public static Set<Link> parseLinks(Collection<String> links) throws ParseException {
        requireNonNull(links);
        final Set<Link> linkSet = new HashSet<>();
        for (String link : links) {
            linkSet.add(parseLink(link));
        }
        return linkSet;
    }

    //@@author cheeheng-reused
    //AB3 code moved from AddCommand because at least three different classes using this method
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


    /**
     * Parses a {@code String} task description into a {@code Task}.
     * Leading and trailing whitespaces of description will be trimmed.
     * @throws ParseException if the given description is invalid.
     */
    public static Task parseNewTaskDescription(String taskDescription)
            throws ParseException {
        requireNonNull(taskDescription);
        String trimmedDescription = taskDescription.trim();
        if (!Task.isValidTaskDescription(trimmedDescription)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(trimmedDescription);
    }

    /**
     * Parses a {@code String} task number into an {@code Index}.*
     * @throws ParseException if the given number is invalid.
     */
    public static Index parseTaskNumberToDelete(String oneBasedTaskNumber)
            throws ParseException {
        requireNonNull(oneBasedTaskNumber);
        String trimmedIndex = oneBasedTaskNumber.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }
}
