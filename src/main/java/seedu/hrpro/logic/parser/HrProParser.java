package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.hrpro.logic.commands.AddCommand;
import seedu.hrpro.logic.commands.AddStaffCommand;
import seedu.hrpro.logic.commands.AddTaskCommand;
import seedu.hrpro.logic.commands.ClearCommand;
import seedu.hrpro.logic.commands.Command;
import seedu.hrpro.logic.commands.DeleteCommand;
import seedu.hrpro.logic.commands.DeleteStaffCommand;
import seedu.hrpro.logic.commands.DeleteTaskCommand;
import seedu.hrpro.logic.commands.EditCommand;
import seedu.hrpro.logic.commands.EditStaffCommand;
import seedu.hrpro.logic.commands.EditTaskCommand;
import seedu.hrpro.logic.commands.ExitCommand;
import seedu.hrpro.logic.commands.FindCommand;
import seedu.hrpro.logic.commands.FindStaffCommand;
import seedu.hrpro.logic.commands.FindTaskCommand;
import seedu.hrpro.logic.commands.HelpCommand;
import seedu.hrpro.logic.commands.ListCommand;
import seedu.hrpro.logic.commands.MarkTaskCommand;
import seedu.hrpro.logic.commands.SortCommand;
import seedu.hrpro.logic.commands.SortCompleteCommand;
import seedu.hrpro.logic.commands.SortTaskCommand;
import seedu.hrpro.logic.commands.UnmarkTaskCommand;
import seedu.hrpro.logic.commands.ViewCommand;
import seedu.hrpro.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class HrProParser {

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

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddStaffCommand.COMMAND_WORD:
            return new AddStaffCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case EditStaffCommand.COMMAND_WORD:
            return new EditStaffCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteStaffCommand.COMMAND_WORD:
            return new DeleteStaffCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        case FindStaffCommand.COMMAND_WORD:
            return new FindStaffCommandParser().parse(arguments);

        case SortTaskCommand.COMMAND_WORD:
            return new SortTaskCommand();

        case SortCompleteCommand.COMMAND_WORD:
            return new SortCompleteCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
