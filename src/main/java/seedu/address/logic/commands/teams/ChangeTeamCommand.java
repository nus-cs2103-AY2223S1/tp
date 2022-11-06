package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TeamInputCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractSingleItem;

// @@author autumn-sonata
/**
 * Changes a current working context of the team
 */
public class ChangeTeamCommand extends TeamInputCommand {
    public static final String COMMAND_WORD = "cg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Changes the current context to the index specified\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1\n"
        + "To go back to the previous context, use\n"
        + COMMAND_WORD + " ..";

    public static final String SWITCH_SUCCESS = " switched to %s%n";

    private final Index targetIndex;
    private final int status;
    // status table
    // 1 - normal/read from index
    // 0 - traverse up 1 directory
    // -1 - traverse to root
    // 2 - use setter

    /**
     * Constructor for cg command
     */
    public ChangeTeamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        status = 1;
    }

    /**
     * Constructor when cg is expected to receive an input
     */
    public ChangeTeamCommand(int status) {
        this.targetIndex = null;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AbstractSingleItem toSwitch;
        if (status == 1) {
            List<Group> lastShownList = model.getFilteredTeamList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TEAM_DISPLAYED_INDEX);
            }

            toSwitch = lastShownList.get(targetIndex.getZeroBased());
        } else if (status == 0) {
            if (model.getContextContainer() != null) {
                toSwitch = model.getContextContainer().getParent();
            } else {
                return new CommandResult("No more parent!");
            }
        } else if (status == -1) {
            toSwitch = null;
        } else {
            assert status == -2;
            if (group == null) {
                throw new CommandException("Method takes in an input of group!");
            }
            toSwitch = group;
        }

        model.updateContextContainer(toSwitch);
        return new CommandResult(String.format(SWITCH_SUCCESS, toSwitch == null ? "root" : toSwitch.toString()));
    }
}
