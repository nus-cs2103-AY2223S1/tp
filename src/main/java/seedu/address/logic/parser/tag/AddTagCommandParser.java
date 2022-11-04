package seedu.address.logic.parser.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.tag.AddTagCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    public static final String MESSAGE_TOO_MANY_CONTACTS_OR_TASKS = "You can only add a label to a maximum of "
        + "one contact and one task at a time.";

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTACT, PREFIX_TASK, PREFIX_TAG);

        Index contactIndex;
        Index taskIndex;

        boolean addTagToContact = argMultimap.getValue(PREFIX_CONTACT).isPresent();
        boolean addTagToTask = argMultimap.getValue(PREFIX_TASK).isPresent();

        if (argMultimap.getAllValues(PREFIX_CONTACT).size() > 1 || argMultimap.getAllValues(PREFIX_TASK).size() > 1) {
            throw new ParseException(MESSAGE_TOO_MANY_CONTACTS_OR_TASKS);
        }

        try {
            contactIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT).orElse("1"));
            taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK).orElse("1"));
        } catch (ParseException pe) {
            throw new ParseException(Messages.MESSAGE_INVALID_PERSON_OR_TASK_DISPLAYED_INDEX);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTaskDescriptor::setTags);
        List<String> tagStrings = argMultimap.getAllValues(PREFIX_TAG);


        if (!editPersonDescriptor.isAnyFieldEdited() && !editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddTagCommand.MESSAGE_TAG_NOT_ADDED);
        }

        return new AddTagCommand(contactIndex, taskIndex, editPersonDescriptor, editTaskDescriptor,
            addTagToContact, addTagToTask, tagStrings);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
