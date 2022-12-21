package seedu.masslinkers.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_ARGUMENTS;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_MORE_THAN_ONE_INDEX;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_UNEXPECTED_PREFIX;
import static seedu.masslinkers.logic.parser.ModCommandParser.INDEX_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.commons.util.StringUtil;
import seedu.masslinkers.logic.commands.ModCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Mod.ModCategory;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Telegram;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String PREFIX_REGEX = "(?i)[a-z]/.*";

    /**
     * Parses the preamble, {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        checkForPrefix(trimmedIndex);

        // checks if index has extraneous characters
        String[] splitArgs = trimmedIndex.split("\\s+");
        if (splitArgs.length > 1) {
            List<String> illegalChars = Arrays.stream(splitArgs)
                    .filter(x -> !StringUtil.isNonZeroUnsignedInteger(x))
                    .collect(Collectors.toList());
            // if there is more than 2 valid indexes
            if (illegalChars.isEmpty()) {
                throw new ParseException(MESSAGE_MORE_THAN_ONE_INDEX);
            }
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENTS, illegalChars.get(0)));
        }

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
        checkForPrefix(trimmedName);
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException only if the given {@code phone} is empty. Otherwise, it will be accepted.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        String trimmedPhone = phone.trim();
        if (Phone.isEmptyPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String handle} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid.
     */
    public static Telegram parseTelegram(String handle) throws ParseException {
        requireNonNull(handle);
        String trimmedHandle = handle.trim();
        checkForPrefix(trimmedHandle);

        if (!Telegram.isValidTelegram(trimmedHandle)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedHandle);
    }

    /**
     * Parses a {@code String username} into a {@code GitHub}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static GitHub parseGitHub(String username) throws ParseException {
        String trimmedUsername = username.trim();
        checkForPrefix(trimmedUsername);

        if (!GitHub.isValidGitHub(trimmedUsername)) {
            throw new ParseException(GitHub.MESSAGE_CONSTRAINTS);
        }
        return new GitHub(trimmedUsername);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        String trimmedEmail = email.trim();
        checkForPrefix(trimmedEmail);

        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses an {@code String interest} into an {@code interest}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interest} is invalid.
     */
    public static Interest parseInterest(String interest) throws ParseException {
        String trimmedInterest = interest.trim();
        checkForPrefix(trimmedInterest);

        if (!Interest.isValidInterest(trimmedInterest)) {
            throw new ParseException(Interest.MESSAGE_CONSTRAINTS);
        }
        return new Interest(trimmedInterest);
    }

    /**
     * Parses {@code Collection<String> interests} into a {@code Set<Interest>}.
     */
    public static Set<Interest> parseInterests(Collection<String> interests) throws ParseException {
        final Set<Interest> interestSet = new HashSet<>();
        for (String interestName : interests) {
            interestSet.add(parseInterest(interestName));
        }
        return interestSet;
    }

    /**
     * Parses a {@code String mod} into a {@code Mod}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mod} is invalid.
     */
    public static Mod parseMod(String mod) throws ParseException {
        String trimmedMod = mod.trim();
        checkForPrefix(trimmedMod);
        String trimmedUpperCasedMod = trimmedMod.toUpperCase();

        if (!Mod.isValidModName(trimmedUpperCasedMod)) {
            throw new ParseException(Mod.MESSAGE_CONSTRAINTS);
        }
        return new Mod(trimmedUpperCasedMod);
    }

    /**
     * Parses {@code Collection<String> mods} into a {@code ObservableList<Mod>}.
     */
    public static ObservableList<Mod> parseMods(Collection<String> mods) throws ParseException {
        List<String> deDupStringList = new ArrayList<>(new HashSet<>(mods));
        final ObservableList<Mod> modSet = FXCollections.observableArrayList();
        for (String modName : deDupStringList) {
            modSet.add(parseMod(modName));
        }
        return modSet;
    }

    //@@author jonasgwt
    /**
     * Parses {@code String modName} into a {@code ModCategory}
     *
     * @param modName The module name.
     * @return The category that the module name fall under.
     */
    public static ModCategory parseModsToCategory(String modName) {
        assert modName != null;
        String prefix = modName.split("[0-9]")[0];

        // prefix should be at least of length 2 as checked by the VALIDATION_REGEX in Mod.
        assert prefix.length() > 1;

        String modPrefix = prefix.substring(0, 2);
        switch (modPrefix) {
        case "CS":
        case "IS":
        case "CP":
            return ModCategory.COMP;
        case "MA":
        case "ST":
            return ModCategory.MATH;
        case "LS":
        case "CM":
        case "PC":
            return ModCategory.SCI;
        case "ES":
            return ModCategory.COMMS;
        case "GE":
        case "UT":
            return ModCategory.GE;
        default:
            return ModCategory.UE;
        }
    }

    //@@author jonasgwt
    /**
     * Checks for extraneous characters that are prefixes.
     * @param args The string to check.
     * @throws ParseException When an invalid prefix is found.
     */
    private static void checkForPrefix(String args) throws ParseException {
        String[] splitArgs = args.split("\\s+");
        if (splitArgs.length > 1) {
            Set<String> stringsWithInvalidPrefix = Arrays.stream(splitArgs)
                    .filter(x -> x.matches(PREFIX_REGEX)).collect(Collectors.toSet());
            if (!stringsWithInvalidPrefix.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_UNEXPECTED_PREFIX,
                        stringsWithInvalidPrefix.iterator().next().substring(0, 2)));
            }
        }
    }

    //@@author jonasgwt
    /**
     * Extracts out the index of the student specified in the user command.
     *
     * @param args The user command.
     * @return The index of the student in String.
     */
    public static String getIndexFromCommand(String args) throws ParseException {
        String[] splitArgs = args.split("\\s+");
        List<String> validIndexes = Arrays.stream(splitArgs)
                .filter(x -> INDEX_FORMAT.matcher(x.trim()).matches())
                .collect(Collectors.toList());

        // no valid index
        if (validIndexes.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_INDEX_EMPTY);
        }

        // valid index is not after the command word
        if (!validIndexes.get(0).equals(splitArgs[0])) {
            throw new ParseException(String.format(ModCommand.INVALID_ARGUMENTS, splitArgs[0]));
        }

        return validIndexes.iterator().next();
    }
}
