package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import hobbylist.logic.commands.FindStatusCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.StatusMatchesGivenStatus;

/**
 * Parses input arguments and creates a new FindStatusCommand object
 */
public class FindStatusCommandParser implements Parser<FindStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStatusCommand
     * and returns a FindStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStatusCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStatusCommand.MESSAGE_USAGE));
        }

        return new FindStatusCommand(new StatusMatchesGivenStatus(trimmedArgs));
    }
}
