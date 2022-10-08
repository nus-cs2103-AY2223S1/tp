package seedu.travelr.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.EditCommand;
import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.event.Event;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESC, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditTripDescriptor editTripDescriptor = new EditTripDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editTripDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESC).isPresent()) {
            editTripDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTripDescriptor::setTags);

        if (!editTripDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editTripDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Event>> parseTagsForEdit(Collection<String> events) throws ParseException {
        assert events != null;

        if (events.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> eventSet = events.size() == 1 && events.contains("") ? Collections.emptySet() : events;
        return Optional.of(ParserUtil.parseEvents(eventSet));
    }

}
