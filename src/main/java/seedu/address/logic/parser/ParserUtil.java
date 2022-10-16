package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_OPTIONS = "Command options is invalid";

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
     * Parses two one based index and returns it. Leading and trailing whitespaces will be trimmed.
     *
     * @param oneBasedIndex to be parsed.
     * @return a list of 2 oneBasedIndex.
     * @throws ParseException if indices are invalid.
     */
    public static List<Index> parseTwoIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        String[] indexes = trimmedIndex.split(" ");

        if (indexes.length != 2
                || !StringUtil.isNonZeroUnsignedInteger(indexes[0])
                || !StringUtil.isNonZeroUnsignedInteger(indexes[1])) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        List<Index> indices = new LinkedList<>();
        indices.add(Index.fromOneBased(Integer.parseInt(indexes[0])));
        indices.add(Index.fromOneBased(Integer.parseInt(indexes[1])));

        return indices;
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
     * Parses a {@code String taskDescription} into a {@code Task}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDescription} is invalid.
     */
    public static Task parseTask(String taskDescription) throws ParseException {
        requireNonNull(taskDescription);
        String[] descriptionAndTime = taskDescription.trim().split("\\|");

        if (descriptionAndTime.length == 1) {
            return parseTaskWithoutDateTime(descriptionAndTime[0]);
        }

        return parseTaskWithDateTime(descriptionAndTime[0], descriptionAndTime[1]);
    }

    private static Task parseTaskWithoutDateTime(String taskDescription) throws ParseException {
        String trimmedTaskDescription = taskDescription.trim();

        if (!Task.isValidTaskDescription(trimmedTaskDescription)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }

        return new Task(taskDescription);
    }

    private static Task parseTaskWithDateTime(String taskDescription, String dateTime) throws ParseException {
        String trimmedTaskDescription = taskDescription.trim();
        String trimmedDateTime = dateTime.trim();

        if (!Task.isValidTaskDescription(trimmedTaskDescription) || !DateTime.isValidDateTime(trimmedDateTime)) {
            System.out.println(dateTime);
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }

        return new Task(taskDescription, new DateTime(dateTime));
    }

    /**
     * Parses {@code Collection<String> tasks} into a {@code TaskList}.
     */
    public static TaskList parseTasks(Collection<String> taskDescriptions) throws ParseException {
        requireNonNull(taskDescriptions);
        final ArrayList<Task> tasks = new ArrayList<>();
        for (String taskDescription : taskDescriptions) {
            tasks.add(parseTask(taskDescription));
        }
        return new TaskList(tasks);
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
     * Parses the value of {@code String option} from {@code String arguments}.
     */
    private static Optional<String> parseOption(String arguments, String option) {
        requireNonNull(arguments);
        requireNonNull(option);
        String[] options = arguments.trim().split("\\s+");
        for (int i = 0; i < options.length; i++) {
            if (options[i].contains("/")) {
                break;
            }
            if (i > 0 && options[i - 1].equals(option)) {
                return Optional.of(options[i]);
            }
        }
        return Optional.empty();
    }

    /**
     * Parses the values of {@code Prefix... options} from {@code String arguments}
     * and return them in a {@code ArgumentMultimap}.
     */
    public static ArgumentMultimap parseOptions(String arguments, Prefix... options) {
        requireNonNull(arguments);
        requireNonNull(options);
        ArgumentMultimap optionValues = new ArgumentMultimap();
        for (int i = 0; i < options.length; i++) {
            Optional<String> value = parseOption(arguments, options[i].toString());
            if (value.isPresent()) {
                optionValues.put(options[i], value.get());
            }
        }
        return optionValues;
    }

    /**
     * Erases the options and value of {@code String option} from {@code String arguments}
     * and return the resulting {@code String}.
     */
    private static String eraseOption(String arguments, String option) {
        requireNonNull(arguments);
        requireNonNull(option);
        String[] options = arguments.trim().split("\\s+");
        for (int i = 0; i + 1 < options.length; i++) {
            if (options[i].equals(option)) {
                options[i] = "";
                options[i + 1] = "";
                break;
            }
        }
        return Arrays.stream(options).reduce((x, y) -> x + " " + y).get().trim();
    }

    /**
     * Erases the options and values of {@code Prefix... options} from {@code String arguments}
     * and return the resulting {@code String}.
     */
    public static String eraseOptions(String arguments, Prefix... options) {
        requireNonNull(arguments);
        requireNonNull(options);
        for (int i = 0; i < options.length; i++) {
            arguments = eraseOption(arguments, options[i].toString());
        }
        return arguments;
    }
}
