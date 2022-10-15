package soconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import soconnect.commons.core.index.Index;
import soconnect.logic.commands.TagAddCommand;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagAddCommand object
 */
public class TagAddCommandParser implements Parser<TagAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagAddCommand
     * and returns an TagAddCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public TagAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;
        Tag tag;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagAddCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        } else {
            throw new ParseException(TagAddCommand.MESSAGE_NO_TAG);
        }

        return new TagAddCommand(index, tag);
    }
}
