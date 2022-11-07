package seedu.hrpro.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.commons.util.StringUtil;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.StaffContact;
import seedu.hrpro.model.staff.StaffDepartment;
import seedu.hrpro.model.staff.StaffLeave;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffTitle;
import seedu.hrpro.model.tag.Tag;
import seedu.hrpro.model.task.TaskDescription;

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
     * Parses a {@code String name} into a {@code ProjectName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ProjectName parseProjectName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedProjectName = name.trim();
        if (!ProjectName.isValidProjectName(trimmedProjectName)) {
            throw new ParseException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectName(trimmedProjectName);
    }

    /**
     * Parses a {@code String budget} into a {@code Budget}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code budget} is invalid.
     */
    public static Budget parseBudget(String budget) throws ParseException {
        requireNonNull(budget);
        String trimmedBudget = budget.trim();
        if (!Budget.isValidBudget(trimmedBudget)) {
            throw new ParseException(Budget.MESSAGE_CONSTRAINTS);
        }
        return new Budget(trimmedBudget);
    }

    /**
     * Parses a {@code String deadline} into an {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
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
     * Parses {@code String staffContact} into a {@code StaffContact}.
     *
     * @throws ParseException if the given {@code staffContact} is invalid.
     */
    public static StaffContact parseStaffContact(String staffContact) throws ParseException {
        requireNonNull(staffContact);
        String trimmedContact = staffContact.trim();
        if (!StaffContact.isValidStaffContact(trimmedContact)) {
            throw new ParseException(StaffContact.MESSAGE_CONSTRAINTS);
        }
        return new StaffContact(trimmedContact);
    }

    /**
     * Parses {@code String staffDepartment} into a {@code StaffDepartment}.
     *
     * @throws ParseException if the given {@code staffDepartment} is invalid.
     */
    public static StaffDepartment parseStaffDepartment(String staffDepartment) throws ParseException {
        requireNonNull(staffDepartment);
        String trimmedDepartment = staffDepartment.trim();
        if (!StaffDepartment.isValidStaffDepartment(staffDepartment)) {
            throw new ParseException(StaffDepartment.MESSAGE_CONSTRAINTS);
        }
        return new StaffDepartment(trimmedDepartment);
    }

    /**
     * Parses {@code String staffLeave} into a {@code StaffLeave}.
     *
     * @throws ParseException if the given {@code staffLeave} is invalid.
     */
    public static StaffLeave parseStaffLeave(String staffLeave) throws ParseException {
        requireNonNull(staffLeave);
        String trimmedLeave = staffLeave.trim();
        if (!StaffLeave.isValidStaffLeave(staffLeave)) {
            throw new ParseException(StaffLeave.MESSAGE_CONSTRAINTS);
        }
        return new StaffLeave(trimmedLeave);
    }

    /**
     * Parses {@code String staffName} into a {@code StaffName}.
     *
     * @throws ParseException if the given {@code staffName} is invalid.
     */
    public static StaffName parseStaffName(String staffName) throws ParseException {
        requireNonNull(staffName);
        String trimmedName = staffName.trim();
        if (!StaffName.isValidStaffName(trimmedName)) {
            throw new ParseException(StaffName.MESSAGE_CONSTRAINTS);
        }
        return new StaffName(trimmedName);
    }

    /**
     * Parses {@code String staffTitle} into a {@code StaffTitle}.
     *
     * @throws ParseException if the given {@code staffTitle} is invalid.
     */
    public static StaffTitle parseStaffTitle(String staffTitle) throws ParseException {
        requireNonNull(staffTitle);
        String trimmedTitle = staffTitle.trim();
        if (!StaffTitle.isValidStaffTitle(trimmedTitle)) {
            throw new ParseException(StaffTitle.MESSAGE_CONSTRAINTS);
        }
        return new StaffTitle(trimmedTitle);
    }

    /**
     * Parses {@code String taskDeadline} into a {@code TaskDeadline}
     *
     * @throws ParseException if the given {@code taskDeadline} is invalid.
     */
    public static Deadline parseTaskDeadline(String taskDeadline) throws ParseException {
        requireNonNull(taskDeadline);
        String trimmedDeadline = taskDeadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses {@code String description} into a {@code TaskDescription}
     *
     * @throws ParseException if the given {@code taskDescription} is invalid.
     */
    public static TaskDescription parseTaskDescription(String taskDescription) throws ParseException {
        requireNonNull(taskDescription);
        String trimmedDescription = taskDescription.trim();
        if (!TaskDescription.isValidTaskDescription(trimmedDescription)) {
            throw new ParseException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        return new TaskDescription(trimmedDescription);
    }
}
