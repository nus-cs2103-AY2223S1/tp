package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddQCommand;
import seedu.address.logic.commands.AddResponseCommand;
import seedu.address.logic.commands.AddStuCommand;
import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteQCommand;
import seedu.address.logic.commands.DeleteStuCommand;
import seedu.address.logic.commands.DeleteTutorialCommand;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindStuCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HelpStuCommand;
import seedu.address.logic.commands.ListStuCommand;
import seedu.address.logic.commands.MarkQCommand;
import seedu.address.logic.commands.MarkTutorialCommand;
import seedu.address.logic.commands.UnhelpStuCommand;
import seedu.address.logic.commands.UnmarkQCommand;
import seedu.address.logic.commands.UnmarkTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        case AddQCommand.COMMAND_WORD:
            return new AddQCommandParser().parse(arguments);

        case AddStuCommand.COMMAND_WORD:
            return new AddStuCommandParser().parse(arguments);

        case EditStuCommand.COMMAND_WORD:
            return new EditStuCommandParser().parse(arguments);

        case DeleteQCommand.COMMAND_WORD:
            return new DeleteQCommandParser().parse(arguments);

        case DeleteStuCommand.COMMAND_WORD:
            return new DeleteStuCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindStuCommand.COMMAND_WORD:
            return new FindStuCommandParser().parse(arguments);

        case ListStuCommand.COMMAND_WORD:
            return new ListStuCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddTutorialCommand.COMMAND_WORD:
            return new AddTutorialCommandParser().parse(arguments);

        case DeleteTutorialCommand.COMMAND_WORD:
            return new DeleteTutorialCommandParser().parse(arguments);

        case MarkQCommand.COMMAND_WORD:
            return new MarkQCommandParser().parse(arguments);

        case UnmarkQCommand.COMMAND_WORD:
            return new UnmarkQCommandParser().parse(arguments);

        case MarkTutorialCommand.COMMAND_WORD:
            return new MarkTutorialCommandParser().parse(arguments);

        case UnmarkTutorialCommand.COMMAND_WORD:
            return new UnmarkTutorialCommandParser().parse(arguments);

        case AttendanceCommand.COMMAND_WORD:
            return new AttendanceCommandParser().parse(arguments);

        case AddResponseCommand.COMMAND_WORD:
            return new AddResponseCommandParser().parse(arguments);

        case HelpStuCommand.COMMAND_WORD:
            return new HelpStuCommandParser().parse(arguments);

        case UnhelpStuCommand.COMMAND_WORD:
            return new UnhelpStuCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
