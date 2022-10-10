package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Team;


/**
 * Parses input arguments and creates a new AddTeamCommand object
 */
public class AddTeamCommandParser implements Parser<AddTeamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTeamCommand.
     *
     * @param args refer to the subsequent arguments after the initial command word.
     * @return an AddTeamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTeamCommand parse(String args) throws ParseException {
        try {
            requireNonNull(args);
            String trimmedArgs = args.trim();
            Team newTeam = ParserUtil.parseTeam(trimmedArgs);
            return new AddTeamCommand(newTeam);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            AddTeamCommand.MESSAGE_USAGE), pe);
        }
    }
}
