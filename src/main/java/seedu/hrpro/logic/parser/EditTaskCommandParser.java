package seedu.hrpro.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditTaskCommand;
import seedu.hrpro.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.hrpro.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION, PREFIX_TASK_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setTaskDescription(ParserUtil
                    .parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            editTaskDescriptor.setTaskDeadline(ParserUtil.parseDeadline(
                    argMultimap.getValue(PREFIX_TASK_DEADLINE).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}
