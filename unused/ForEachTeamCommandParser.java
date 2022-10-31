package seedu.address.logic.parser.teams;

import seedu.address.logic.commands.teams.ForEachTeamCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse user input for ForEachTeam Command
 */
public class ForEachTeamCommandParser implements Parser<ForEachTeamCommand> {

    @Override
    public ForEachTeamCommand parse(String userInput) throws ParseException {
        return new ForEachTeamCommand(userInput.trim());
    }

}
