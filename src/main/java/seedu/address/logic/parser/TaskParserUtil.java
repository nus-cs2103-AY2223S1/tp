package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Project;
import seedu.address.model.task.Title;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes related to tasks.
 */
public class TaskParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
    */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_PARSE_FAILURE);
        }

        if (trimmedDeadline.equals("?") || trimmedDeadline.equals(Deadline.UNSPECIFIED_DEADLINE_IDENTIFIER)) {
            return Deadline.UNSPECIFIED;
        }

        List<Date> parseResult = new PrettyTimeParser().parse(trimmedDeadline);

        if (parseResult.isEmpty()) {
            throw new ParseException(Deadline.MESSAGE_PARSE_FAILURE);
        }
        return Deadline.of(TaskParserUtil.convertToLocalDate(parseResult.get(0)));
    }

    /**
     * Parses a {@code String project name} into a {@code Project}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code project} is invalid.
     */
    public static Project parseProject(String projectName) throws ParseException {
        requireNonNull(projectName);
        String trimmedProjectName = projectName.trim();
        if (!Project.isValidProjectName(trimmedProjectName)) {
            throw new ParseException(Project.MESSAGE_CONSTRAINTS);
        }
        return new Project(trimmedProjectName);
    }

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
     * Parses {@code Collection<String> oneBasedIndexes} into a {@code Set<Index>}.
     */
    public static Set<Index> parseIndexes(Collection<String> oneBasedIndexes) throws ParseException {
        requireNonNull(oneBasedIndexes);
        final Set<Index> indexSet = new HashSet<>();
        for (String oneBasedIndex : oneBasedIndexes) {
            String trimmedIndex = oneBasedIndex.trim();
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indexSet.add(Index.fromOneBased(Integer.parseInt(trimmedIndex)));
        }
        return indexSet;
    }

    /**
     * Parses {@code Collection<String> contacts} into a {@code Set<Contact>}.
     */
    public static Set<Contact> parseContacts(Collection<String> contacts) {
        requireNonNull(contacts);
        final Set<Contact> contactSet = new HashSet<>();
        for (String contactName : contacts) {
            contactSet.add(new Contact(contactName));
        }
        return contactSet;
    }

    /**
     * Parses index inputs in {@code Collection<String> inputs} (that can contain index and non-index inputs)
     * into a {@code Set<Index>}.
     */
    public static Set<Index> parseIndexesMixed(Collection<String> inputs) throws ParseException {
        requireNonNull(inputs);
        final Set<Index> indexSet = new HashSet<>();
        for (String input : inputs) {
            String trimmedInput = input.trim();
            if (StringUtil.isInteger(trimmedInput)) {
                if (!StringUtil.isNonZeroUnsignedInteger(trimmedInput)) {
                    throw new ParseException(MESSAGE_INVALID_INDEX);
                }
                indexSet.add(Index.fromOneBased(Integer.parseInt(trimmedInput)));
            }
        }
        return indexSet;
    }

    /**
     * Parses non-index inputs in {@code Collection<String> inputs} (that can contain index and non index inputs)
     * into a {@code Set<String>}.
     */
    public static Set<String> parseTextsMixed(Collection<String> inputs) {
        requireNonNull(inputs);
        final Set<String> textSet = new HashSet<>();
        for (String input : inputs) {
            String trimmedInput = input.trim();
            if (!StringUtil.isInteger(trimmedInput)) {
                textSet.add(trimmedInput);
            }
        }
        return textSet;
    }

    /**
     * Convert given Date object to LocalDate object.
     */
    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Parses string to LocalDate object. Used for initialising TypicalTasks so the string given is always in
     * correct format.
     */
    public static LocalDate convertStringToLocalDate(String date) {
        if (date.trim().equals("?") || date.trim().equals(Deadline.UNSPECIFIED_DEADLINE_IDENTIFIER)) {
            return Deadline.UNSPECIFIED.getDate();
        }
        List<Date> result = new PrettyTimeParser().parse(date);
        return TaskParserUtil.convertToLocalDate(result.get(0));
    }

}
