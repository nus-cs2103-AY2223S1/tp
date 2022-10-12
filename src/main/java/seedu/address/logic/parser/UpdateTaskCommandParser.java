package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new UpdateTaskCommand object.
 */
public class UpdateTaskCommandParser implements Parser<UpdateTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UpdateTaskCommand
     * and returns a UpdateTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE),
                    pe);
        }

        UpdateTaskCommand.UpdateTaskDescriptor updateTaskDescriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            updateTaskDescriptor.setTitle(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            updateTaskDescriptor.setDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(updateTaskDescriptor::setTags);

        if (!updateTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateTaskCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateTaskCommand(index, updateTaskDescriptor);
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
