package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.REditCommand;
import foodwhere.logic.commands.REditCommand.EditReviewDescriptor;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Tag;

/**
 * Parses input arguments and creates a new REditCommand object
 */
public class REditCommandParser implements Parser<REditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the REditCommand
     * and returns an REditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public REditCommand parse(String args) throws ParseException {
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, REditCommand.MESSAGE_USAGE), pe);
        }

        EditReviewDescriptor editReviewDescriptor = new EditReviewDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            editReviewDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_CONTENT).isPresent()) {
            editReviewDescriptor.setContent(ParserUtil.parseContent(
                    argMultimap.getValue(CliSyntax.PREFIX_CONTENT).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_RATING).isPresent()) {
            editReviewDescriptor.setRating(ParserUtil.parseRating(
                    Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_RATING).get())));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG))
                .ifPresent(editReviewDescriptor::setTags);

        if (!editReviewDescriptor.isAnyFieldEdited()) {
            throw new ParseException(REditCommand.MESSAGE_NOT_EDITED);
        }

        return new REditCommand(index, editReviewDescriptor);
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
