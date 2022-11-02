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

//@@author jonasgwt
/**
 * Parses input arguments and creates a new ModCommand object.
 */
public class ModCommandParser implements Parser<ModCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern INDEX_FORMAT = Pattern.compile("-?\\d+");
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

        // TODO: Add cases for other mod commands
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

    /**
     * Parses a mod add command from user to construct a ModAddCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModAddCommand for execution.
     * @throws ParseException If there is a parse error.
     */
    private ModAddCommand parseAddCommand(String args) throws ParseException {
        Index index;
        String trimmedArgs = args.trim();
        String indexFromCommand = getIndexFromCommand(trimmedArgs);
        Set<String> modsFromCommand = getModsFromCommand(trimmedArgs);
        Optional<ObservableList<Mod>> mods = parseMods(modsFromCommand);
        if (mods.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }

        try {
            index = ParserUtil.parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX,
                    ModAddCommand.MESSAGE_USAGE), pe);
        }
        return new ModAddCommand(index, mods.get());
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
        Index index;
        String trimmedArgs = args.trim();
        String indexFromCommand = getIndexFromCommand(trimmedArgs);
        Set<String> modsFromCommand = getModsFromCommand(trimmedArgs);
        Optional<ObservableList<Mod>> mods = parseMods(modsFromCommand);

        if (mods.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }

        try {
            index = ParserUtil.parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX,
                    ModAddCommand.MESSAGE_USAGE), pe);
        }
        return new ModDeleteCommand(index, mods.get());
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
        Index index;
        String trimmedArgs = args.trim();
        String indexOrAll = getModMarkIndexOrAll(trimmedArgs);
        Set<String> modsFromCommand = getModsFromCommand(trimmedArgs);
        Optional<ObservableList<Mod>> mods = parseMods(modsFromCommand);

        if (indexOrAll.equals("all")) {
            return new ModMarkAllCommand();

        } else if (mods.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);

        } else {
            try {
                String indexFromCommand = getIndexFromCommand(trimmedArgs);
                index = ParserUtil.parseIndex(indexFromCommand);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX,
                        ModAddCommand.MESSAGE_USAGE), pe);
            }
            return new ModMarkCommand(index, mods.get());
        }

    }

    /**
     * Parses a mod unmark command from user to construct a ModUnmarkCommand for execution.
     *
     * @param args Arguments from user input.
     * @return A ModUnmarkCommand for execution.
     * @throws ParseException If there is a parse error.
     */
    private ModUnmarkCommand parseUnmarkCommand(String args) throws ParseException {
        Index index;
        String trimmedArgs = args.trim();
        String indexFromCommand = getIndexFromCommand(trimmedArgs);
        Set<String> modsFromCommand = getModsFromCommand(trimmedArgs);
        Optional<ObservableList<Mod>> mods = parseMods(modsFromCommand);
        if (mods.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }

        try {
            index = ParserUtil.parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX,
                    ModAddCommand.MESSAGE_USAGE), pe);
        }
        return new ModUnmarkCommand(index, mods.get());
    }

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

    /**
     * Extracts out the index of the student specified in the user command.
     *
     * @param args The user command.
     * @return The index of the student in String.
     */
    private String getIndexFromCommand(String args) throws ParseException {
        String[] splittedArgs = args.split("\\s+");
        String index = splittedArgs[0];
        final Matcher matcher = INDEX_FORMAT.matcher(index.trim());
        if (!matcher.matches()) {
            throw new ParseException(ModCommand.MESSAGE_INDEX_EMPTY);
        }
        return index;
    }

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

    /**
     * Extracts out the mods specified in the user command.
     *
     * @param args The user command.
     * @return A set of mods of type String.
     */
    private Set<String> getModsFromCommand(String args) {
        String[] splittedArgs = args.split("\\s+");
        List<String> extractedMods = Arrays.asList(splittedArgs).subList(1, splittedArgs.length);
        return new HashSet<>(extractedMods);
    }
}
