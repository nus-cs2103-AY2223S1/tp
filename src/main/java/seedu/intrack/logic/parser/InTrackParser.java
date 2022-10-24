package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.intrack.logic.commands.AddCommand;
import seedu.intrack.logic.commands.AddTaskCommand;
import seedu.intrack.logic.commands.ClearCommand;
import seedu.intrack.logic.commands.Command;
import seedu.intrack.logic.commands.DeleteCommand;
import seedu.intrack.logic.commands.DeleteTaskCommand;
import seedu.intrack.logic.commands.EditCommand;
import seedu.intrack.logic.commands.ExitCommand;
import seedu.intrack.logic.commands.FilterCommand;
import seedu.intrack.logic.commands.FindNameCommand;
import seedu.intrack.logic.commands.FindPositionCommand;
import seedu.intrack.logic.commands.HelpCommand;
import seedu.intrack.logic.commands.ListCommand;
import seedu.intrack.logic.commands.RemarkCommand;
import seedu.intrack.logic.commands.SelectCommand;
import seedu.intrack.logic.commands.StatsCommand;
import seedu.intrack.logic.commands.StatusCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InTrackParser {

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

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case FindNameCommand.COMMAND_WORD:
            return new FindNameCommandParser().parse(arguments);

        case FindPositionCommand.COMMAND_WORD:
            return new FindPositionCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();

        case StatusCommand.COMMAND_WORD:
            return new StatusCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
