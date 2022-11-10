package seedu.codeconnect.logic.parser;

import static seedu.codeconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeconnect.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.codeconnect.logic.commands.AddContactCommand;
import seedu.codeconnect.logic.commands.AddTaskCommand;
import seedu.codeconnect.logic.commands.CleanTasksCommand;
import seedu.codeconnect.logic.commands.ClearCommand;
import seedu.codeconnect.logic.commands.Command;
import seedu.codeconnect.logic.commands.DeleteContactCommand;
import seedu.codeconnect.logic.commands.DeleteTaskCommand;
import seedu.codeconnect.logic.commands.EditContactCommand;
import seedu.codeconnect.logic.commands.EditTaskCommand;
import seedu.codeconnect.logic.commands.ExitCommand;
import seedu.codeconnect.logic.commands.FindContactCommand;
import seedu.codeconnect.logic.commands.FindTaskCommand;
import seedu.codeconnect.logic.commands.HelpCommand;
import seedu.codeconnect.logic.commands.ListContactCommand;
import seedu.codeconnect.logic.commands.ListTaskCommand;
import seedu.codeconnect.logic.commands.MarkTaskCommand;
import seedu.codeconnect.logic.commands.UnmarkTaskCommand;
import seedu.codeconnect.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CodeConnectParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String SAVEME_COMMAND_WORD = "saveme";

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

        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindContactCommand.COMMAND_WORD:
            return new FindContactCommandParser().parse(arguments);

        case SAVEME_COMMAND_WORD:
            return FindContactCommand.withoutArgs();

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case CleanTasksCommand.COMMAND_WORD:
            return new CleanTasksCommand();

        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
