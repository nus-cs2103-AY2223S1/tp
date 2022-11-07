package seedu.address.logic.parser.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.ParserUtil.parseTagsForEdit;

import java.util.List;

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

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    public static final String MESSAGE_TOO_MANY_CONTACTS_OR_TASKS = "You can only add a label to a maximum of "
        + "one contact and one task at a time.";
    public static final String MESSAGE_MISSING_INDEX = "At least 1 contact or task index must be provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTACT, PREFIX_TASK, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        Index contactIndex;
        Index taskIndex;

        boolean addTagToContact = argMultimap.getValue(PREFIX_CONTACT).isPresent();
        boolean addTagToTask = argMultimap.getValue(PREFIX_TASK).isPresent();

        if (!addTagToContact && !addTagToTask) {
            throw new ParseException(MESSAGE_MISSING_INDEX);
        }

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

}
