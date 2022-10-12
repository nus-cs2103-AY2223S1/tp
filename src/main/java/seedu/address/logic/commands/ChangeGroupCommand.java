package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Path;

/**
 * Change the scope of the group to a different group.
 */
public class ChangeGroupCommand extends Command {

    public static final String COMMAND_WORD = "cg"; // "cg" stands for change group

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change to a currently "
            + "existing group. Group names should be alphanumeric and may contain hyphens and "
            + "underscores.\n"
            + "Parameters: group_name/group_within_group_name\n"
            + "Example: " + COMMAND_WORD + " group_1/group_a";

    public static final String MESSAGE_SUCCESS = "Changed group to: %1$s";

    public static final String MESSAGE_NO_GROUP_FOUND = "This group scope does not currently "
            + "exist in the address book";

    public static final String MESSAGE_IN_CURRENT_GROUP = "You are currently in the group "
            + "scope specified.";

    private final Path path;

    /**
     * Creates a ChangeGroupCommand to change scope of group to the specified {@Code Group}
     */
    public ChangeGroupCommand(Path path) {
        this.path = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String newPath = path.getPath();
        if (model.isInSamePath(newPath)) {
            throw new CommandException(MESSAGE_IN_CURRENT_GROUP);
        }

        if (!model.canChangeContext(newPath)) {
            throw new CommandException(MESSAGE_NO_GROUP_FOUND);
        }

        model.changeContext(newPath);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newPath));
    }
}
