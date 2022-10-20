package soconnect.logic.parser.tagcommandparsers;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import soconnect.logic.commands.tagcommands.TagCreateCommand;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCreateCommand object.
 */
public class TagCreateCommandParser implements Parser<TagCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCreateCommand
     * and returns an TagCreateCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public TagCreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCreateCommand.MESSAGE_USAGE));
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());

        return new TagCreateCommand(tag);
    }

}

