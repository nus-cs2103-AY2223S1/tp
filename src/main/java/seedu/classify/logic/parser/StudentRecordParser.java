package seedu.classify.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.AddStudentCommand;
import seedu.classify.logic.commands.ClearCommand;
import seedu.classify.logic.commands.Command;
import seedu.classify.logic.commands.DeleteCommand;
import seedu.classify.logic.commands.EditCommand;
import seedu.classify.logic.commands.ExitCommand;
import seedu.classify.logic.commands.FindCommand;
import seedu.classify.logic.commands.HelpCommand;
import seedu.classify.logic.commands.ToggleViewCommand;
import seedu.classify.logic.commands.ViewAllCommand;
import seedu.classify.logic.commands.ViewClassCommand;
import seedu.classify.logic.commands.ViewStatsCommand;
import seedu.classify.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class StudentRecordParser {

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
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ViewAllCommand.COMMAND_WORD:
            return new ViewAllCommand();

        case ViewClassCommand.COMMAND_WORD:
            return new ViewClassCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ToggleViewCommand.COMMAND_WORD:
            return new ToggleViewCommand();

        case ViewStatsCommand.COMMAND_WORD:
            return new ViewStatsCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
