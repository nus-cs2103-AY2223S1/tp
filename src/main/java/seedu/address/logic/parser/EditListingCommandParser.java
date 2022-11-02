package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditListingCommand;
import seedu.address.logic.commands.EditListingCommand.EditListingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditListingCommandParser implements Parser<EditListingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditListingCommand
     * and returns an EditListingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditListingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LISTING_ID, PREFIX_ADDRESS, PREFIX_NAME, PREFIX_ASKING_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            EditListingCommand.MESSAGE_USAGE), pe);
        }

        EditListingDescriptor editListingDescriptor = new EditListingDescriptor();
        if (argMultimap.getValue(PREFIX_LISTING_ID).isPresent()) {
            editListingDescriptor.setId(ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editListingDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editListingDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_ASKING_PRICE).isPresent()) {
            editListingDescriptor.setAskingPrice(
                ParserUtil.parsePrice(argMultimap.getValue(PREFIX_ASKING_PRICE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editListingDescriptor::setTags);

        if (!editListingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditListingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditListingCommand(index, editListingDescriptor);
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

