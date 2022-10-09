package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DeleteTaskCommand.DeleteTaskFromPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

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

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTaskCommand.MESSAGE_USAGE), pe);
        }

        DeleteTaskFromPersonDescriptor deleteTaskFromPersonDescriptor =
                new DeleteTaskFromPersonDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_NUMBER).isPresent()) {
            String taskNumberGivenByUser =
                    argMultimap.getValue(PREFIX_TASK_NUMBER).get();
            Index taskIndexToRemove =
                    ParserUtil.parseTaskNumberToDelete(taskNumberGivenByUser);
            deleteTaskFromPersonDescriptor.setIndexOfTaskToDelete(taskIndexToRemove);
        }
        return new DeleteTaskCommand(index, deleteTaskFromPersonDescriptor);
    }
}
