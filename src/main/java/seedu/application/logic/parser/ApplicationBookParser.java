package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.AddInterviewCommand;
import seedu.application.logic.commands.ArchiveCommand;
import seedu.application.logic.commands.ClearCommand;
import seedu.application.logic.commands.Command;
import seedu.application.logic.commands.DeleteCommand;
import seedu.application.logic.commands.EditCommand;
import seedu.application.logic.commands.ExitCommand;
import seedu.application.logic.commands.FindCommand;
import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.commands.ListArchiveCommand;
import seedu.application.logic.commands.ListCommand;
import seedu.application.logic.commands.RedoCommand;
import seedu.application.logic.commands.RemindCommand;
import seedu.application.logic.commands.RemoveInterviewCommand;
import seedu.application.logic.commands.RetrieveCommand;
import seedu.application.logic.commands.SortCommand;
import seedu.application.logic.commands.StatsCommand;
import seedu.application.logic.commands.UndoCommand;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ApplicationBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform to the expected format
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

        case AddInterviewCommand.COMMAND_WORD:
            return new AddInterviewCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case RemoveInterviewCommand.COMMAND_WORD:
            return new RemoveInterviewCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemindCommand.COMMAND_WORD:
            return new RemindCommand();

        case ArchiveCommand.COMMAND_WORD:
            return new ArchiveCommandParser().parse(arguments);

        case ListArchiveCommand.COMMAND_WORD:
            return new ListArchiveCommand();

        case RetrieveCommand.COMMAND_WORD:
            return new RetrieveCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
