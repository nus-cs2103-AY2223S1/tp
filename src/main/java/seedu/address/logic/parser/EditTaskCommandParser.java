package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_ADD_STUDENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_DELETE_STUDENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_TITLE, PREFIX_TASK_DESCRIPTION,
                PREFIX_DEADLINE_DATE, PREFIX_ASSIGNMENT_ADD_STUDENTS, PREFIX_ASSIGNMENT_DELETE_STUDENTS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_TITLE).isPresent()) {
            editTaskDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TASK_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent()) {
            editTaskDescriptor
                    .setDescription(ParserUtil
                            .parseDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE_DATE).isPresent()) {
            editTaskDescriptor
                    .setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DEADLINE_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_ASSIGNMENT_ADD_STUDENTS).isPresent()) {
            editTaskDescriptor
                    .setStudentsToAdd(ParserUtil
                            .parseStudents(argMultimap.getValue(PREFIX_ASSIGNMENT_ADD_STUDENTS).get()));
        }

        if (argMultimap.getValue(PREFIX_ASSIGNMENT_DELETE_STUDENTS).isPresent()) {
            editTaskDescriptor
                    .setStudentsToDelete(ParserUtil
                            .parseStudents(argMultimap.getValue(PREFIX_ASSIGNMENT_DELETE_STUDENTS).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}
