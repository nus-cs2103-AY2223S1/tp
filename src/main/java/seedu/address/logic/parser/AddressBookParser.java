package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.commands.contact.FilterContactCommand;
import seedu.address.logic.commands.contact.FindContactCommand;
import seedu.address.logic.commands.contact.ListContactCommand;
import seedu.address.logic.commands.tag.AddTagCommand;
import seedu.address.logic.commands.tag.DeleteTagCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.FilterTaskCommand;
import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.task.MarkTaskCommand;
import seedu.address.logic.commands.task.UnmarkTaskCommand;
import seedu.address.logic.parser.contact.AddContactCommandParser;
import seedu.address.logic.parser.contact.DeleteContactCommandParser;
import seedu.address.logic.parser.contact.EditContactCommandParser;
import seedu.address.logic.parser.contact.FilterContactCommandParser;
import seedu.address.logic.parser.contact.FindContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.tag.AddTagCommandParser;
import seedu.address.logic.parser.tag.DeleteTagCommandParser;
import seedu.address.logic.parser.task.AddTaskCommandParser;
import seedu.address.logic.parser.task.EditTaskCommandParser;
import seedu.address.logic.parser.task.FilterTaskCommandParser;
import seedu.address.logic.parser.task.FindTaskCommandParser;
import seedu.address.logic.parser.task.MarkTaskCommandParser;
import seedu.address.logic.parser.task.UnmarkTaskCommandParser;

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

        case FilterContactCommand.COMMAND_WORD:
            return new FilterContactCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case FilterTaskCommand.COMMAND_WORD:
            return new FilterTaskCommandParser().parse(arguments);

        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
