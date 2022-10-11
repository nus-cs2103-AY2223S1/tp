package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Budget;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectName;

import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffInsurance;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
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
     * Parses {@code String contact} into a {@code StaffContact}.
     */
    public static StaffContact parseStaffContact(String contact) throws ParseException {
        requireNonNull(contact);
        String trimmedContact = contact.trim();
        if (!StaffContact.isValidStaffContact(trimmedContact)) {
            throw new ParseException(StaffContact.MESSAGE_CONSTRAINTS);
        }
        return new StaffContact(trimmedContact);
    }

    /**
     * Parses {@code String department} into a {@code StaffDepartment}.
     */
    public static StaffDepartment parseStaffDepartment(String department) throws ParseException {
        requireNonNull(department);
        String trimmedDepartment = department.trim();
        if (!StaffDepartment.isValidStaffDepartment(department)) {
            throw new ParseException(StaffDepartment.MESSAGE_CONSTRAINTS);
        }
        return new StaffDepartment(trimmedDepartment);
    }

    /**
     * Parses {@code String insurance} into a {@code StaffInsurance}.
     */
    public static StaffInsurance parseStaffInsurance(String insurance) throws ParseException {
        requireNonNull(insurance);
        String trimmedInsurance = insurance.trim();
        if (!StaffInsurance.isValidStaffInsurance(insurance)) {
            throw new ParseException(StaffInsurance.MESSAGE_CONSTRAINTS);
        }
        return new StaffInsurance(trimmedInsurance);
    }

    /**
     * Parses {@code String name} into a {@code StaffName}.
     */
    public static StaffName parseStaffName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!StaffName.isValidStaffName(trimmedName)) {
            throw new ParseException(StaffName.MESSAGE_CONSTRAINTS);
        }
        return new StaffName(trimmedName);
    }

    /**
     * Parses {@code String title} into a {@code StaffTitle}.
     */
    public static StaffTitle parseStaffTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!StaffTitle.isValidStaffTitle(title)) {
            throw new ParseException(StaffTitle.MESSAGE_CONSTRAINTS);
        }
        return new StaffTitle(trimmedTitle);
    }
}
