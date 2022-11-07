package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.tag.DeleteAllCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAllCommand object
 */
public class DeleteAllCommandParser implements Parser<DeleteAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAllCommand
     * and returns a DeleteAllCommand object for execution.
     *
     * @param args Raw arguments from user.
     * @return DeleteAllCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public DeleteAllCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAllCommand.MESSAGE_USAGE));
        }

        List<String> tags = List.of(trimmedArgs.split(" "));
        return new DeleteAllCommand(ParserUtil.parseTags(tags));
    }
}
