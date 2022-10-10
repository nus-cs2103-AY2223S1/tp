package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.containsWhitespace;

import seedu.address.logic.commands.ChangeTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Path;

/**
 * Parses input arguments and creates a new ChangeTeamCommand object
 */
public class ChangeTeamCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeTeamCommand.
     *
     * @param args refer to the subsequent arguments after the initial command word.
     * @return a ChangeTeamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeTeamCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (containsWhitespace(trimmedArgs)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTeamCommand.MESSAGE_USAGE));
        }

        Path path = new Path(trimmedArgs);
        return new ChangeTeamCommand(path);
    }
}