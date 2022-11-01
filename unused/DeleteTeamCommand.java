package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

// @@author mohamedsaf1
/**
 * Deletes a team from Contactmation
 */
public class DeleteTeamCommand extends TeamCommand {
    public static final String SUBCOMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUBCOMMAND_WORD
        + ": Delete the team with the specified index\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " " + SUBCOMMAND_WORD + " 1\n";

    public static final String SWITCH_SUCCESS = " Deleted %s%n";

    private Group toDelete = null;

    private final Index targetIndex;

    public DeleteTeamCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (toDelete == null && targetIndex == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        if (toDelete == null) {
            toDelete = model.getFromFilteredTeams(targetIndex);
        }

        model.deleteTeam(toDelete);
        return new CommandResult(String.format(SWITCH_SUCCESS, toDelete));
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof Group)) {
            toDelete = null;
            return this;
        }
        this.toDelete = (Group) additionalData;
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeleteTeamCommand)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        DeleteTeamCommand c = (DeleteTeamCommand) other;
        if (targetIndex == null) {
            if (c.targetIndex != null) {
                return false;
            }
        } else if (!targetIndex.equals(c.targetIndex)) {
            return false;
        }

        if (toDelete == null) {
            if (c.toDelete != null) {
                return false;
            }
        } else if (!toDelete.equals(c.toDelete)) {
            return false;
        }

        return true;
    }
}
