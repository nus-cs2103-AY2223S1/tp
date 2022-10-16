package soconnect.logic.parser.tagcommandparsers;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import soconnect.commons.core.index.Index;
import soconnect.logic.commands.tagcommands.TagRemoveCommand;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;

public class TagRemoveCommandParser implements Parser<TagRemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagRemoveCommand
     * and returns an TagRemoveCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public TagRemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;
        Tag tag;

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(TagRemoveCommand.MESSAGE_NO_TAG);
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
            return new TagRemoveCommand(index, tag);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagRemoveCommand.MESSAGE_USAGE));
        }
    }
}
