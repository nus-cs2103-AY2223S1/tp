package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CloneCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.ToggleListModeCommand;
import seedu.address.logic.commands.UnappendCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SurvinParser {

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

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case CloneCommand.COMMAND_WORD:
            return new CloneCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case ThemeCommand.COMMAND_WORD:
            return new ThemeCommandParser().parse(arguments);

        case AppendCommand.COMMAND_WORD:
            return new AppendCommandParser().parse(arguments);

        case UnappendCommand.COMMAND_WORD:
            return new UnappendCommandParser().parse(arguments);

        case ToggleListModeCommand.COMMAND_WORD:
            return new ToggleListModeCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
