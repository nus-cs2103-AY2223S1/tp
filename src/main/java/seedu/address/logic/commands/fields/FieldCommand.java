package seedu.address.logic.commands.fields;

import java.util.List;
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

    DisplayItem sItem = null;

    @Override
    public void setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof DisplayItem)) {
            sItem = null;
            return;
        }
        sItem = (DisplayItem) additionalData;
    }    

    protected DisplayItem   selectFromRightModel(Model model, String type, Index targetIndex) throws ParseException, CommandException {
        List<? extends DisplayItem> lastShownList = null;
        switch (type) {
            case "g":
                lastShownList = model.getFilteredTeamList();
                break;
            case "t":
                lastShownList = model.getFilteredTaskList();
                break;
            case "u":
                lastShownList = model.getFilteredPersonList();
                break;
            default:
                break;
        }
        if (lastShownList == null) {
            return null;
        }
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(INDEX_OUT_OF_BOUND);
        }
        return lastShownList.get(targetIndex.getZeroBased());
    }

    public static String getRestOfArgs(String args) {
        Matcher match = PATTERN.matcher(args.trim());
        if (!match.matches()) {
            return args.trim();
        }
        return match.group("rest").trim();
    }
}
