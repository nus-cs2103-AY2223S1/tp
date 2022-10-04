package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jarvis.logic.commands.student.AddCommand;
import jarvis.logic.commands.student.ClearCommand;
import jarvis.logic.commands.Command;
import jarvis.logic.commands.student.DeleteCommand;
import jarvis.logic.commands.student.EditCommand;
import jarvis.logic.commands.ExitCommand;
import jarvis.logic.commands.student.FindCommand;
import jarvis.logic.commands.HelpCommand;
import jarvis.logic.commands.student.ListCommand;
import jarvis.logic.commands.task.AddTaskCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.logic.parser.student.AddCommandParser;
import jarvis.logic.parser.student.DeleteCommandParser;
import jarvis.logic.parser.student.EditCommandParser;
import jarvis.logic.parser.student.FindCommandParser;
import jarvis.logic.parser.task.AddTaskCommandParser;

/**
 * Parses user input.
 */
public class JarvisParser {

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

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
