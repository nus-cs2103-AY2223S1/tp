package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modtrekt.logic.commands.AddCommand;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.ExitCommand;
import modtrekt.logic.commands.HelpCommand;
import modtrekt.logic.commands.RemoveTaskCommand;
import modtrekt.logic.commands.tasks.ListTasksCommand;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.logic.parser.tasks.ListTasksCommandParser;

/**
 * Parses user input.
 */
public class TaskBookParser {

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

        //case AddTaskCommand.COMMAND_WORD:
        //    return new AddTaskCommandParser().parse(arguments);
        case RemoveTaskCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case ListTasksCommand.COMMAND_WORD:
            return new ListTasksCommandParser().parse(arguments);

        //case DeleteCommand.COMMAND_WORD:
        //    return new DeleteCommandParser().parse(arguments);

        //case ListCommand.COMMAND_WORD:
        //    return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
