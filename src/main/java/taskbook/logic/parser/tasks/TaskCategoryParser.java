package taskbook.logic.parser.tasks;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.tasks.TaskDeadlineCommand;
import taskbook.logic.commands.tasks.TaskDeleteCommand;
import taskbook.logic.commands.tasks.TaskEditCommand;
import taskbook.logic.commands.tasks.TaskEventCommand;
import taskbook.logic.commands.tasks.TaskFindCommand;
import taskbook.logic.commands.tasks.TaskListCommand;
import taskbook.logic.commands.tasks.TaskMarkCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.logic.commands.tasks.TaskUnmarkCommand;
import taskbook.logic.parser.exceptions.ParseException;

/**
 * Parses user input of task category.
 */
public class TaskCategoryParser {
    public static final String CATEGORY_WORD = "task";
    public static final String CATEGORY_WORD_SHORTCUT = "t";

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
        case TaskTodoCommand.COMMAND_WORD:
            return new TaskTodoCommandParser().parse(arguments);
        case TaskDeadlineCommand.COMMAND_WORD:
            return new TaskDeadlineCommandParser().parse(arguments);
        case TaskEventCommand.COMMAND_WORD:
            return new TaskEventCommandParser().parse(arguments);
        case TaskEditCommand.COMMAND_WORD:
            return new TaskEditCommandParser().parse(arguments);
        case TaskMarkCommand.COMMAND_WORD:
            return new TaskMarkCommandParser().parse(arguments);
        case TaskUnmarkCommand.COMMAND_WORD:
            return new TaskUnmarkCommandParser().parse(arguments);
        case TaskDeleteCommand.COMMAND_WORD:
            return new TaskDeleteCommandParser().parse(arguments);
        case TaskListCommand.COMMAND_WORD:
            return new TaskListCommand();
        case TaskSortCommand.COMMAND_WORD:
            return new TaskSortCommandParser().parse(arguments);
        case TaskFindCommand.COMMAND_WORD:
            return new TaskFindCommandParser().parse(arguments);
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
