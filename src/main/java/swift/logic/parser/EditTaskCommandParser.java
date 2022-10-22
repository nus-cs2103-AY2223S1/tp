package swift.logic.parser;

import static java.util.Objects.requireNonNull;
import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.parser.CliSyntax.PREFIX_CONTACT;
import static swift.logic.parser.CliSyntax.PREFIX_NAME;

import swift.commons.core.index.Index;
import swift.logic.commands.EditTaskCommand;
import swift.logic.commands.EditTaskCommand.EditTaskDescriptor;
import swift.logic.parser.exceptions.ParseException;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CONTACT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTaskDescriptor.setTaskName(ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            editTaskDescriptor.setContactIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }

}
