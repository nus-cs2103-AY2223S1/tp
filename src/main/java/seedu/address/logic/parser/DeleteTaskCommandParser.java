package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBER;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DeleteTaskCommand.DeleteTaskFromModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new {@code DeleteTaskCommand} object.
 */
public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code DeleteCommand} and returns a {@code DeleteCommand} object for
     * execution.
     * @throws ParseException if the user input does not conform with the
     *                        expected format.
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                        PREFIX_MODULE_CODE, PREFIX_TASK_NUMBER);
        Boolean isModuleCodeAbsent = !arePrefixesPresent(argMultimap,
                PREFIX_MODULE_CODE);
        Boolean isPreamblePresent = !argMultimap.getPreamble().isEmpty();
        Boolean isTaskNumberAbsent = !arePrefixesPresent(argMultimap,
                PREFIX_TASK_NUMBER);
        if (isModuleCodeAbsent || isPreamblePresent || isTaskNumberAbsent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTaskCommand.MESSAGE_USAGE));
        }

        DeleteTaskFromModuleDescriptor deleteTaskFromPersonDescriptor =
                new DeleteTaskFromModuleDescriptor();
        String moduleCodeOfModuleToDeleteTaskFromAsString =
                argMultimap.getValue(PREFIX_MODULE_CODE).get();
        ModuleCode moduleCodeOfModuleToDeleteTaskFrom =
                ParserUtil.parseModuleCode(moduleCodeOfModuleToDeleteTaskFromAsString);
        deleteTaskFromPersonDescriptor.setModuleCodeOfModuleWithTaskToDelete(moduleCodeOfModuleToDeleteTaskFrom);
        String taskNumberGivenByUser =
                argMultimap.getValue(PREFIX_TASK_NUMBER).get();
        Index taskIndexToRemove =
                ParserUtil.parseTaskNumberToDelete(taskNumberGivenByUser);
        deleteTaskFromPersonDescriptor.setIndexOfTaskToDelete(taskIndexToRemove);
        return new DeleteTaskCommand(deleteTaskFromPersonDescriptor);
    }
}
