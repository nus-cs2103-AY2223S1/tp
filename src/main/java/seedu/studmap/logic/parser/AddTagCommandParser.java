package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.logic.commands.AddTagCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;
        Set<Tag> tags;
        try {
            tags = parseTagsSet(argMultimap.getAllValues(PREFIX_TAG));
            if (tags.size() == 0) {
                throw new ParseException(AddTagCommand.MESSAGE_TAGS_NOT_ADDED);
            }
            if (argMultimap.getPreamble().equalsIgnoreCase("all")) {
                return new AddTagCommand(tags);
            }
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE), ive);
        }

        return new AddTagCommand(index, tags);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Set<Tag> parseTagsSet(Collection<String> tags) throws ParseException {
        assert tags != null && !tags.isEmpty();

        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return ParserUtil.parseTags(tagSet);
    }
}
