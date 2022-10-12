package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_STUDENT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TaskEditCommand;
import seedu.address.logic.commands.task.TaskEditCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class TaskEditCommandParser implements Parser<TaskEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an TaskEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_TASK_DEADLINE,
                        PREFIX_TASK_DESC, PREFIX_TASK_STUDENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new TaskEditCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            editTaskDescriptor.setTaskName(ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            editTaskDescriptor.setTaskDeadline(
                    ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get())
            );
        }
        if (argMultimap.getValue(PREFIX_TASK_DESC).isPresent()) {
            editTaskDescriptor.setTaskDescription(
                    ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESC).get())
            );
        }
        if (argMultimap.getValue(PREFIX_TASK_STUDENT).isPresent()) {
            List<String> names = argMultimap.getAllValues(PREFIX_TASK_STUDENT);
            editTaskDescriptor.setStudentNames(names);
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(TaskEditCommand.MESSAGE_NOT_EDITED);
        }

        return new TaskEditCommand(index, editTaskDescriptor);
    }

}
