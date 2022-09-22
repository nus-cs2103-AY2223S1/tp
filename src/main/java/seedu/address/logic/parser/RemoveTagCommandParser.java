package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.parseIndex;
import static seedu.address.logic.parser.ParserUtil.parseTags;

import java.util.Arrays;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new RemoveTagCommand object
 */
public class RemoveTagCommandParser implements Parser<RemoveTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveTagCommand
     * and returns a RemoveTagCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public RemoveTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTagCommand.MESSAGE_USAGE));
        }

        String[] argArray = trimmedArgs.split("\\s+");
        Index index;
        Set<Tag> tags;
        try {
            index = parseIndex(argArray[0]);
            tags = parseTags(Arrays.asList(argArray).subList(1, argArray.length));
        } catch (ParseException e) {
            throw new ParseException(RemoveTagCommand.MESSAGE_USAGE);
        }

        return new RemoveTagCommand(index, tags);
    }
}
