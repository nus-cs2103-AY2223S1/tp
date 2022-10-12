package seedu.address.logic.parser;

import seedu.address.logic.commands.teams.TeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new TeamCommand object
 */
public class TeamCommandParser implements Parser<TeamCommand> {

    @Override
    public TeamCommand parse(String userInput) throws ParseException {
        return new TeamCommand(new Group(userInput.trim()));
    }

}
