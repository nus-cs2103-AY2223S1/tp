package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Path;

/**
 * Change the scope of the team to a different team.
 */
public class ChangeTeamCommand extends Command {

    public static final String COMMAND_WORD = "ct"; // "ct" stands for change team

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change to a currently "
            + "existing team. Team names should be alphanumeric and may contain hyphens and "
            + "underscores.\n"
            + "Parameters: team_name/team_within_team_name\n"
            + "Example: " + COMMAND_WORD + " team_1/team_a";

    public static final String MESSAGE_SUCCESS = "Changed team to: %1$s";

    public static final String MESSAGE_NO_TEAM_FOUND = "This team scope does not currently "
            + "exist in the address book";

    public static final String MESSAGE_IN_CURRENT_TEAM = "You are currently in the team "
            + "scope specified.";

    private final Path path;

    /**
     * Creates a ChangeTeamCommand to change scope of team to the specified {@Code Team}
     */
    public ChangeTeamCommand(Path path) {
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String newPath = path.getPath();
        if (model.isInSamePath(newPath)) {
            throw new CommandException(MESSAGE_IN_CURRENT_TEAM);
        }

        if (!model.canChangeContext(newPath)) {
            throw new CommandException(MESSAGE_NO_TEAM_FOUND);
        }

        model.changeContext(newPath);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
