package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModAddCommand;
import seedu.address.logic.commands.ModCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Mod;

/**
 * Parses input arguments and creates a new ModCommand object.
 */
public class ModCommandParser implements Parser<ModCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern INDEX_FORMAT = Pattern.compile("\\d+");

    /**
     * Parses the given {@code userInput} of arguments in the context of the ModCommand
     * and returns a ModCommand object for execution.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ModCommand parse(String args) throws ParseException {
        requireNonNull(args);

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        String trimmedArgs = args.trim();
        // check if empty
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModCommand.MESSAGE_USAGE));
        }
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModCommand.MESSAGE_USAGE));
        }
        // parse
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // TODO: Add cases for other mod commands
        switch (commandWord) {
        case ModAddCommand.COMMAND_WORD:
            return parseAddCommand(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModCommand.MESSAGE_USAGE));
        }
    }

    private ModAddCommand parseAddCommand(String args) throws ParseException {
        Index index;
        args = args.trim();
        String indexFromCommand = getIndexFromCommand(args);
        Set<String> modsFromCommand = getModsFromCommand(args);
        Optional<Set<Mod>> mods = parseMods(modsFromCommand);
        if (mods.isEmpty()) {
            throw new ParseException(ModCommand.MESSAGE_MODS_EMPTY);
        }

        try {
            index = ParserUtil.parseIndex(indexFromCommand);
        } catch (ParseException pe) {
            throw new ParseException(ModCommand.MESSAGE_INDEX_INVALID, pe);
        }
        return new ModAddCommand(index, mods.get());
    }

    /**
     * Converts a collection of strings representing mod names to a set of mods.
     *
     * @param mods A collection of mods in string.
     * @return A set of mods.
     * @throws ParseException When mod names are of incorrect format.
     */
    private Optional<Set<Mod>> parseMods(Collection<String> mods) throws ParseException {
        assert mods != null;

        if (mods.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> modSet = mods.size() == 1 && mods.contains("") ? Collections.emptySet() : mods;
        return Optional.of(ParserUtil.parseMods(modSet));
    }

    /**
     * Extracts out the index of the person specified in the user command.
     *
     * @param args The user command.
     * @return The index of the person in String.
     */
    private String getIndexFromCommand(String args) throws ParseException {
        String[] splittedArgs = args.split(" ");
        String index = splittedArgs[0];
        final Matcher matcher = INDEX_FORMAT.matcher(index.trim());
        if (!matcher.matches()) {
            throw new ParseException(ModCommand.MESSAGE_INDEX_EMPTY);
        }
        return index;
    }

    /**
     * Extracts out the mods specified in the user command.
     *
     * @param args The user command.
     * @return A set of mods of type String.
     */
    private Set<String> getModsFromCommand(String args) {
        String[] splittedArgs = args.split(" ");
        List<String> extractedMods = Arrays.asList(splittedArgs).subList(1, splittedArgs.length);
        extractedMods = extractedMods.stream().map(String::toUpperCase).collect(Collectors.toList());
        return new HashSet<>(extractedMods);
    }
}
