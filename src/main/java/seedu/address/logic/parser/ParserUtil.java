package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Criteria;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */

public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index should be an unsigned integer that is"
            + "greater than 0 and less than 2147483647.";
    public static final String MESSAGE_INVALID_KEYWORDS = "The keywords for tagdel must be priority"
            + " or deadline or both.";
    public static final String MESSAGE_INVALID_NUMBER_OF_KEYWORDS = "The number of keywords used for tag"
            + "del should be either 1 or 2";

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
     * Parses the {@code moduleCode} String into a {@code ModuleCode} object.
     *
     * @param moduleCode The module code of the module.
     * @return The ModuleCode object created from the moduleCode string.
     * @throws ParseException if the given {@code moduleCode} is not valid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.strip();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses the {@code moduleName} String into a {@code ModuleName} object.
     *
     * @param moduleName The name of the module.
     * @return The ModuleName object created from the moduleName string.
     * @throws ParseException if the given {@code moduleName} is not valid.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedModuleName = moduleName.strip();
        if (!ModuleName.isValidModuleName(trimmedModuleName)) {
            throw new ParseException(ModuleName.MODULE_NAME_CONSTRAINTS);
        }
        return new ModuleName(trimmedModuleName);
    }

    /**
     * Parses the {@code moduleCredit} String into a {@code ModuleCredit} object.
     *
     * @param moduleCredit The module credit of the module.
     * @return The ModuleCredit object created from the moduleCredit string.
     * @throws ParseException if the given {@code moduleCredit} is not valid.
     */
    public static ModuleCredit parseModuleCredit(String moduleCredit) throws ParseException {
        requireNonNull(moduleCredit);
        final int integerModuleCredit;
        String trimmedModuleCredit = moduleCredit.strip();
        try {
            integerModuleCredit = Integer.parseInt(trimmedModuleCredit);
        } catch (NumberFormatException nfe) {
            throw new ParseException(ModuleCredit.MODULE_CREDIT_CONSTRAINTS);
        }
        if (!ModuleCredit.isValidModuleCredit(integerModuleCredit)) {
            throw new ParseException(ModuleCredit.MODULE_CREDIT_CONSTRAINTS);
        }
        return new ModuleCredit(integerModuleCredit);
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
     * Parses delete tag keywords from a String into a Set containing each keyword.
     *
     * @param keywords The keywords used to specify which tag to delete
     * @return The set of string containing the keywords specifying the tags to delete.
     * @throws ParseException if the keywords given are invalid or are duplicated.
     */
    public static Set<String> parseDeleteTagKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.strip();
        String[] keywordsList = trimmedKeywords.split("\\s+");
        final Set<String> keywordSet = new HashSet<>();
        if (keywordsList.length > 2 || keywordsList.length < 1) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_KEYWORDS);
        }
        for (String keyword : keywordsList) {
            if (!(keyword.equalsIgnoreCase("priority")
                    || keyword.equalsIgnoreCase("deadline"))) {
                throw new ParseException(MESSAGE_INVALID_KEYWORDS);
            }
            keywordSet.add(keyword.toLowerCase());
        }
        return keywordSet;
    }

    /**
     * Parses the priority status into a PriorityTag.
     *
     * @param priorityTag The priority status added to the tag.
     * @return The priorityTag object containing the priority status.
     * @throws ParseException if the priority status is not valid.
     */
    public static PriorityTag parsePriorityTag(String priorityTag) throws ParseException {
        requireNonNull(priorityTag);
        String trimmedPriorityStatus = priorityTag.strip();
        if (!PriorityTag.isValidTag(priorityTag)) {
            throw new ParseException(PriorityTag.PRIORITY_TAG_CONSTRAINTS);
        }
        return new PriorityTag(trimmedPriorityStatus);
    }

    /**
     * Parses the deadline into a DeadlineTag.
     *
     * @param deadline The deadline which is added to the DeadlineTag.
     * @return The deadlineTag containing the deadline status.
     * @throws ParseException if the deadline is in an invalid format.
     */
    public static DeadlineTag parseDeadlineTag(String deadline) throws ParseException {
        requireNonNull(deadline);
        final LocalDate date;
        if (!DeadlineTag.checkDateFormat(deadline)) {
            throw new ParseException(DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS);
        }
        //@@author dlimyy-reused
        //Reused from https://stackoverflow.com/questions/32823368/
        //with minor modifications.
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        //@@author
        try {
            date = LocalDate.parse(deadline, dtf);
        } catch (DateTimeParseException dtp) {
            throw new ParseException(DeadlineTag.DEADLINE_TAG_INVALID_DATE);
        }
        if (!DeadlineTag.isValidDeadline(date)) {
            throw new ParseException(DeadlineTag.DEADLINE_TAG_DATE_HAS_PASSED);
        }
        return new DeadlineTag(date);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static TaskDescription parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!TaskDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(TaskDescription.DESCRIPTION_CONSTRAINTS);
        }
        return new TaskDescription(trimmedDescription);
    }

    /**
     * Parses the criteria given to create a new Criteria object.
     *
     * @param criteria The criteria given for sorting.
     * @return The criteria object which contains the criteria used for sorting.
     * @throws ParseException if the given {@code criteria} is invalid.
     */
    public static Criteria parseCriteria(String criteria) throws ParseException {
        requireNonNull(criteria);
        String strippedCriteria = criteria.strip();
        if (!Criteria.isValidCriteria(strippedCriteria)) {
            throw new ParseException(Criteria.CRITERIA_CONSTRAINTS);
        }
        return new Criteria(strippedCriteria);
    }

    /**
     * Parses a {@code String status} into a {@code TaskStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static TaskStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!TaskStatus.isValidStatus(trimmedStatus)) {
            throw new ParseException(TaskStatus.STATUS_CONSTRAINTS);
        }
        return TaskStatus.of(trimmedStatus);
    }

    /**
     * Parses a {@code String description} into a {@code ExamDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static ExamDescription parseExamDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!ExamDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(ExamDescription.DESCRIPTION_CONSTRAINTS);
        }
        return new ExamDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String examDate} into a {@code ExamDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code examDate} is invalid.
     */
    public static ExamDate parseExamDate(String examDate) throws ParseException {
        requireNonNull(examDate);
        String trimmedDate = examDate.trim();

        if (!ExamDate.isCorrectDateFormat(trimmedDate)) {
            throw new ParseException(ExamDate.DATE_FORMAT_CONSTRAINTS);
        }
        if (!ExamDate.isExistingDate(trimmedDate)) {
            throw new ParseException(ExamDate.VALID_DATE_CONSTRAINTS);
        }
        if (!ExamDate.isNotAPastDate(trimmedDate)) {
            throw new ParseException(ExamDate.NOT_A_PAST_DATE_CONSTRAINTS);
        }
        return new ExamDate(trimmedDate);
    }

}
