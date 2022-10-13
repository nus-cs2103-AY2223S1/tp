package seedu.address.logic.parser;

import java.util.ArrayList;

import seedu.address.logic.commands.DeleteTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Team;

/**
 * Parses input arguments and creates a new DeleteTeamCommand object.
 */
public class DeleteTeamCommandParser implements Parser<DeleteTeamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTeamCommand
     * and returns a DeleteTeamCommand object for execution.
     *
     * @throws ParseException if user input does not conform to the expected format
     */
    @Override
    public DeleteTeamCommand parse(String args) throws ParseException {
        return new DeleteTeamCommand(new Team(args.trim(), new ArrayList<>()));
    }
}
