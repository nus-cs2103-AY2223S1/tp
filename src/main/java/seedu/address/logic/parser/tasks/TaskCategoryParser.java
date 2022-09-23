package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.tasks.TaskAddCommand;
import seedu.address.logic.commands.tasks.TaskDeleteCommand;
import seedu.address.logic.commands.tasks.TaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input of task category.
 */
public class TaskCategoryParser {
    public static final String CATEGORY_WORD = "task";

    /**
     * Parses user input into command for execution.
     *
     * @param commandWord Command word provided by the user.
     * @param arguments   Arguments provided by the user.
     * @return The command based on the user command word and arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public static Command parseCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case TaskAddCommand.COMMAND_WORD:
            return new TaskAddCommandParser().parse(arguments);
        case TaskDeleteCommand.COMMAND_WORD:
            return new TaskDeleteCommandParser().parse(arguments);
        case TaskListCommand.COMMAND_WORD:
            return new TaskListCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
