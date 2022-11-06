package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

/**
 * Utility class that helps to convert links to commands.
 */
public class LinkUtil {
    public static String getAddLinkCommand(Link link) {
        return AddLinkCommand.FULL_COMMAND + " " + getLinkDetails(link);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getLinkDetails(Link link) {
        StringBuilder sb = new StringBuilder();
        sb.append(FLAG_NAME_STR + "\"").append(link.getDisplayedName().toString()).append("\" ");
        sb.append(FLAG_URL_STR + "\"").append(link.getUrl()).append("\" ");
        return sb.toString();
    }

    /**
     * Converts {@code Link} to required arguments.
     */
    public static String[] convertLinkToArgs(Link link) {
        List<String> argList = new ArrayList<>();
        argList.add(FLAG_NAME_STR);
        argList.add(link.getDisplayedName().toString());
        argList.add(FLAG_URL_STR);
        argList.add(link.getUrl().toString());
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Link} to required arguments, along with task index.
     */
    public static String[] convertEditLinkToArgs(Link link, int taskIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(taskIndex));
        argList.add(FLAG_NAME_STR);
        argList.add(link.getDisplayedName().toString());
        argList.add(FLAG_URL_STR);
        argList.add(link.getUrl().toString());
        return argList.toArray(new String[0]);
    }

    /**
     * Converts {@code Link} to only change name, along with task index.
     */
    public static String[] convertEditPartialLinkToArgs(Link link, int taskIndex) {
        List<String> argList = new ArrayList<>();
        argList.add(String.valueOf(taskIndex));
        argList.add(FLAG_NAME_STR);
        argList.add(link.getDisplayedName().toString());
        return argList.toArray(new String[0]);
    }
}
