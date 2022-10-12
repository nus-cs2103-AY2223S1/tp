package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_CATEGORY,
                        PREFIX_DEADLINE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTaskDescriptor.setName(ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editTaskDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editTaskDescriptor.setCategory(ParserUtil.parseTaskCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}
