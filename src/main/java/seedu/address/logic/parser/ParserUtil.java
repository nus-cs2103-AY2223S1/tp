package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_NUMBER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_NUMBERS_TO_SWAP;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.link.Link;
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
     * Parses {@code Collection<String> links} into a {@code Set<Link>}.
     */
    public static Set<Link> parseLinks(
            Collection<String> linkAliases, Collection<String> linkUrls) throws ParseException {
        requireAllNonNull(linkAliases, linkUrls);

        final Set<Link> linkSet = new HashSet<>();
        final List<String> listLinkAliases = new ArrayList<>(linkAliases);
        final List<String> listLinkUrls = new ArrayList<>(linkUrls);
        //check that the number of alias and urls match
        validateAddLinkCommandPairSize(listLinkAliases, listLinkUrls);
        if (!isUniqueList(listLinkAliases)) {
            throw new ParseException(AddLinkCommandParser.MESSAGE_DUPLICATE_ALIAS);
        }
        if (!isUniqueList(listLinkUrls)) {
            throw new ParseException(AddLinkCommandParser.MESSAGE_DUPLICATE_URL);
        }

        for (int i = 0; i < linkAliases.size(); i++) {
            String parsedLinkAlias = parseLinkAlias(listLinkAliases.get(i));
            String parsedLinkUrl = parseLinkUrl(listLinkUrls.get(i));
            linkSet.add(new Link(parsedLinkAlias, parsedLinkUrl));
        }
        return linkSet;
    }

    //Ensures that the number of link aliases match the number of link URLs
    static void validateAddLinkCommandPairSize(
            List<String> linkAliases, List<String> linkUrls) throws ParseException {
        boolean isLinkFieldsSameSize = linkAliases.size() == linkUrls.size();
        if (!isLinkFieldsSameSize) {
            throw new ParseException(AddLinkCommandParser.MESSAGE_CONSTRAINTS);
        }
    }

    // Checks for no duplicates in a generic list
    static boolean isUniqueList(List<String> inputList) {
        return inputList.stream().allMatch(new HashSet<>()::add);
    }

    /**
     * Parses a {@code String linkUrl}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code linkUrl} is invalid.
     */
    public static String parseLinkUrl(String linkUrl) throws ParseException {
        requireNonNull(linkUrl);
        String trimmedLinkUrl = linkUrl.trim();
        if (!Link.isValidLinkUrl(trimmedLinkUrl)) {
            throw new ParseException(Link.MESSAGE_CONSTRAINTS_URL);
        }
        return trimmedLinkUrl;
    }

    /**
     * Parses a {@code String linkAlias}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code linkAlias} is invalid.
     */
    public static String parseLinkAlias(String linkAlias) throws ParseException {
        requireNonNull(linkAlias);
        String trimmedLinkAlias = linkAlias.trim();
        if (!Link.isValidLinkAlias(trimmedLinkAlias)) {
            throw new ParseException(Link.MESSAGE_CONSTRAINTS_ALIAS);
        }
        return trimmedLinkAlias;
    }

    /**
     * Set is used to deal with duplicate aliases. (Order is not maintained)
     * Parses {@code Collection<String> linkAliases}, returns {@code List<String>} of validated linkAliases.
     */
    public static List<String> parseLinkAliases(Collection<String> linkAliases) throws ParseException {
        requireNonNull(linkAliases);
        final List<String> aliasSet = new ArrayList<>();
        for (String linkAlias : linkAliases) {
            aliasSet.add(parseLinkAlias(linkAlias));
        }
        return aliasSet;
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
            throw new ParseException(MESSAGE_INVALID_TASK_NUMBER);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String} with two task numbers into a {@code List} with
     * two {@code Index}es.
     * @throws ParseException if the given numbers are invalid.
     */
    public static List<Index> parseTaskNumbersToSwap(String pairOfOneBasedTaskNumbers)
            throws ParseException {
        requireNonNull(pairOfOneBasedTaskNumbers);
        String trimmedNumbers = pairOfOneBasedTaskNumbers.trim();
        String[] arrayOfTaskNumbers = trimmedNumbers.split(" ");
        Boolean hasOnlyTwoTaskNumbers = arrayOfTaskNumbers.length == 2;
        if (!hasOnlyTwoTaskNumbers) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP);
        }
        String firstTaskNumberAsString = arrayOfTaskNumbers[0];
        String secondTaskNumberAsString = arrayOfTaskNumbers[1];
        Boolean isFirstTaskNumberInvalid =
                !StringUtil.isNonZeroUnsignedInteger(firstTaskNumberAsString);
        Boolean isSecondTaskNumberInvalid =
                !StringUtil.isNonZeroUnsignedInteger(secondTaskNumberAsString);
        if (isFirstTaskNumberInvalid || isSecondTaskNumberInvalid) {
            throw new ParseException(MESSAGE_INVALID_TASK_NUMBER);
        }
        Boolean areTaskNumbersSame =
                firstTaskNumberAsString.equals(secondTaskNumberAsString);
        if (areTaskNumbersSame) {
            throw new ParseException(MESSAGE_INVALID_TASK_NUMBERS_TO_SWAP);
        }
        return Arrays.asList(
                Index.fromOneBased(Integer.parseInt(firstTaskNumberAsString)),
                Index.fromOneBased(Integer.parseInt(secondTaskNumberAsString)));
    }
}
