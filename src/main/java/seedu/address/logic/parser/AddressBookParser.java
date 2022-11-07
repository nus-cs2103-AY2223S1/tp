package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.CopyContactEmailCommand;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.commands.contact.FilterContactCommand;
import seedu.address.logic.commands.contact.FindContactCommand;
import seedu.address.logic.commands.contact.ListContactCommand;
import seedu.address.logic.commands.tag.AddTagCommand;
import seedu.address.logic.commands.tag.DeleteAllCommand;
import seedu.address.logic.commands.tag.DeleteTagCommand;
import seedu.address.logic.commands.tag.ListTagCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.ArchiveTaskCommand;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.FilterTaskCommand;
import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.commands.task.ListArchivedTaskCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.task.MarkTaskCommand;
import seedu.address.logic.commands.task.ReminderCommand;
import seedu.address.logic.commands.task.SortByDeadlineCommand;
import seedu.address.logic.commands.task.SortByIdCommand;
import seedu.address.logic.commands.task.TaskProgressCommand;
import seedu.address.logic.commands.task.UnarchiveTaskCommand;
import seedu.address.logic.commands.task.UnmarkTaskCommand;
import seedu.address.logic.parser.contact.AddContactCommandParser;
import seedu.address.logic.parser.contact.CopyContactEmailCommandParser;
import seedu.address.logic.parser.contact.DeleteContactCommandParser;
import seedu.address.logic.parser.contact.EditContactCommandParser;
import seedu.address.logic.parser.contact.FilterContactCommandParser;
import seedu.address.logic.parser.contact.FindContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.tag.AddTagCommandParser;
import seedu.address.logic.parser.tag.DeleteAllCommandParser;
import seedu.address.logic.parser.tag.DeleteTagCommandParser;
import seedu.address.logic.parser.task.AddTaskCommandParser;
import seedu.address.logic.parser.task.ArchiveTaskCommandParser;
import seedu.address.logic.parser.task.DeleteTaskCommandParser;
import seedu.address.logic.parser.task.EditTaskCommandParser;
import seedu.address.logic.parser.task.FilterTaskCommandParser;
import seedu.address.logic.parser.task.FindTaskCommandParser;
import seedu.address.logic.parser.task.MarkTaskCommandParser;
import seedu.address.logic.parser.task.ReminderCommandParser;
import seedu.address.logic.parser.task.TaskProgressCommandParser;
import seedu.address.logic.parser.task.UnarchiveTaskCommandParser;
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

        case FindContactCommand.COMMAND_WORD:
            return new FindContactCommandParser().parse(arguments);

        case FilterContactCommand.COMMAND_WORD:
            return new FilterContactCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case CopyContactEmailCommand.COMMAND_WORD:
            return new CopyContactEmailCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case DeleteAllCommand.COMMAND_WORD:
            return new DeleteAllCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case FilterTaskCommand.COMMAND_WORD:
            return new FilterTaskCommandParser().parse(arguments);

        case ArchiveTaskCommand.COMMAND_WORD:
            return new ArchiveTaskCommandParser().parse(arguments);

        case UnarchiveTaskCommand.COMMAND_WORD:
            return new UnarchiveTaskCommandParser().parse(arguments);

        case ReminderCommand.COMMAND_WORD:
            return new ReminderCommandParser().parse(arguments);

        case TaskProgressCommand.COMMAND_WORD:
            return new TaskProgressCommandParser().parse(arguments);

        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case ListArchivedTaskCommand.COMMAND_WORD:
            return new ListArchivedTaskCommand();

        case ListTagCommand.COMMAND_WORD:
            return new ListTagCommand();

        case SortByDeadlineCommand.COMMAND_WORD:
            return new SortByDeadlineCommand();

        case SortByIdCommand.COMMAND_WORD:
            return new SortByIdCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into String to determine tab to show.
     *
     * @param userInput full user input string
     * @return the unique int id of the tab to show
     * @throws ParseException if the user input does not conform the expected format
     */
    public int parseCommandForTab(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        switch (commandWord) {

        case AddContactCommand.COMMAND_WORD:
        case EditContactCommand.COMMAND_WORD:
        case DeleteContactCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindContactCommand.COMMAND_WORD:
        case FilterContactCommand.COMMAND_WORD:
        case ListContactCommand.COMMAND_WORD:
        case CopyContactEmailCommand.COMMAND_WORD:
            return 0;

        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_WORD:
            return 3;

        case AddTagCommand.COMMAND_WORD:
        case DeleteTagCommand.COMMAND_WORD:
        case DeleteAllCommand.COMMAND_WORD:
        case ListTagCommand.COMMAND_WORD:
            return 2;

        case AddTaskCommand.COMMAND_WORD:
        case DeleteTaskCommand.COMMAND_WORD:
        case EditTaskCommand.COMMAND_WORD:
        case FindTaskCommand.COMMAND_WORD:
        case FilterTaskCommand.COMMAND_WORD:
        case ReminderCommand.COMMAND_WORD:
        case TaskProgressCommand.COMMAND_WORD:
        case MarkTaskCommand.COMMAND_WORD:
        case UnmarkTaskCommand.COMMAND_WORD:
        case ListTaskCommand.COMMAND_WORD:
        case ListArchivedTaskCommand.COMMAND_WORD:
        case SortByDeadlineCommand.COMMAND_WORD:
        case SortByIdCommand.COMMAND_WORD:
        case ArchiveTaskCommand.COMMAND_WORD:
        case UnarchiveTaskCommand.COMMAND_WORD:
            return 1;

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
