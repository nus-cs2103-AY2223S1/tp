package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ModAddCommand;
import seedu.address.logic.commands.ModCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ModCommandParser implements Parser<ModCommand>{

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

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
        // parse
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case ModAddCommand.COMMAND_WORD:
            return parseAddCommand(arguments);
        default:
            throw new ParseException(ModCommand.MESSAGE_USAGE);
        }
    }

    private ModAddCommand parseAddCommand(String args) throws ParseException {
        return null;
    }
}
