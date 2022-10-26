package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import hobbylist.logic.commands.FilterStatusCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.StatusMatchesGivenStatus;

/**
 * Parses input arguments and creates a new FilterStatusCommand object
 */
public class FilterStatusCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterStatusCommand
     * and returns a FilterStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterStatusCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterStatusCommand.MESSAGE_USAGE));
        }

        return new FilterStatusCommand(new StatusMatchesGivenStatus(trimmedArgs));
    }
}
