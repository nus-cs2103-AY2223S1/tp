package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.watson.logic.commands.AddCommand;
import seedu.watson.logic.commands.AttendanceCommand;
import seedu.watson.logic.commands.ClearCommand;
import seedu.watson.logic.commands.Command;
import seedu.watson.logic.commands.DeleteCommand;
import seedu.watson.logic.commands.EditCommand;
import seedu.watson.logic.commands.ExitCommand;
import seedu.watson.logic.commands.FindCommand;
import seedu.watson.logic.commands.GradeCommand;
import seedu.watson.logic.commands.HelpCommand;
import seedu.watson.logic.commands.ListCommand;
import seedu.watson.logic.commands.PredictionCommand;
import seedu.watson.logic.commands.RemarkCommand;
import seedu.watson.logic.commands.SortCommand;
import seedu.watson.logic.parser.exceptions.ParseException;

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

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case PredictionCommand.COMMAND_WORD:
            return new PredictionCommandParser().parse(arguments);

        case AttendanceCommand.COMMAND_WORD:
            return new AttendanceCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
