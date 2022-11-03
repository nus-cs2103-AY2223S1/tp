package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR;

import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.model.team.Link;

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
}
