package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.item.AbstractContainerItem;

/**
 * Changes a current working context of the team
 */
public class ChangeTeamCommand extends Command {
    public static final String COMMAND_WORD = "cg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the current context to the index specified\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "To go back to the previous context, use\n"
            + COMMAND_WORD + " ..";

    public static final String SWITCH_SUCCESS = " switched %s%n";

    private final Index targetIndex;

    public ChangeTeamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AbstractContainerItem toSwitch;
        if (targetIndex == null) {
            if (model.getContextContainer() != null) {
                toSwitch = model.getContextContainer().getParent();
            } else {
                return new CommandResult("No more parent!");
            }
        } else {
            List<Group> lastShownList = model.getFilteredTeamList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            toSwitch = lastShownList.get(targetIndex.getZeroBased());
        }
        model.updateContextContainer(toSwitch);
        return new CommandResult(String.format(SWITCH_SUCCESS, toSwitch));
    }
}
