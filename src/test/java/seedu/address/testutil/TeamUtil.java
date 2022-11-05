package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.team.Team;

/**
 * Utility class to convert {@code Team} to String arguments for testing
 */
public class TeamUtil {

    /**
     * Converts {@code Team} to required arguments.
     */
    public static String[] convertTeamToArgs(Team team) {
        List<String> argList = new ArrayList<>();
        argList.add(team.getTeamName().teamName);
        argList.add(FLAG_DESCRIPTION_STR);
        argList.add(team.getDescription().description);
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Team} to required arguments.
     */
    public static String[] convertEditTeamToArgs(Team team) {
        List<String> argList = new ArrayList<>();
        argList.add(FLAG_NAME_STR);
        argList.add(team.getTeamName().teamName);
        argList.add(FLAG_DESCRIPTION_STR);
        argList.add(team.getDescription().description);
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Team} to required arguments.
     */
    public static String[] convertEditPartialTeamToArgs(Team team) {
        List<String> argList = new ArrayList<>();
        argList.add(FLAG_DESCRIPTION_STR);
        argList.add(team.getDescription().description);
        return argList.toArray(new String[0]);
    }
}
