package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import foodwhere.logic.commands.ClearCommand;
import foodwhere.logic.commands.Command;
import foodwhere.logic.commands.ExitCommand;
import foodwhere.logic.commands.HelpCommand;
import foodwhere.logic.commands.RAddCommand;
import foodwhere.logic.commands.RDeleteCommand;
import foodwhere.logic.commands.REditCommand;
import foodwhere.logic.commands.RListCommand;
import foodwhere.logic.commands.SAddCommand;
import foodwhere.logic.commands.SDeleteCommand;
import foodwhere.logic.commands.SEditCommand;
import foodwhere.logic.commands.SFindCommand;
import foodwhere.logic.commands.SListCommand;
import foodwhere.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case RAddCommand.COMMAND_WORD:
            return new RAddCommandParser().parse(arguments);

        case SAddCommand.COMMAND_WORD:
            return new SAddCommandParser().parse(arguments);

        case SDeleteCommand.COMMAND_WORD:
            return new SDeleteCommandParser().parse(arguments);

        case RDeleteCommand.COMMAND_WORD:
            return new RDeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case RListCommand.COMMAND_WORD:
            return new RListCommand();

        case SListCommand.COMMAND_WORD:
            return new SListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case REditCommand.COMMAND_WORD:
            return new REditCommandParser().parse(arguments);

        case SEditCommand.COMMAND_WORD:
            return new SEditCommandParser().parse(arguments);

        case SFindCommand.COMMAND_WORD:
            return new SFindCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
