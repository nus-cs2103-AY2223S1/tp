package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.Group;

/**
 * Represents the commands that can take in team as an additonal parameter
 */
public abstract class TeamInputCommand extends Command {

    protected Group group = null;

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Group)) {
            group = null;
            return this;
        }
        this.group = (Group) additionalData;
        return this;
    }
}
