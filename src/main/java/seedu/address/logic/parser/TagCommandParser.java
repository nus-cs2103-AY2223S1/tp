package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseIndex;
import static seedu.address.logic.parser.ParserUtil.parseTags;

import java.util.Arrays;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns a TagCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        String[] argArray = trimmedArgs.split("\\s+");

        Index index;
        Set<Tag> tags;
        try {
            index = parseIndex(argArray[0]);
            tags = parseTags(Arrays.asList(argArray).subList(1, argArray.length));
        } catch (ParseException e) {
            throw new ParseException(TagCommand.MESSAGE_USAGE);
        }

        return new TagCommand(index, tags);
    }
}
