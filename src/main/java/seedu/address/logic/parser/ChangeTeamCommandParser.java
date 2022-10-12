package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ChangeTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Path;

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
        try {
            requireNonNull(args);
            Path path = ParserUtil.parsePath(args);
            return new ChangeTeamCommand(path);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ChangeTeamCommand.MESSAGE_USAGE), pe);
        }
    }
}