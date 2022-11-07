package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gim.logic.commands.AddCommand;
import gim.logic.commands.ClearCommand;
import gim.logic.commands.Command;
import gim.logic.commands.DeleteCommand;
import gim.logic.commands.ExitCommand;
import gim.logic.commands.FilterCommand;
import gim.logic.commands.GenerateCommand;
import gim.logic.commands.HelpCommand;
import gim.logic.commands.ListCommand;
import gim.logic.commands.PrCommand;
import gim.logic.commands.RangeCommand;
import gim.logic.commands.SortCommand;
import gim.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ExerciseTrackerParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RangeCommand.COMMAND_WORD:
            return new RangeCommandParser().parse(arguments);

        case GenerateCommand.COMMAND_WORD:
            return new GenerateCommandParser().parse(arguments);

        case PrCommand.COMMAND_WORD:
            return new PrCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
