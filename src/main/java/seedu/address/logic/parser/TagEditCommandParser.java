package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.TagEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagEditCommand object
 */
public class TagEditCommandParser implements Parser<TagEditCommand> {

    @Override
    public TagEditCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
