package nus.climods.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import nus.climods.commons.core.index.Index;
import nus.climods.commons.util.StringUtil;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.module.ModuleCode;
import nus.climods.model.person.Address;
import nus.climods.model.person.Email;
import nus.climods.model.person.Name;
import nus.climods.model.person.Phone;
import nus.climods.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    // Validates user entered module codes at parsing stage.
    public static final Pattern MODULE_CODE_PATTERN = Pattern.compile("[A-Z]{0,3}\\d{4}");
    // Validates user flag, case insensitive
    public static final Pattern USER_FLAG_PATTERN = Pattern.compile("(?i)--user(?-i)");
    // Validates faculty code, case insensitive and matches between 2-3 chars
    public static final Pattern FACULTY_CODE_PATTERN = Pattern.compile("^[A-Za-z]{2,3}$");
    // TODO: Change this to a less hacky solution
    public static final String FAULTY_FACULTY_CODE = "zzz";
    /**
     * Returns List of whitespace-delimited arguments given arguments string supplied by the user
     * @param arguments String supplied by user as arguments after preamble
     * @return List of arguments provided
     */
    public static List<String> convertArgumentStringToList(String arguments) {
        List<String> res = ArgumentTokenizer
            .tokenize(arguments.trim(), new Prefix(""))
            .getAllValues(new Prefix(""));

        // When arguments is empty string once trimmed, res is a List with just one empty string
        // Return empty list to demarcate this case clearly
        if (res.size() == 1 && res.get(0).trim().equals("")) {
            return List.of();
        }

        return res;
    }

    /**
     * Returns String array of whitespace-delimited arguments given arguments string supplied by the user. This function
     * makes it more convenient to work with commons.cli which accepts a String array of arguments to parse
     *
     * @param arguments String supplied by user as arguments after preamble
     * @return String array of arguments provided
     */
    public static String[] convertArgumentStringToArray(String arguments) {
        return convertArgumentStringToList(arguments).toArray(new String[] {});
    }

    /**
     * Parses module code according to user input
     *
     * @param input Input string from user representing a module code
     * @return Empty Optional if input string does not pass initial validation check, else Optional of the entered
     *               String
     */
    public static Optional<String> parseModuleCode(String input) {
        if (!MODULE_CODE_PATTERN.matcher(input.trim()).find()) {
            return Optional.empty();
        }

        return Optional.of(input);
    }

    /**
     * Parse user flag according to user input
     * @param input Input string from user containing --user. Case insensitive
     * @return Empty optional if input string does not contain --user, else Optional of --user flag
     */
    public static Optional<Boolean> parseUserFlag(String input) {
        if (!USER_FLAG_PATTERN.matcher(input.trim()).find()) {
            return Optional.empty();
        }
        return Optional.of(Boolean.TRUE);
    }

    /**
     * Parse faculty code according to user input
     * @param input Input string from user containing faculty code
     * @return
     */
    public static Optional<String> parseFacultyCode(String input) {
        if (input.length() == 0) {
            return Optional.empty();
        }
        input = !FACULTY_CODE_PATTERN.matcher(input.trim()).find() ? FAULTY_FACULTY_CODE : input;
        return Optional.of(input);
    }
    // TODO: Remove addressbook code
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
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be trimmed.
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
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ModuleCode parseCode(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}. Leading and trailing whitespaces will be trimmed.
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
     * Parses a {@code String address} into an {@code Address}. Leading and trailing whitespaces will be trimmed.
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
     * Parses a {@code String email} into an {@code Email}. Leading and trailing whitespaces will be trimmed.
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
     * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be trimmed.
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
}
