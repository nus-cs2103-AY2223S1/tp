package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTEGER_INDEX;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index must be a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.equals("")) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (trimmedIndex.contains(" ")) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }


        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INTEGER_INDEX);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    // ------------------
    // Person commands
    // ------------------

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

    // ------------------
    // Task commands
    // ------------------

    /**
     * Parses a {@code String taskName} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code TaskName} is invalid.
     */
    public static TaskName parseTaskName(String taskName) throws ParseException {
        requireNonNull(taskName);
        String trimmedName = taskName.trim();
        if (!TaskName.isValidTaskName(trimmedName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedName);
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
        if (!Description.isValidTaskDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim().toLowerCase();
        Optional<PriorityEnum> priorityEnum = PriorityEnum.getFromString(trimmedPriority);

        if (priorityEnum.isEmpty()) {
            throw new ParseException("\"" + priority + "\"" + " is not a valid priority! "
            + Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(priorityEnum.get());
    }

    /**
     * Parses a {@code String taskCategory} into a {@code TaskCategory}.
     *
     * @throws ParseException if the given {@code taskCategory} is invalid.
     */
    public static TaskCategory parseTaskCategory(String taskCategory) throws ParseException {
        requireNonNull(taskCategory);
        String trimmedCategory = taskCategory.trim().toLowerCase();
        Optional<TaskCategoryType> categoryEnum = TaskCategoryType.getFromString(trimmedCategory);
        if (categoryEnum.isEmpty()) {
            throw new ParseException("\"" + taskCategory + "\"" + " is not a valid category! "
            + TaskCategory.MESSAGE_CONSTRAINTS);
        }
        return new TaskCategory(categoryEnum.get()); // TODO: remove hardcoded category level
    }

    /**
     * Parses a {@code String taskDeadline} into a {@code TaskDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDeadline} is does not follow the format YYYY-MM-DD.
     */
    public static TaskDeadline parseTaskDeadline(String taskDeadline) throws ParseException {
        requireNonNull(taskDeadline);
        String trimmedDeadline = taskDeadline.trim();
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(trimmedDeadline);
        } catch (DateTimeParseException e) {
            throw new ParseException("Deadline must be of the form YYYY-MM-DD");
        }
        return new TaskDeadline(localDate);
    }

    /**
     * Parses a {@code String taskDate} into a {@code TaskDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDate} is does not follow the format YYYY-MM-DD.
     */
    public static TaskDate parseTaskDate(String taskDate) throws ParseException {
        requireNonNull(taskDate);
        String trimmedDate = taskDate.trim();
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(trimmedDate);
        } catch (DateTimeParseException e) {
            throw new ParseException("Date must be of the form YYYY-MM-DD");
        }
        return new TaskDate(localDate);
    }

    /**
     * Parses a {@code String taskDone} into a {@code Boolean} representing whether the task is completed.
     *
     * @throws ParseException if the given {@code taskDeadline} is does not follow the format YYYY-MM-DD.
     */
    public static Boolean parseTaskIsDone(String taskDone) throws ParseException {
        requireNonNull(taskDone);
        String trimmedDeadline = taskDone.trim();
        String trimmedLowerCasedDeadline = trimmedDeadline.toLowerCase();
        if (trimmedLowerCasedDeadline.equals("t") || trimmedLowerCasedDeadline.equals("true")) {
            return true;
        } else if (trimmedLowerCasedDeadline.equals("f") || trimmedLowerCasedDeadline.equals("false")) {
            return false;
        } else {
            throw new ParseException("Invalid DONE format");
        }
    }
}
