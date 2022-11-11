package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.studmap.logic.commands.AddCommand;
import seedu.studmap.logic.commands.ClearCommand;
import seedu.studmap.logic.commands.Command;
import seedu.studmap.logic.commands.DeleteCommand;
import seedu.studmap.logic.commands.EditCommand;
import seedu.studmap.logic.commands.ExitCommand;
import seedu.studmap.logic.commands.FilterCommand;
import seedu.studmap.logic.commands.FindCommand;
import seedu.studmap.logic.commands.GradeCommand;
import seedu.studmap.logic.commands.HelpCommand;
import seedu.studmap.logic.commands.ImportCommand;
import seedu.studmap.logic.commands.ListCommand;
import seedu.studmap.logic.commands.MarkCommand;
import seedu.studmap.logic.commands.ParticipateCommand;
import seedu.studmap.logic.commands.SortCommand;
import seedu.studmap.logic.commands.TagCommand;
import seedu.studmap.logic.commands.UngradeCommand;
import seedu.studmap.logic.commands.UnmarkCommand;
import seedu.studmap.logic.commands.UnparticipateCommand;
import seedu.studmap.logic.commands.UntagCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class StudMapParser {

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

        case ImportCommand.COMMAND_WORD:
            return new ImportCommand();

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case UntagCommand.COMMAND_WORD:
            return new UntagCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case UngradeCommand.COMMAND_WORD:
            return new UngradeCommandParser().parse(arguments);

        case ParticipateCommand.COMMAND_WORD:
            return new ParticipateCommandParser().parse(arguments);

        case UnparticipateCommand.COMMAND_WORD:
            return new UnparticipateCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
