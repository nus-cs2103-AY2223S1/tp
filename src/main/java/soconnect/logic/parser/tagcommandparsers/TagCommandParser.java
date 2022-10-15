package soconnect.logic.parser.tagcommandparsers;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soconnect.logic.commands.Command;
import soconnect.logic.commands.HelpCommand;
import soconnect.logic.commands.tagcommands.TagAddCommand;
import soconnect.logic.commands.tagcommands.TagCreateCommand;
import soconnect.logic.commands.tagcommands.TagEditCommand;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses tag specific user input.
 */
public class TagCommandParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput Full user input string.
     * @return The command based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public Command parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case TagCreateCommand.COMMAND_WORD:
            return new TagCreateCommandParser().parse(arguments);

        case TagEditCommand.COMMAND_WORD:
            return new TagEditCommandParser().parse(arguments);

        case TagAddCommand.COMMAND_WORD:
            return new TagAddCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
