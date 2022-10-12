package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.teams.ChangeTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ChangeTeamCommandParser implements Parser<ChangeTeamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeTeamCommand parse(String args) throws ParseException {
        try {
            if (args.trim().equals("..")) {
                return new ChangeTeamCommand(null);
            }
            Index index = ParserUtil.parseIndex(args);
            return new ChangeTeamCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeTeamCommand.MESSAGE_USAGE), pe);
        }
    }

}
