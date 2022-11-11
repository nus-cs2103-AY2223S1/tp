package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.AddTaskCommand.AddTaskToModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.task.Task;

/**
 * Parses input arguments and creates a new {@code AddTaskCommand} object.
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
        checkIfAllArgumentsArePresent(argMultimap);

        AddTaskToModuleDescriptor addTaskToModuleDescriptor =
                new AddTaskToModuleDescriptor();
        String moduleCodeOfModuleToAddTaskToAsString =
                argMultimap.getValue(PREFIX_MODULE_CODE).get();
        ModuleCode moduleCodeOfModuleToAddTaskTo =
                ParserUtil.parseModuleCode(moduleCodeOfModuleToAddTaskToAsString);
        addTaskToModuleDescriptor.setModuleCodeOfModuleToAddTaskTo(moduleCodeOfModuleToAddTaskTo);
        String taskDescriptionOfNewTaskToAdd =
                argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get();
        Task newTaskToAdd =
                ParserUtil.parseNewTaskDescription(taskDescriptionOfNewTaskToAdd);
        addTaskToModuleDescriptor.setNewTask(newTaskToAdd);
        return new AddTaskCommand(addTaskToModuleDescriptor);
    }

    /**
     * Checks that all arguments are present.
     * @param argMultimap {@code ArgumentMultimap} containing the arguments
     *                    given by the user.
     */
    private void checkIfAllArgumentsArePresent(ArgumentMultimap argMultimap) throws ParseException {
        Boolean isModuleCodeAbsent = !arePrefixesPresent(argMultimap,
                PREFIX_MODULE_CODE);
        Boolean isPreamblePresent = !argMultimap.getPreamble().isEmpty();
        Boolean isTaskDescriptionAbsent = !arePrefixesPresent(argMultimap,
                PREFIX_TASK_DESCRIPTION);
        if (isModuleCodeAbsent || isPreamblePresent || isTaskDescriptionAbsent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.MESSAGE_USAGE));
        }
    }
}
