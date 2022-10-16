package soconnect.logic.parser.tagcommandparsers;


import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.*;

import java.util.List;

import soconnect.logic.commands.tagcommands.TagEditCommand;
import soconnect.logic.parser.*;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagEditCommand object.
 */
public class TagEditCommandParser implements Parser<TagEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagEditCommand
     * and returns an TagEditCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public TagEditCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagEditCommand.MESSAGE_USAGE));
        }

        List<Tag> tagList = ParserUtil.parseTagsIntoList(argMultimap.getAllValues(PREFIX_TAG));

        if (tagList.size() != 2) {
            throw new ParseException(TagEditCommand.MESSAGE_NOT_EDITED);
        }

        Tag oldTagName = tagList.get(0);
        Tag newTagName = tagList.get(1);

        return new TagEditCommand(oldTagName, newTagName);
    }

}
