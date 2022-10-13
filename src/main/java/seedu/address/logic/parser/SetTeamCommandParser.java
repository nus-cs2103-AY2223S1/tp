package seedu.address.logic.parser;

import java.util.ArrayList;

import seedu.address.logic.commands.SetTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Team;
/**
 * Parses input arguments and creates a new SetTeamCommand object.
 */
public class SetTeamCommandParser implements Parser<SetTeamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SetTeamCommand
     * and returns a SetTeamCommand object for execution.
     *
     * @throws ParseException if user input does not conform to the expected format
     */
    @Override
    public SetTeamCommand parse(String args) throws ParseException {
        return new SetTeamCommand(new Team(args.trim(), new ArrayList<>()));
    }
}
