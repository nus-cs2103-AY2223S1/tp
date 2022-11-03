package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_EMPTY_COMMAND;
import static seedu.taassist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.taassist.logic.commands.AddCommand;
import seedu.taassist.logic.commands.AddcCommand;
import seedu.taassist.logic.commands.AddsCommand;
import seedu.taassist.logic.commands.AssignCommand;
import seedu.taassist.logic.commands.ClearCommand;
import seedu.taassist.logic.commands.Command;
import seedu.taassist.logic.commands.DeleteCommand;
import seedu.taassist.logic.commands.DeletecCommand;
import seedu.taassist.logic.commands.DeletesCommand;
import seedu.taassist.logic.commands.EditCommand;
import seedu.taassist.logic.commands.ExitCommand;
import seedu.taassist.logic.commands.ExportCommand;
import seedu.taassist.logic.commands.FindCommand;
import seedu.taassist.logic.commands.FocusCommand;
import seedu.taassist.logic.commands.GradeCommand;
import seedu.taassist.logic.commands.HelpCommand;
import seedu.taassist.logic.commands.ListCommand;
import seedu.taassist.logic.commands.ListcCommand;
import seedu.taassist.logic.commands.ScoresCommand;
import seedu.taassist.logic.commands.UnassignCommand;
import seedu.taassist.logic.commands.UnfocusCommand;
import seedu.taassist.logic.commands.ViewCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TaAssistParser {

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
            throw new ParseException(MESSAGE_EMPTY_COMMAND);
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

        case AddcCommand.COMMAND_WORD:
            return new AddcCommandParser().parse(arguments);

        case DeletecCommand.COMMAND_WORD:
            return new DeletecCommandParser().parse(arguments);

        case AssignCommand.COMMAND_WORD:
            return new AssignCommandParser().parse(arguments);

        case UnassignCommand.COMMAND_WORD:
            return new UnassignCommandParser().parse(arguments);

        case AddsCommand.COMMAND_WORD:
            return new AddsCommandParser().parse(arguments);

        case DeletesCommand.COMMAND_WORD:
            return new DeletesCommandParser().parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ScoresCommand.COMMAND_WORD:
            return new ScoresCommandParser().parse(arguments);

        case ListcCommand.COMMAND_WORD:
            return new ListcCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case FocusCommand.COMMAND_WORD:
            return new FocusCommandParser().parse(arguments);

        case UnfocusCommand.COMMAND_WORD:
            return new UnfocusCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
