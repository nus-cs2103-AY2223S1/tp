package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swift.logic.commands.AddContactCommand;
import swift.logic.commands.AddTaskCommand;
import swift.logic.commands.AssignCommand;
import swift.logic.commands.ClearCommand;
import swift.logic.commands.Command;
import swift.logic.commands.DeleteContactCommand;
import swift.logic.commands.DeleteTaskCommand;
import swift.logic.commands.EditContactCommand;
import swift.logic.commands.EditTaskCommand;
import swift.logic.commands.ExitCommand;
import swift.logic.commands.FindContactCommand;
import swift.logic.commands.FindTaskCommand;
import swift.logic.commands.HelpCommand;
import swift.logic.commands.ListContactCommand;
import swift.logic.commands.ListTaskCommand;
import swift.logic.commands.MarkTaskCommand;
import swift.logic.commands.SelectContactCommand;
import swift.logic.commands.SelectTaskCommand;
import swift.logic.commands.UnassignCommand;
import swift.logic.commands.UnmarkTaskCommand;
import swift.logic.parser.exceptions.ParseException;

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
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE)
            );
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
        case SelectContactCommand.COMMAND_WORD:
            return new SelectContactCommandParser().parse(arguments);
        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();
        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();
        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);
        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);
        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);
        case SelectTaskCommand.COMMAND_WORD:
            return new SelectTaskCommandParser().parse(arguments);
        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);
        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);
        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);
        case UnassignCommand.COMMAND_WORD:
            return new UnassignCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
