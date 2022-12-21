package seedu.masslinkers.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INCOMPLETE_COMMAND_FORMAT;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.ModAddCommand;
import seedu.masslinkers.logic.commands.ModCommand;
import seedu.masslinkers.logic.commands.ModDeleteCommand;
import seedu.masslinkers.logic.commands.ModFindCommand;
import seedu.masslinkers.logic.commands.ModMarkAllCommand;
import seedu.masslinkers.logic.commands.ModMarkCommand;
import seedu.masslinkers.logic.commands.ModUnmarkCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.ModContainsKeywordsPredicate;
import seedu.masslinkers.model.student.ModTakenContainsKeywordsPredicate;
import seedu.masslinkers.model.student.ModTakingContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ModCommand object.
 */
public class ModCommandParser implements Parser<ModCommand> {

    public static final Pattern INDEX_FORMAT = Pattern.compile("-?\\d+");
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String MOD_TAKEN_COMMAND_WORD = "taken";
    private static final String MOD_TAKING_COMMAND_WORD = "taking";

    /**
     * Parses the given {@code userInput} of arguments in the context of the ModCommand
     * and returns a ModCommand object for execution.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ModCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmedArgs);

        // check if empty and matches format
        if (trimmedArgs.isEmpty() || !matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INCOMPLETE_COMMAND_FORMAT, ModCommand.MESSAGE_USAGE));
        }

        // parse
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ModAddCommand.COMMAND_WORD:
            return parseAddCommand(arguments);
        case ModDeleteCommand.COMMAND_WORD:
            return parseDeleteCommand(arguments);
        case ModMarkCommand.COMMAND_WORD:
            return parseMarkCommand(arguments);
        case ModUnmarkCommand.COMMAND_WORD:
            return parseUnmarkCommand(arguments);
        case ModFindCommand.COMMAND_WORD:
            return parseFindCommand(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModCommand.MESSAGE_USAGE));
        }
    }

    //@@author jonasgwt
    /**
     * Parses a mod add command from user to construct a ModAddCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModAddCommand for execution.
     * @throws ParseException If there is a parse error.
     */
    private ModAddCommand parseAddCommand(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // checks for common user mistakes
        checkForUserMistakes(trimmedArgs);

        return new ModAddCommand(getIndex(trimmedArgs), getMods(trimmedArgs));
    }

    //@@author ElijahS67
    /**
     * Parses a mod delete command from user to construct a ModDeleteCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModDeleteCommand for execution.
     * @throws ParseException If there is a parse error.
     */
    private ModDeleteCommand parseDeleteCommand(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // checks for common user mistakes
        checkForUserMistakes(trimmedArgs);

        return new ModDeleteCommand(getIndex(trimmedArgs), getMods(trimmedArgs));
    }

    //@@author carriezhengjr
    /**
     * Parses a mod mark command from user to construct a ModMarkAllCommand or ModMarkCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModCommand for execution, depending on if it is ModMarkAllCommand or ModMarkCommand.
     * @throws ParseException If there is a parse error.
     */
    private ModCommand parseMarkCommand(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // checks for common user mistakes
        checkForUserMistakes(trimmedArgs);

        // checks if it is a mod mark all command
        String indexOrAll = getModMarkIndexOrAll(trimmedArgs);
        if (indexOrAll.equals("all")) {
            return new ModMarkAllCommand();
        }

        return new ModMarkCommand(getIndex(trimmedArgs), getMods(trimmedArgs));
    }

    //@@author carriezhengjr
    /**
     * Parses a mod unmark command from user to construct a ModUnmarkCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModUnmarkCommand for execution.
     * @throws ParseException If there is a parse error.
     */
    private ModUnmarkCommand parseUnmarkCommand(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // checks for common user mistakes
        checkForUserMistakes(trimmedArgs);

        return new ModUnmarkCommand(getIndex(trimmedArgs), getMods(trimmedArgs));
    }

    //@@author chm252
    /**
     * Parses a mod find command from user to construct a ModFindCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModFindCommand for execution.
     * @throws ParseException If there is a parse error.
     */
    private ModFindCommand parseFindCommand(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }

