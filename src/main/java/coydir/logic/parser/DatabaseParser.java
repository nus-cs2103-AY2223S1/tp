package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import coydir.logic.commands.AddCommand;
import coydir.logic.commands.AddLeaveCommand;
import coydir.logic.commands.BatchAddCommand;
import coydir.logic.commands.ClearCommand;
import coydir.logic.commands.Command;
import coydir.logic.commands.DeleteCommand;
import coydir.logic.commands.DeleteLeaveCommand;
import coydir.logic.commands.EditCommand;
import coydir.logic.commands.ExitCommand;
import coydir.logic.commands.FindCommand;
import coydir.logic.commands.HelpCommand;
import coydir.logic.commands.ListCommand;
import coydir.logic.commands.RateCommand;
import coydir.logic.commands.ViewCommand;
import coydir.logic.commands.ViewDepartmentCommand;
import coydir.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class DatabaseParser {

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

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case BatchAddCommand.COMMAND_WORD:
            return new BatchAddCommandParser().parse(arguments);

        case ViewDepartmentCommand.COMMAND_WORD:
            return new ViewDepartmentCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case RateCommand.COMMAND_WORD:
            return new RateCommandParser().parse(arguments);

        case AddLeaveCommand.COMMAND_WORD:
            return new AddLeaveCommandParser().parse(arguments);

        case DeleteLeaveCommand.COMMAND_WORD:
            return new DeleteLeaveCommandParser().parse(arguments);

        default:
            throw new ParseException('"' + commandWord + '"' + MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
