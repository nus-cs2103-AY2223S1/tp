package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ForEachCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.tasks.AddTaskCommand;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.logic.commands.tasks.TaskCommand;
import seedu.address.logic.commands.tasks.UnmarkTaskCommand;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parser for all Task commands
 */
public class TaskCommandParser implements Parser<Command> {
    private static final String MESSAGE_USAGE = TaskCommand.COMMAND_WORD + " [add|delete|mark|unmark|select]";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subcommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution. The input must be a valid subcommand for Task.
     * There should not be a TaskCommand prefix in the input.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddTaskCommand.SUBCOMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);
        case DeleteCommand.SUBCOMMAND_WORD:
            return DeleteCommand
                .<Task>parser((m, i) -> m.getFromFilteredTasks(i), (m, task) -> m.deleteTask(task),
                    o -> o instanceof Task)
                .parse(arguments);
        case MarkTaskCommand.SUBCOMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);
        case UnmarkTaskCommand.SUBCOMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);
        case SelectCommand.SUBCOMMAND_WORD:
            return SelectCommand.parser((m, i) -> m.getFromFilteredTasks(i)).parse(arguments);
        case ForEachCommand.SUBCOMMAND_WORD:
            return ForEachCommand.parser(m -> m.getFilteredTaskList()).parse(arguments);
        case FindCommand.SUBCOMMAND_WORD:
            return new FindCommandParser<Task>((m, p) -> m.updateFilteredTaskList(p),
                m -> m.getFilteredTaskList().size()).parse(arguments);
        default:
            throw new ParseException(MESSAGE_USAGE);
        }
    }
}