        String[] keywords = trimmedArgs.split("\\s+");
        boolean isTaken = keywords[0].equalsIgnoreCase(MOD_TAKEN_COMMAND_WORD);
        boolean isTaking = keywords[0].equalsIgnoreCase(MOD_TAKING_COMMAND_WORD);
        String[] keywordsWithoutFirstElement = Arrays.copyOfRange(keywords, 1, keywords.length);
        if ((isTaken || isTaking) && keywordsWithoutFirstElement.length == 0) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }

        if (isTaken) {
            return new ModFindCommand(
                    new ModTakenContainsKeywordsPredicate(Arrays.asList(keywordsWithoutFirstElement)));
        } else if (isTaking) {
            return new ModFindCommand(
                    new ModTakingContainsKeywordsPredicate(Arrays.asList(keywordsWithoutFirstElement)));
        } else {
            return new ModFindCommand(new ModContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }

    // ------------------------------------- Helper Methods -----------------------------------------------------------

    //@@author jonasgwt
    /**
     * Converts a collection of strings representing mod names to a set of mods.
     *
     * @param mods A collection of mods in string.
     * @return A set of mods.
     * @throws ParseException When mod names are of incorrect format.
     */
    private Optional<ObservableList<Mod>> parseMods(Collection<String> mods) throws ParseException {
        assert mods != null;

        if (mods.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> modSet = mods.size() == 1 && mods.contains("") ? Collections.emptySet() : mods;
        return Optional.of(ParserUtil.parseMods(modSet));
    }

    //@@author carriezhengjr
    /**
     * Extracts out the word after mod mark, which could be the index of the student or "all".
     *
     * @param args The user command.
     * @return The index of the student in String or the word "all".
     */
    private String getModMarkIndexOrAll(String args) throws ParseException {
        String[] splittedArgs = args.split("\\s+");
        String indexOrAll = splittedArgs[0].trim();
        boolean isAll = indexOrAll.equals("all");

        if (isAll) {
            // Checks if the word is "all". If yes, the word "all" will be returned.
        } else {
            // Checks if the word is an index. If yes, the index will be returned. Otherwise, throw an error.
            final Matcher matcher = INDEX_FORMAT.matcher(indexOrAll);
            if (!matcher.matches()) {
                throw new ParseException(ModCommand.MESSAGE_INDEX_EMPTY);
            }
        }
        return indexOrAll;
    }

    //@@author jonasgwt
    /**
     * Extracts out the mods specified in the user command.
     *
     * @param args The user command.
     * @return A set of mods of type String.
     */
    private Set<String> getModsFromCommand(String args) {
        String[] splittedArgs = args.split("\\s+");
        // it is guaranteed that a valid index would be in the first element of the array as
        // ParserUtil.getIndexFromCommand would have thrown an exception otherwise.
        assert INDEX_FORMAT.matcher(splittedArgs[0]).matches();
        List<String> extractedMods = Arrays.asList(splittedArgs).subList(1, splittedArgs.length);
        return new HashSet<>(extractedMods);
    }

    //@@author jonasgwt
    /**
     * Checks for common user mistakes.
     * 1. Adding words between command word and index.
     *
     * @param args The user input to check.
     * @throws ParseException When user input is found to have made one of the mistakes listed.
     */
    private void checkForUserMistakes(String args) throws ParseException {
        String[] splittedArgs = args.split("\\s+");
        List<String> validIndexes = Arrays.stream(splittedArgs)
                .filter(x -> INDEX_FORMAT.matcher(x.trim()).matches() || x.equals("all"))
                .collect(Collectors.toList());

        // valid index is not after the command word
        if (!validIndexes.isEmpty() && !validIndexes.get(0).equals(splittedArgs[0])) {
            throw new ParseException(String.format(ModCommand.INVALID_ARGUMENTS, splittedArgs[0]));
        }
    }

    /**
     * Returns the index of the student from user input.
     *
     * @param trimmedArgs The user input.
     * @return The index from the command.
     * @throws ParseException If there is an invalid user input.
     */
    private Index getIndex(String trimmedArgs) throws ParseException {
        Index index;

        String indexFromCommand = ParserUtil.getIndexFromCommand(trimmedArgs);

        // attempts to parse the index
        try {
            index = ParserUtil.parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        return index;
    }

    /**
     * Returns an ObservableList of mods from user input.
     *
     * @param trimmedArgs The user input.
     * @return An ObservableList of Mods
     * @throws ParseException If there is no mods in user input or input invalid.
     */
    private ObservableList<Mod> getMods(String trimmedArgs) throws ParseException {
        Set<String> modsFromCommand = getModsFromCommand(trimmedArgs);
        Optional<ObservableList<Mod>> mods = parseMods(modsFromCommand);
        if (mods.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }
        return mods.get();
    }
}
