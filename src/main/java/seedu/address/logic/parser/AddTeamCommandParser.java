package seedu.address.logic.parser;

import java.util.ArrayList;

import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Team;

public class AddTeamCommandParser implements Parser<AddTeamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTeamCommand
     * and returns a AddTeamCommand object for execution.
     *
     * @throws ParseException if user input does not conform the expected format
     */
    @Override
    public AddTeamCommand parse(String args) throws ParseException {
        return new AddTeamCommand(new Team(args, new ArrayList<>()));
    }
}
