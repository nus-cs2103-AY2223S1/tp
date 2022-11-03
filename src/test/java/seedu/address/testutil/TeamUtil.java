package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;

import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.model.team.Team;

public class TeamUtil {

    public static String getAddTeamCommand(Team team) {
        return AddTeamCommand.FULL_COMMAND + " " + getTeamDetails(team);
    }

    public static String getTeamDetails(Team team) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(team.getTeamName().toString()).append(" ");
        sb.append(FLAG_DESCRIPTION_STR + "\"").append(team.getDescription().toString()).append("\" ");
        return sb.toString();
    }
}
