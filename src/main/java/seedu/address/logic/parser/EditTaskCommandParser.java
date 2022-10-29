package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTEGER_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;

import java.util.logging.Logger;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        Boolean isEdited = false;
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_PRIORITY, PREFIX_CATEGORY,
                        PREFIX_DEADLINE, PREFIX_DONE, PREFIX_PERSON);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INTEGER_INDEX)) {
                throw new ParseException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    pe.getMessage() + "\n" + EditTaskCommand.MESSAGE_USAGE),
                    pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            isEdited = true;
            editTaskDescriptor.setName(ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            isEdited = true;
            editTaskDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            isEdited = true;
            editTaskDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            isEdited = true;
            editTaskDescriptor.setCategory(ParserUtil.parseTaskCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            isEdited = true;
            editTaskDescriptor.setDeadline(ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_DONE).isPresent()) {
            isEdited = true;
            editTaskDescriptor.setDone(ParserUtil.parseTaskIsDone(argMultimap.getValue(PREFIX_DONE).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON).isPresent()) {
            isEdited = true;
            if (argMultimap.getValue(PREFIX_PERSON).get().equalsIgnoreCase("none")) {
                editTaskDescriptor.setPersonEmail(Email.getNoEmailInstance());
            } else {
                try {
                    Email personEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_PERSON).get());
                    editTaskDescriptor.setPersonEmail(personEmail);
                } catch (ParseException pe) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage()));
                }
            }
        }

        if (!editTaskDescriptor.isAnyFieldEdited() || !isEdited) {
            logger.info("TEST");
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}
