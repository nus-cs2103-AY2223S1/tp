package foodwhere.logic.parser;

import static foodwhere.logic.commands.SEditCommand.MESSAGE_INVALID_INDEX_ERROR;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.SEditCommand;
import foodwhere.logic.commands.SEditCommand.EditStallDescriptor;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Tag;

/**
 * Parses input arguments and creates a new SEditCommand object
 */
public class SEditCommandParser implements Parser<SEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SEditCommand
     * and returns an SEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_ADDRESS,
                        CliSyntax.PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INDEX_ERROR);
        }

        EditStallDescriptor editStallDescriptor = new EditStallDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editStallDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            editStallDescriptor.setAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG))
                .ifPresent(editStallDescriptor::setTags);

        return new SEditCommand(index, editStallDescriptor);
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
