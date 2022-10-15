package soconnect.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soconnect.commons.core.Messages;
import soconnect.logic.commands.AddCommand;
import soconnect.logic.commands.ClearCommand;
import soconnect.logic.commands.Command;
import soconnect.logic.commands.CustomiseCommand;
import soconnect.logic.commands.DeleteCommand;
import soconnect.logic.commands.EditCommand;
import soconnect.logic.commands.ExitCommand;
import soconnect.logic.commands.FindCommand;
import soconnect.logic.commands.HelpCommand;
import soconnect.logic.commands.ListCommand;
import soconnect.logic.commands.SearchCommand;
import soconnect.logic.commands.SortCommand;
import soconnect.logic.commands.tagcommands.TagCommand;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.logic.parser.tagcommandparsers.TagCommandParser;

/**
 * Parses user input.
 */
public class SoConnectParser {

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
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case CustomiseCommand.COMMAND_WORD:
            return new CustomiseCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
