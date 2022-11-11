package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.UntagCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Parses input arguments and creates a new UntagCommand object
 */
public class UntagCommandParser implements Parser<UntagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UntagCommand
     * and returns an UntagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public UntagCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        requireNonNull(args);
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(UntagCommand.MESSAGE_USAGE);
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UntagCommand.MESSAGE_USAGE),
                    pe);
        }

        Optional<Set<Tag>> tagsToRemove = parseTags(argMultimap.getAllValues(PREFIX_TAG));

        if (tagsToRemove.isEmpty()) {
            throw new ParseException(UntagCommand.MESSAGE_NOT_UNTAGGED);
        }

        return new UntagCommand(index, tagsToRemove.get());
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTags(Collection<String> tags) throws ParseException {
        assert tags != null;
        return tags.isEmpty() ? Optional.empty() : Optional.of(ParserUtil.parseTags(tags));
    }

}
