package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.tasks.AddTaskCommand;
import seedu.address.logic.commands.tasks.DeleteTaskCommand;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.logic.commands.tasks.SelectTaskCommand;
import seedu.address.logic.commands.tasks.TaskCommand;
import seedu.address.logic.commands.tasks.UnmarkTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for all Task commands
 */
public class TaskCommandParser implements Parser<TaskCommand> {
    private static final String MESSAGE_USAGE = TaskCommand.COMMAND_WORD + " [add|delete|mark|unmark|select]";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subcommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution. The input must be a valid
     * subcommand for Task. There should not be
     * a TaskCommand prefix in the input.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case AddTaskCommand.SUBCOMMAND_WORD:
                return new AddTaskCommandParser().parse(arguments);
            case DeleteTaskCommand.SUBCOMMAND_WORD:
                return new DeleteTaskCommandParser().parse(arguments);
            case MarkTaskCommand.SUBCOMMAND_WORD:
                return new MarkTaskCommandParser().parse(arguments);
            case UnmarkTaskCommand.SUBCOMMAND_WORD:
                return new UnmarkTaskCommandParser().parse(arguments);
            case SelectTaskCommand.SUBCOMMAND_WORD:
                return new SelectTaskCommandParser().parse(arguments);
            default:
                throw new ParseException(MESSAGE_USAGE);
        }
    }
}
