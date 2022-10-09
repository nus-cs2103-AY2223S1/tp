package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.AddTaskCommand.AddTaskToPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.task.Task;

/**
 * Parses input arguments and creates a new {@code AddTaskCommand} object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code AddTaskCommand} and returns an {@code AddTaskCommand} object
     * for execution.
     * @throws ParseException if user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_MODULE_CODE, PREFIX_TASK_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE), pe);
        }

        AddTaskToPersonDescriptor addTaskToPersonDescriptor =
                new AddTaskToPersonDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent()) {
            String taskDescriptionOfNewTaskToAdd =
                    argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get();
            Task newTaskToAdd =
                    ParserUtil.parseNewTaskDescription(taskDescriptionOfNewTaskToAdd);
            addTaskToPersonDescriptor.setNewTask(newTaskToAdd);
        }
        return new AddTaskCommand(index, addTaskToPersonDescriptor);
    }
}
