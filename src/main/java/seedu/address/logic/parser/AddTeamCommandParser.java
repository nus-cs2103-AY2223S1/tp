package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.containsBackslash;
import static seedu.address.logic.parser.ParserUtil.containsWhitespace;

import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
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
        String trimmedArgs = args.trim();

        if (containsWhitespace(trimmedArgs) || containsBackslash(trimmedArgs)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE));
        }

        Name teamName = ParserUtil.parseName(args);
        Team newTeam = new Team(teamName);
        return new AddTeamCommand(newTeam);
    }
}
