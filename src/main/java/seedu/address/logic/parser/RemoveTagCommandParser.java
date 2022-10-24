package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
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
        System.out.println(trimmedArgs);

        String[] argArray = trimmedArgs.split("\\s+");

        Optional<Index> index;
        Set<Tag> tags;

        try {
            System.out.println("haha");
            index = parseIndex(argArray[0]);
            tags = ParserUtil.parseTags(Arrays.asList(argArray).subList(index.isPresent() ? 1 : 0, argArray.length));
        } catch (ParseException e) {
            System.out.println("haha");
            throw new ParseException(RemoveTagCommand.MESSAGE_USAGE);
        }

        return index.isPresent() ? new RemoveTagCommand(index.get(), tags) : new RemoveTagCommand(tags);
    }

    private Optional<Index> parseIndex(String arg) throws ParseException {
        if (StringUtil.isNonZeroUnsignedInteger(arg)) {
            return Optional.of(ParserUtil.parseIndex(arg));
        }
        return Optional.empty();
    }
}
