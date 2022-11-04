package seedu.address.testutil;

import seedu.address.model.tag.Tag;
import seedu.address.model.team.Team;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.logic.parser.CliSyntax.*;

public class TeamUtil {

    public static String[] convertTeamToArgs(Team team) {
        List<String> argList = new ArrayList<>();
        argList.add(team.getTeamName().teamName);
        argList.add(FLAG_DESCRIPTION_STR);
        argList.add(team.getDescription().description);
        return argList.toArray(new String[0]);
    }

    public static String[] convertEditTeamToArgs(Team team) {
        List<String> argList = new ArrayList<>();
        argList.add(FLAG_NAME_STR);
        argList.add(team.getTeamName().teamName);
        argList.add(FLAG_DESCRIPTION_STR);
        argList.add(team.getDescription().description);
        return argList.toArray(new String[0]);
    }
}
