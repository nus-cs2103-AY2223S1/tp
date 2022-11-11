package seedu.address.logic.commands.fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;

/**
 * Commands for Tasks
 */
public abstract class FieldCommand extends Command {

    public static final String COMMAND_WORD = "field";
    private static final Pattern PATTERN = Pattern.compile("(?<type>[gtl])/(?<id>[0-9]+)\\s+(?<rest>.*)");
    protected DisplayItem sItem = null;

    /**
     * Returns the complete command phrase for the task command with given subCommand
     *
     * @param subcommand The subcommand to be added
     * @return The complete command phrase
     */
    static String getFullCommand(String subcommand) {
        return COMMAND_WORD + " " + subcommand;
    }


    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof DisplayItem)) {
            sItem = null;
            return this;
        }
        sItem = (DisplayItem) additionalData;
        return this;
    }

    protected DisplayItem selectFromRightModel(Model model, String type, Index targetIndex)
            throws ParseException, CommandException {

        switch (type) {
        case "g":
            return model.getFromFilteredTeams(targetIndex);
        case "t":
            return model.getFromFilteredTasks(targetIndex);
        case "u":
            return model.getFromFilteredPerson(targetIndex);
        default:
            return null;
        }
    }

    public static String getRestOfArgs(String args) {
        Matcher match = PATTERN.matcher(args.trim());
        if (!match.matches()) {
            return args.trim();
        }
        return match.group("rest").trim();
    }
}
