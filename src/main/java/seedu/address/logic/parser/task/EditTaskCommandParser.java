package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Project;

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
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DEADLINE, PREFIX_PROJECT, PREFIX_CONTACT);

        Index targetIndex;

        try {
            targetIndex = TaskParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editTaskDescriptor.setTitle(TaskParserUtil
                    .parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(TaskParserUtil
                .parseDeadline(argMultimap.getValue(PREFIX_TITLE).get()));
        }

        if (argMultimap.getValue(PREFIX_PROJECT).isPresent()) {
            if (argMultimap.getValue(PREFIX_PROJECT).get().equals("")) {
                editTaskDescriptor.setProject(Project.UNSPECIFIED);
            } else {
                editTaskDescriptor.setProject(TaskParserUtil
                        .parseProject(argMultimap.getValue(PREFIX_PROJECT).get()));
            }
        }
        parseContactsForEdit(
            argMultimap.getAllValues(PREFIX_CONTACT)).ifPresent(editTaskDescriptor::setAssignedContacts);

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(targetIndex, editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> contacts} into a {@code Set<Contact>} if {@code contacts} is non-empty.
     * If {@code contacts} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Contact>} containing zero contacts.
     */
    private Optional<Set<Contact>> parseContactsForEdit(Collection<String> contacts) throws ParseException {
        assert contacts != null;

        if (contacts.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> contactSet = contacts.size() == 1 && contacts.contains("")
            ? Collections.emptySet()
            : contacts;

        return Optional.of(TaskParserUtil.parseContacts(contactSet));
    }
}
