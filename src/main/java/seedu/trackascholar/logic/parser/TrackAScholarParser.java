package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.trackascholar.logic.commands.AddCommand;
import seedu.trackascholar.logic.commands.ClearCommand;
import seedu.trackascholar.logic.commands.Command;
import seedu.trackascholar.logic.commands.DeleteCommand;
import seedu.trackascholar.logic.commands.EditCommand;
import seedu.trackascholar.logic.commands.ExitCommand;
import seedu.trackascholar.logic.commands.FilterCommand;
import seedu.trackascholar.logic.commands.FindCommand;
import seedu.trackascholar.logic.commands.HelpCommand;
import seedu.trackascholar.logic.commands.ImportCommand;
import seedu.trackascholar.logic.commands.ListCommand;
import seedu.trackascholar.logic.commands.PinCommand;
import seedu.trackascholar.logic.commands.RemoveCommand;
import seedu.trackascholar.logic.commands.SortCommand;
import seedu.trackascholar.logic.commands.UnPinCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TrackAScholarParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
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

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case PinCommand.COMMAND_WORD:
            return new PinCommandParser().parse(arguments);

        case UnPinCommand.COMMAND_WORD:
            return new UnPinCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
