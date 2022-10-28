package seedu.address.logic.commands.fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.person.Person;

/**
 * Commands for Tasks
 */
public abstract class FieldCommand extends Command {

    public static final String COMMAND_WORD = "field";
    private static final String INDEX_OUT_OF_BOUND = "The chosen index is out of range for %s list!";
    private static final Pattern PATTERN = Pattern.compile("(?<type>[gtl])/(?<id>[0-9]+)\\s+(?<rest>.*)");

    /**
     * Returns the complete command phrase for the task command with given
     * subCommand
     *
     * @param subcommand The subcommand to be added
     * @return The complete command phrase
     */
    static String getFullCommand(String subcommand) {
        return COMMAND_WORD + " " + subcommand;
    }

    private Index Index;

    protected DisplayItem selectFromRightModel(Model model, String args) throws ParseException {
        DisplayItem ret = null;
        Matcher match = PATTERN.matcher(args);
        if (!match.matches()) {
            return null;
        }

        String type = match.group("type");
        Index index = ParserUtil.parseIndex(match.group("id"));
        List<? extends DisplayItem> 
        switch (type) {
            case "g":
                model.getFilteredTeamList();
            case "t":

            case "u":

            default:
                break;
        }
    }
}
