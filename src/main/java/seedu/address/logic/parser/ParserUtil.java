package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Criteria;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.TaskDescription;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */

public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index should be an unsigned integer that is"
            + " greater than 0 and less than 2147483648.";
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
        if (!PriorityTag.isValidTag(trimmedPriorityStatus)) {
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
