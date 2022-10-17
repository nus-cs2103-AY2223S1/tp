package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GithubCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class GithubCommandParser implements Parser<GithubCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GithubCommand
     * and returns a GithubCommand object that contains an index for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GithubCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new GithubCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GithubCommand.MESSAGE_USAGE), pe);
        }
    }

}
